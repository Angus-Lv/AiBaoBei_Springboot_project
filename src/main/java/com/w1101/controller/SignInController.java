package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.SignInRecord;
import com.w1101.service.SignInRecordService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/signin")
public class SignInController {

    @Autowired
    private SignInRecordService signInRecordService;

    @GetMapping("/stats")
    public Map<String, Object> getSignInStats() {
        Map<String, Object> stats = signInRecordService.getSignInStats();
        return ResponseUtil.success("success", stats);
    }

    @GetMapping("/settings")
    public Map<String, Object> getSignInSettings() {
        Map<String, Object> settings = signInRecordService.getSignInSettings();
        return ResponseUtil.success("success", settings);
    }

    @PutMapping("/settings")
    public Map<String, Object> updateSignInSettings(@RequestBody Map<String, Object> settingsData) {
        Integer signInPoints = (Integer) settingsData.get("signInPoints");
        Object continuousSignInBonus = settingsData.get("continuousSignInBonus");

        boolean success = signInRecordService.updateSignInSettings(signInPoints, continuousSignInBonus);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("更新失败");
    }

    @GetMapping("/records")
    public Map<String, Object> getSignInRecords(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) Integer userId) {
        IPage<SignInRecord> signInRecordPage = signInRecordService.getSignInRecords(page, pageSize, userId);

        Map<String, Object> data = new HashMap<>();
        data.put("list", signInRecordPage.getRecords());
        data.put("total", signInRecordPage.getTotal());

        return ResponseUtil.success("success", data);
    }
}
