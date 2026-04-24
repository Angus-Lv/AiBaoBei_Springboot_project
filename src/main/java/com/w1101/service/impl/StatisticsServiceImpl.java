package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.w1101.entity.Order;
import com.w1101.mapper.OrderMapper;
import com.w1101.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Map<String, Object> getSalesStatistics(String timeRange, String startDate, String endDate, Integer limit) {
        Map<String, Object> result = new HashMap<>();
        
        // 处理时间范围
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        
        if ("today".equals(timeRange)) {
            LocalDate today = LocalDate.now();
            startTime = today.atStartOfDay();
            endTime = today.atTime(LocalTime.MAX);
        } else if ("week".equals(timeRange)) {
            LocalDate today = LocalDate.now();
            LocalDate monday = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
            LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
            startTime = monday.atStartOfDay();
            endTime = sunday.atTime(LocalTime.MAX);
        } else if ("month".equals(timeRange)) {
            LocalDate today = LocalDate.now();
            LocalDate firstDay = today.withDayOfMonth(1);
            LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
            startTime = firstDay.atStartOfDay();
            endTime = lastDay.atTime(LocalTime.MAX);
        } else if ("custom".equals(timeRange) && startDate != null && endDate != null) {
            startTime = LocalDate.parse(startDate).atStartOfDay();
            endTime = LocalDate.parse(endDate).atTime(LocalTime.MAX);
        } else {
            // 默认查询最近7天
            LocalDate today = LocalDate.now();
            LocalDate sevenDaysAgo = today.minusDays(6);
            startTime = sevenDaysAgo.atStartOfDay();
            endTime = today.atTime(LocalTime.MAX);
        }
        
        // 获取最近订单
        QueryWrapper<Order> recentOrdersWrapper = new QueryWrapper<>();
        recentOrdersWrapper.between("create_time", startTime, endTime);
        recentOrdersWrapper.orderByDesc("create_time");
        if (limit == null) {
            limit = 10;
        }
        recentOrdersWrapper.last("LIMIT " + limit);
        List<Order> recentOrders = orderMapper.selectList(recentOrdersWrapper);
        
        List<Map<String, Object>> recentOrdersList = new ArrayList<>();
        for (Order order : recentOrders) {
            Map<String, Object> orderData = new HashMap<>();
            orderData.put("id", order.getId());
            orderData.put("orderNo", order.getOrderId());
            orderData.put("userName", order.getMemberNickname());
            orderData.put("productCount", order.getTotalQuantity());
            orderData.put("totalAmount", order.getTotalPrice());
            orderData.put("status", order.getStatus());
            orderData.put("createTime", order.getCreateTime());
            recentOrdersList.add(orderData);
        }
        result.put("recentOrders", recentOrdersList);
        
        return result;
    }
    
    @Override
    public List<Map<String, Object>> getOrderStatusDistribution(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> result = new ArrayList<>();
        
        // 处理时间范围
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        
        if ("today".equals(timeRange)) {
            LocalDate today = LocalDate.now();
            startTime = today.atStartOfDay();
            endTime = today.atTime(LocalTime.MAX);
        } else if ("week".equals(timeRange)) {
            LocalDate today = LocalDate.now();
            LocalDate monday = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
            LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
            startTime = monday.atStartOfDay();
            endTime = sunday.atTime(LocalTime.MAX);
        } else if ("month".equals(timeRange)) {
            LocalDate today = LocalDate.now();
            LocalDate firstDay = today.withDayOfMonth(1);
            LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
            startTime = firstDay.atStartOfDay();
            endTime = lastDay.atTime(LocalTime.MAX);
        } else if ("custom".equals(timeRange) && startDate != null && endDate != null) {
            startTime = LocalDate.parse(startDate).atStartOfDay();
            endTime = LocalDate.parse(endDate).atTime(LocalTime.MAX);
        } else {
            // 默认查询最近7天
            LocalDate today = LocalDate.now();
            LocalDate sevenDaysAgo = today.minusDays(6);
            startTime = sevenDaysAgo.atStartOfDay();
            endTime = today.atTime(LocalTime.MAX);
        }
        
        // 统计总订单数
        QueryWrapper<Order> totalOrdersWrapper = new QueryWrapper<>();
        totalOrdersWrapper.between("create_time", startTime, endTime);
        long totalOrders = orderMapper.selectCount(totalOrdersWrapper);
        
        // 统计各状态订单数和占比
        String[] statuses = {"pending", "unshipped", "shipped", "completed", "cancelled", "refunding", "refunded"};
        for (String status : statuses) {
            QueryWrapper<Order> statusWrapper = new QueryWrapper<>();
            statusWrapper.between("create_time", startTime, endTime);
            statusWrapper.eq("status", status);
            long count = orderMapper.selectCount(statusWrapper);
            double percentage = totalOrders > 0 ? (count * 100.0 / totalOrders) : 0;
            
            Map<String, Object> statusData = new HashMap<>();
            statusData.put("status", status);
            statusData.put("count", count);
            statusData.put("percentage", Math.round(percentage * 100) / 100.0);
            result.add(statusData);
        }
        
        return result;
    }

    @Override
    public Map<String, Object> getOrderStatistics(String timeRange, String startDate, String endDate) {
        Map<String, Object> result = new HashMap<>();
        
        // 处理时间范围
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        
        if ("today".equals(timeRange)) {
            LocalDate today = LocalDate.now();
            startTime = today.atStartOfDay();
            endTime = today.atTime(LocalTime.MAX);
        } else if ("week".equals(timeRange)) {
            LocalDate today = LocalDate.now();
            LocalDate monday = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
            LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
            startTime = monday.atStartOfDay();
            endTime = sunday.atTime(LocalTime.MAX);
        } else if ("month".equals(timeRange)) {
            LocalDate today = LocalDate.now();
            LocalDate firstDay = today.withDayOfMonth(1);
            LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
            startTime = firstDay.atStartOfDay();
            endTime = lastDay.atTime(LocalTime.MAX);
        } else if ("custom".equals(timeRange) && startDate != null && endDate != null) {
            startTime = LocalDate.parse(startDate).atStartOfDay();
            endTime = LocalDate.parse(endDate).atTime(LocalTime.MAX);
        } else {
            // 默认查询最近7天
            LocalDate today = LocalDate.now();
            LocalDate sevenDaysAgo = today.minusDays(6);
            startTime = sevenDaysAgo.atStartOfDay();
            endTime = today.atTime(LocalTime.MAX);
        }
        
        // 1. 总订单数
        QueryWrapper<Order> totalOrdersWrapper = new QueryWrapper<>();
        totalOrdersWrapper.between("create_time", startTime, endTime);
        long totalOrders = orderMapper.selectCount(totalOrdersWrapper);
        result.put("totalOrders", totalOrders);
        
        // 2. 总销售额（已完成订单）
        QueryWrapper<Order> totalSalesWrapper = new QueryWrapper<>();
        totalSalesWrapper.between("create_time", startTime, endTime);
        totalSalesWrapper.eq("status", "completed");
        List<Order> completedOrders = orderMapper.selectList(totalSalesWrapper);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Order order : completedOrders) {
            totalAmount = totalAmount.add(order.getTotalPrice());
        }
        result.put("totalAmount", totalAmount);
        
        // 3. 已完成订单
        long completedOrderCount = completedOrders.size();
        result.put("completedOrders", completedOrderCount);
        
        // 4. 退款订单
        QueryWrapper<Order> refundedOrdersWrapper = new QueryWrapper<>();
        refundedOrdersWrapper.between("create_time", startTime, endTime);
        refundedOrdersWrapper.eq("status", "refunded");
        long refundOrders = orderMapper.selectCount(refundedOrdersWrapper);
        result.put("refundOrders", refundOrders);
        
        // 5. 订单趋势
        List<Map<String, Object>> orderTrend = new ArrayList<>();
        List<Double> amountTrend = new ArrayList<>();
        List<Long> orderTrendList = new ArrayList<>();
        LocalDate startDateObj = startTime.toLocalDate();
        LocalDate endDateObj = endTime.toLocalDate();
        for (LocalDate date = startDateObj; !date.isAfter(endDateObj); date = date.plusDays(1)) {
            LocalDateTime dayStart = date.atStartOfDay();
            LocalDateTime dayEnd = date.atTime(LocalTime.MAX);
            
            QueryWrapper<Order> dayOrderWrapper = new QueryWrapper<>();
            dayOrderWrapper.between("create_time", dayStart, dayEnd);
            long orderCount = orderMapper.selectCount(dayOrderWrapper);
            orderTrendList.add(orderCount);
            
            QueryWrapper<Order> daySalesWrapper = new QueryWrapper<>();
            daySalesWrapper.between("create_time", dayStart, dayEnd);
            daySalesWrapper.eq("status", "completed");
            List<Order> dayCompletedOrders = orderMapper.selectList(daySalesWrapper);
            BigDecimal daySales = BigDecimal.ZERO;
            for (Order order : dayCompletedOrders) {
                daySales = daySales.add(order.getTotalPrice());
            }
            amountTrend.add(daySales.doubleValue());
            
            Map<String, Object> dayData = new HashMap<>();
            dayData.put("date", date.toString());
            dayData.put("orderCount", orderCount);
            dayData.put("sales", daySales.doubleValue());
            orderTrend.add(dayData);
        }
        result.put("orderTrend", orderTrendList);
        result.put("amountTrend", amountTrend);
        
        // 6. 订单状态分布
        List<Map<String, Object>> orderStatusDistribution = new ArrayList<>();
        String[] statuses = {"pending", "unshipped", "shipped", "completed", "cancelled", "refunding", "refunded"};
        for (String status : statuses) {
            QueryWrapper<Order> statusWrapper = new QueryWrapper<>();
            statusWrapper.between("create_time", startTime, endTime);
            statusWrapper.eq("status", status);
            long count = orderMapper.selectCount(statusWrapper);
            double percentage = totalOrders > 0 ? (count * 100.0 / totalOrders) : 0;
            
            Map<String, Object> statusData = new HashMap<>();
            statusData.put("status", status);
            statusData.put("count", count);
            statusData.put("percentage", Math.round(percentage * 100) / 100.0);
            orderStatusDistribution.add(statusData);
        }
        result.put("orderStatusDistribution", orderStatusDistribution);
        
        // 7. 最近订单
        QueryWrapper<Order> recentOrdersWrapper = new QueryWrapper<>();
        recentOrdersWrapper.between("create_time", startTime, endTime);
        recentOrdersWrapper.orderByDesc("create_time");
        recentOrdersWrapper.last("LIMIT 10");
        List<Order> recentOrders = orderMapper.selectList(recentOrdersWrapper);
        
        List<Map<String, Object>> recentOrdersList = new ArrayList<>();
        for (Order order : recentOrders) {
            Map<String, Object> orderData = new HashMap<>();
            orderData.put("id", order.getId());
            orderData.put("orderNo", order.getOrderId());
            orderData.put("userName", order.getMemberNickname());
            orderData.put("productCount", order.getTotalQuantity());
            orderData.put("totalAmount", order.getTotalPrice());
            orderData.put("status", order.getStatus());
            orderData.put("createTime", order.getCreateTime());
            recentOrdersList.add(orderData);
        }
        result.put("recentOrders", recentOrdersList);
        
        return result;
    }
}
