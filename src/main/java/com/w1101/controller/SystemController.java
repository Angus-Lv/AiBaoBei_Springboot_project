package com.w1101.controller;

import com.w1101.service.SystemService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @GetMapping("/config")
    public Map<String, Object> getSystemConfig() {
        Map<String, Object> config = systemService.getSystemConfig();
        return ResponseUtil.success("success", config);
    }

    @PutMapping("/config")
    public Map<String, Object> updateSystemConfig(@RequestBody Map<String, Object> configData) {
        boolean success = systemService.updateSystemConfig(configData);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("更新失败");
    }

    @PostMapping("/upload/image")
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = systemService.uploadImage(file);
        return ResponseUtil.success("success", result);
    }

    @PostMapping("/change-password")
    public Map<String, Object> changePassword(@RequestBody Map<String, String> passwordData) {
        String oldPassword = passwordData.get("oldPassword");
        String newPassword = passwordData.get("newPassword");

        boolean success = systemService.changePassword(oldPassword, newPassword);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("修改失败");
    }
}
