package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Order;

public interface OrderService {

    IPage<Order> getOrderList(int page, int pageSize, String orderId, Integer memberId, String status, String startDate, String endDate);

    IPage<Order> getOrderList(int page, int pageSize, String keyword, String status);

    Order getOrderById(Integer id);

    boolean updateOrderStatus(Integer id, String status, String remark);

    boolean cancelOrder(Integer id, String remark);

    boolean completeOrder(Integer id, String remark);

    boolean shipOrder(Integer id, String remark);
}
