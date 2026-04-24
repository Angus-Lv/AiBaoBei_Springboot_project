package com.w1101.controller;

import com.w1101.utils.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AuthController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        // 简单的认证逻辑（实际项目中应该从数据库验证）
        if ("admin".equals(username) && "admin123".equals(password)) {
            // 生成模拟token（预留JWT但不使用）
            String token = "mock_token_" + System.currentTimeMillis();
            
            Map<String, Object> admin = new HashMap<>();
            admin.put("id", 1);
            admin.put("username", username);
            admin.put("role", "admin");
            
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("admin", admin);
            
            return ResponseUtil.success("登录成功", data);
        } else {
            return ResponseUtil.error(401, "用户名或密码错误");
        }
    }

    @PostMapping("/logout")
    public Map<String, Object> logout() {
        // 由于使用模拟token，logout只需返回成功
        return ResponseUtil.success("登出成功");
    }
}
