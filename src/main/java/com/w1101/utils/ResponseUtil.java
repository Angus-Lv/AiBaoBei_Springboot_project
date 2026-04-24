package com.w1101.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, Object> success() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "操作成功");
        return response;
    }

    public static Map<String, Object> success(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "操作成功");
        response.put("data", data);
        return response;
    }

    public static Map<String, Object> success(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public static Map<String, Object> error(int code, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);
        return response;
    }

    public static Map<String, Object> error(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 400);
        response.put("message", message);
        return response;
    }

    public static Map<String, Object> unauthorized() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 401);
        response.put("message", "未授权");
        return response;
    }

    public static Map<String, Object> forbidden() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 403);
        response.put("message", "禁止访问");
        return response;
    }

    public static Map<String, Object> notFound() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 404);
        response.put("message", "资源不存在");
        return response;
    }

    public static Map<String, Object> serverError() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 500);
        response.put("message", "服务器内部错误");
        return response;
    }
}
