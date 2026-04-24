package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Order;
import com.w1101.service.OrderService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public Map<String, Object> getOrderList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status) {
        IPage<Order> orderPage = orderService.getOrderList(page, pageSize, keyword, status);

        Map<String, Object> data = new HashMap<>();
        data.put("list", orderPage.getRecords());
        data.put("total", orderPage.getTotal());

        return ResponseUtil.success("success", data);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseUtil.notFound();
        }
        return ResponseUtil.success("获取成功", order);
    }

    @PutMapping("/{id}/status")
    public Map<String, Object> updateOrderStatus(@PathVariable Integer id, @RequestBody Map<String, Object> statusData) {
        String status = (String) statusData.get("status");
        String remark = (String) statusData.get("remark");

        if (status == null) {
            return ResponseUtil.error("状态参数不能为空");
        }

        boolean success = orderService.updateOrderStatus(id, status, remark);
        if (success) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", id);
            data.put("status", status);
            data.put("remark", remark);
            return ResponseUtil.success("更新成功", data);
        }
        return ResponseUtil.error("更新失败");
    }

    @PutMapping("/{id}/cancel")
    public Map<String, Object> cancelOrder(@PathVariable Integer id, @RequestBody Map<String, String> cancelData) {
        String remark = cancelData.get("remark");
        boolean success = orderService.cancelOrder(id, remark);
        if (success) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", id);
            data.put("status", "cancelled");
            data.put("remark", remark);
            return ResponseUtil.success("取消成功", data);
        }
        return ResponseUtil.error("取消失败");
    }

    @PutMapping("/{id}/complete")
    public Map<String, Object> completeOrder(@PathVariable Integer id, @RequestBody Map<String, String> completeData) {
        String remark = completeData.get("remark");
        boolean success = orderService.completeOrder(id, remark);
        if (success) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", id);
            data.put("status", "completed");
            data.put("remark", remark);
            return ResponseUtil.success("完成成功", data);
        }
        return ResponseUtil.error("完成失败");
    }

    @PutMapping("/{id}/ship")
    public Map<String, Object> shipOrder(@PathVariable Integer id, @RequestBody Map<String, String> shipData) {
        String remark = shipData.get("remark");
        boolean success = orderService.shipOrder(id, remark);
        if (success) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", id);
            data.put("status", "shipped");
            data.put("remark", remark);
            return ResponseUtil.success("确认自提成功", data);
        }
        return ResponseUtil.error("确认自提失败");
    }
}
