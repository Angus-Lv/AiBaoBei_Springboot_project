package com.w1101.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.w1101.entity.User;
import com.w1101.mapper.UserMapper;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        User user = userMapper.selectById(userId);
        if (user == null) {
            return ResponseUtil.fail("用户不存在");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("nickname", user.getNickname());
        data.put("avatar", user.getAvatar());
        data.put("phone", user.getPhone());
        data.put("vipLevel", user.getVipLevel());
        data.put("points", user.getPoints());
        data.put("balance", user.getBalance());
        data.put("createdAt", user.getCreatedAt());

        return ResponseUtil.success(data);
    }

    @PutMapping("/info")
    public Map<String, Object> updateUserInfo(HttpServletRequest request,
                                               @RequestBody Map<String, String> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        User user = userMapper.selectById(userId);
        if (user == null) {
            return ResponseUtil.fail("用户不存在");
        }

        if (params.containsKey("nickname")) {
            user.setNickname(params.get("nickname"));
        }
        if (params.containsKey("avatar")) {
            user.setAvatar(params.get("avatar"));
        }
        if (params.containsKey("phone")) {
            user.setPhone(params.get("phone"));
        }

        userMapper.updateById(user);

        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("nickname", user.getNickname());
        data.put("avatar", user.getAvatar());

        return ResponseUtil.success(data);
    }

    @PostMapping("/change-password")
    public Map<String, Object> changePassword(HttpServletRequest request,
                                               @RequestBody Map<String, String> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        String oldPassword = params.get("oldPassword");
        String newPassword = params.get("newPassword");

        if (oldPassword == null || newPassword == null) {
            return ResponseUtil.fail("密码不能为空");
        }

        User user = userMapper.selectById(userId);
        if (user == null) {
            return ResponseUtil.fail("用户不存在");
        }

        if (!user.getPassword().equals(oldPassword)) {
            return ResponseUtil.fail("原密码错误");
        }

        user.setPassword(newPassword);
        userMapper.updateById(user);

        return ResponseUtil.success("密码修改成功");
    }
}
