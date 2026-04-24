package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Exchange;
import com.w1101.entity.Gift;
import com.w1101.service.ExchangeService;
import com.w1101.service.GiftService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/point-exchange")
public class PointExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private GiftService giftService;

    @GetMapping("/stats")
    public Map<String, Object> getExchangeStats() {
        Map<String, Object> stats = exchangeService.getExchangeStats();
        return ResponseUtil.success("success", stats);
    }

    @GetMapping("/records")
    public Map<String, Object> getExchangeRecords(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String status) {
        IPage<Exchange> exchangePage = exchangeService.getExchangeRecords(page, pageSize, userId, status);

        Map<String, Object> data = new HashMap<>();
        data.put("list", exchangePage.getRecords());
        data.put("total", exchangePage.getTotal());

        return ResponseUtil.success("success", data);
    }

    @PutMapping("/records/{id}/complete")
    public Map<String, Object> completeExchange(@PathVariable Integer id) {
        boolean success = exchangeService.completeExchange(id);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("完成失败");
    }

    @GetMapping("/gifts")
    public Map<String, Object> getGifts() {
        List<Gift> gifts = giftService.getGifts();
        return ResponseUtil.success("success", gifts);
    }
}
