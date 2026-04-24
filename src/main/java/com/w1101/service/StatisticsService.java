package com.w1101.service;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

    Map<String, Object> getSalesStatistics(String timeRange, String startDate, String endDate, Integer limit);
    
    Map<String, Object> getOrderStatistics(String timeRange, String startDate, String endDate);
    
    List<Map<String, Object>> getOrderStatusDistribution(String timeRange, String startDate, String endDate);
}
