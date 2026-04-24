package com.w1101.controller;

import com.w1101.service.StatisticsService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/statistics/sales")
    public Map<String, Object> getSalesStatistics(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        Map<String, Object> statistics = statisticsService.getSalesStatistics(timeRange, startDate, endDate, limit);
        return ResponseUtil.success(statistics);
    }
    
    @GetMapping("/orders/statistics")
    public Map<String, Object> getOrderStatistics(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        Map<String, Object> statistics = statisticsService.getOrderStatistics(timeRange, startDate, endDate);
        return ResponseUtil.success(statistics);
    }
    
    @GetMapping("/orders/status-distribution")
    public Map<String, Object> getOrderStatusDistribution(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        List<Map<String, Object>> distribution = statisticsService.getOrderStatusDistribution(timeRange, startDate, endDate);
        return ResponseUtil.success(distribution);
    }
}
