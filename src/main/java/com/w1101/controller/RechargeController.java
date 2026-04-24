package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.RechargeRecord;
import com.w1101.service.RechargeRecordService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/recharge")
public class RechargeController {

    @Autowired
    private RechargeRecordService rechargeRecordService;

    @GetMapping("/stats")
    public Map<String, Object> getRechargeStats() {
        Map<String, Object> stats = rechargeRecordService.getRechargeStats();
        return ResponseUtil.success("success", stats);
    }

    @GetMapping("/records")
    public Map<String, Object> getRechargeRecords(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String status) {
        IPage<RechargeRecord> rechargeRecordPage = rechargeRecordService.getRechargeRecords(page, pageSize, userId, status);

        Map<String, Object> data = new HashMap<>();
        data.put("list", rechargeRecordPage.getRecords());
        data.put("total", rechargeRecordPage.getTotal());

        return ResponseUtil.success("success", data);
    }

    @PutMapping("/records/{id}/cancel")
    public Map<String, Object> cancelRecharge(@PathVariable Integer id) {
        boolean success = rechargeRecordService.cancelRecharge(id);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("取消失败");
    }
}
