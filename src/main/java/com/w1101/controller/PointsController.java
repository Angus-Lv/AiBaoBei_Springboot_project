package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.PointsRecord;
import com.w1101.service.PointsRecordService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/points")
public class PointsController {

    @Autowired
    private PointsRecordService pointsRecordService;

    @GetMapping("/stats")
    public Map<String, Object> getPointsStats() {
        Map<String, Object> stats = pointsRecordService.getPointsStats();
        return ResponseUtil.success("success", stats);
    }

    @GetMapping("/settings")
    public Map<String, Object> getPointsSettings() {
        Map<String, Object> settings = pointsRecordService.getPointsSettings();
        return ResponseUtil.success("success", settings);
    }

    @PutMapping("/settings")
    public Map<String, Object> updatePointsSettings(@RequestBody Map<String, Object> settingsData) {
        Integer signInPoints = (Integer) settingsData.get("signInPoints");
        Double purchasePointsRate = (Double) settingsData.get("purchasePointsRate");

        boolean success = pointsRecordService.updatePointsSettings(signInPoints, purchasePointsRate);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("更新失败");
    }

    @GetMapping("/records")
    public Map<String, Object> getPointsRecords(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String type) {
        IPage<PointsRecord> pointsRecordPage = pointsRecordService.getPointsRecords(page, pageSize, userId, type);

        Map<String, Object> data = new HashMap<>();
        data.put("list", pointsRecordPage.getRecords());
        data.put("total", pointsRecordPage.getTotal());

        return ResponseUtil.success("success", data);
    }
}
