package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w1101.entity.Exchange;
import com.w1101.mapper.ExchangeMapper;
import com.w1101.service.ExchangeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeServiceImpl extends ServiceImpl<ExchangeMapper, Exchange> implements ExchangeService {

    @Override
    public IPage<Exchange> getExchangeRecords(int page, int pageSize, Integer userId, String status) {
        Page<Exchange> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Exchange> wrapper = new LambdaQueryWrapper<>();

        // 用户ID筛选
        if (userId != null) {
            wrapper.eq(Exchange::getMemberId, userId);
        }

        // 状态筛选
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(Exchange::getStatus, status);
        }

        // 按兑换时间倒序
        wrapper.orderByDesc(Exchange::getExchangeTime);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public boolean completeExchange(Integer id) {
        Exchange exchange = this.getById(id);
        if (exchange == null) {
            return false;
        }

        exchange.setStatus("completed");
        exchange.setCompleteTime(new java.util.Date());
        return this.updateById(exchange);
    }

    @Override
    public Map<String, Object> getExchangeStats() {
        Map<String, Object> stats = new HashMap<>();

        // 这里需要根据实际情况实现统计逻辑
        // 暂时返回模拟数据
        stats.put("totalExchange", 200);
        stats.put("totalPoints", 50000);

        return stats;
    }

    @Override
    public Exchange getExchangeById(Integer id) {
        return this.getById(id);
    }
}
