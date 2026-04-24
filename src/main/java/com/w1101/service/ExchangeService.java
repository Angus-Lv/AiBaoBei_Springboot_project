package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Exchange;

import java.util.Map;

public interface ExchangeService {

    IPage<Exchange> getExchangeRecords(int page, int pageSize, Integer userId, String status);

    boolean completeExchange(Integer id);

    Map<String, Object> getExchangeStats();

    Exchange getExchangeById(Integer id);
}
