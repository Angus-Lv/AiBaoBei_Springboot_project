package com.w1101.service;

import java.util.Map;

public interface StatisticsService {

    Map<String, Object> getSalesStatistics(String timeRange, String startDate, String endDate);
}
