package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w1101.entity.Order;
import com.w1101.mapper.OrderMapper;
import com.w1101.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public IPage<Order> getOrderList(int page, int pageSize, String orderId, Integer memberId, String status, String startDate, String endDate) {
        Page<Order> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();

        // 订单号搜索
        if (StringUtils.isNotBlank(orderId)) {
            wrapper.like(Order::getOrderId, orderId);
        }

        // 会员ID搜索
        if (memberId != null) {
            wrapper.eq(Order::getMemberId, memberId);
        }

        // 状态筛选
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(Order::getStatus, status);
        }

        // 日期范围筛选
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (StringUtils.isNotBlank(startDate)) {
            LocalDateTime start = LocalDateTime.parse(startDate + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            wrapper.ge(Order::getCreateTime, start);
        }
        if (StringUtils.isNotBlank(endDate)) {
            LocalDateTime end = LocalDateTime.parse(endDate + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            wrapper.le(Order::getCreateTime, end);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(Order::getCreateTime);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public IPage<Order> getOrderList(int page, int pageSize, String keyword, String status) {
        Page<Order> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();

        // 关键词搜索（订单号或会员名称）
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like(Order::getOrderId, keyword).or().like(Order::getMemberNickname, keyword);
        }

        // 状态筛选
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(Order::getStatus, status);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(Order::getCreateTime);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public Order getOrderById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean updateOrderStatus(Integer id, String status, String remark) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        order.setRemark(remark);
        order.setUpdateTime(LocalDateTime.now());
        return this.updateById(order);
    }

    @Override
    public boolean cancelOrder(Integer id, String remark) {
        return updateOrderStatus(id, "cancelled", remark);
    }

    @Override
    public boolean completeOrder(Integer id, String remark) {
        return updateOrderStatus(id, "completed", remark);
    }

    @Override
    public boolean shipOrder(Integer id, String remark) {
        return updateOrderStatus(id, "shipped", remark);
    }
}
