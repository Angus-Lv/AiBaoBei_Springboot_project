package com.w1101.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SystemService {

    Map<String, Object> getSystemConfig();

    boolean updateSystemConfig(Map<String, Object> configData);

    Map<String, Object> uploadImage(MultipartFile file);

    boolean changePassword(String oldPassword, String newPassword);
}
