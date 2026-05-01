package com.w1101.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.w1101.entity.Message;
import com.w1101.mapper.MessageMapper;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageMapper messageMapper;

    @GetMapping
    public Map<String, Object> getMessageList(HttpServletRequest request,
                                               @RequestParam(required = false) String type,
                                               @RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "20") int pageSize) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.and(w -> w.eq("user_id", userId).or().eq("user_id", 0));
        if (type != null && !type.isEmpty()) {
            wrapper.eq("type", type);
        }
        wrapper.orderByDesc("created_at");

        List<Message> messages = messageMapper.selectList(wrapper);

        QueryWrapper<Message> unreadWrapper = new QueryWrapper<>();
        unreadWrapper.and(w -> w.eq("user_id", userId).or().eq("user_id", 0));
        unreadWrapper.eq("is_read", 0);
        long unreadCount = messageMapper.selectCount(unreadWrapper);

        List<Map<String, Object>> list = new ArrayList<>();
        for (Message message : messages) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", message.getId());
            item.put("title", message.getTitle());
            item.put("content", message.getContent());
            item.put("type", message.getType());
            item.put("icon", message.getIcon());
            item.put("isRead", message.getIsRead());
            item.put("createTime", message.getCreatedAt());
            list.add(item);
        }

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, list.size());
        List<Map<String, Object>> pageList = list.subList(start, Math.min(start, list.size()));

        if (start < list.size()) {
            pageList = list.subList(start, end);
        } else {
            pageList = new ArrayList<>();
        }

        Map<String, Object> data = new HashMap<>();
        data.put("list", pageList);
        data.put("total", list.size());
        data.put("unreadCount", unreadCount);
        data.put("page", page);
        data.put("pageSize", pageSize);

        return ResponseUtil.success(data);
    }

    @PutMapping("/{id}/read")
    public Map<String, Object> markAsRead(HttpServletRequest request,
                                           @PathVariable Long id) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        Message message = messageMapper.selectById(id);
        if (message == null) {
            return ResponseUtil.fail("消息不存在");
        }

        if (!message.getUserId().equals(Long.valueOf(userId)) && message.getUserId() != 0) {
            return ResponseUtil.fail("无权操作");
        }

        message.setIsRead(true);
        messageMapper.updateById(message);

        return ResponseUtil.success("标记成功");
    }

    @PutMapping("/read-all")
    public Map<String, Object> markAllAsRead(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        UpdateWrapper<Message> wrapper = new UpdateWrapper<>();
        wrapper.and(w -> w.eq("user_id", userId).or().eq("user_id", 0));
        wrapper.set("is_read", 1);
        messageMapper.update(null, wrapper);

        return ResponseUtil.success("标记成功");
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteMessage(HttpServletRequest request,
                                              @PathVariable Long id) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        Message message = messageMapper.selectById(id);
        if (message == null) {
            return ResponseUtil.fail("消息不存在");
        }

        if (!message.getUserId().equals(Long.valueOf(userId)) && message.getUserId() != 0) {
            return ResponseUtil.fail("无权操作");
        }

        messageMapper.deleteById(id);

        return ResponseUtil.success("删除成功");
    }
}
