package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Order;
import com.w1101.service.OrderService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    public Map<String, Object> updateOrderStatus(@PathVariable Integer id,
                                                 @RequestBody(required = false) Map<String, Object> statusData,
                                                 @RequestParam(value = "status", required = false) String statusParam,
                                                 @RequestParam(value = "remark", required = false) String remarkParam) {
        String status = null;
        String remark = null;
        if (statusData != null) {
            status = (String) statusData.get("status");
            remark = (String) statusData.get("remark");
        }
        // fallback to query params if provided (frontend may send as form or query)
        if (status == null) status = statusParam;
        if (remark == null) remark = remarkParam;

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
    public Map<String, Object> cancelOrder(@PathVariable Integer id,
                                           @RequestBody(required = false) Map<String, String> cancelData,
                                           @RequestParam(value = "remark", required = false) String remarkParam) {
        String remark = null;
        if (cancelData != null) {
            remark = cancelData.get("remark");
        }
        if (remark == null) remark = remarkParam;

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
    public Map<String, Object> completeOrder(@PathVariable Integer id,
                                             @RequestBody(required = false) Map<String, String> completeData,
                                             @RequestParam(value = "remark", required = false) String remarkParam) {
        String remark = null;
        if (completeData != null) {
            remark = completeData.get("remark");
        }
        if (remark == null) remark = remarkParam;

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
    public Map<String, Object> shipOrder(@PathVariable Integer id,
                                         @RequestBody(required = false) Map<String, String> shipData,
                                         @RequestParam(value = "remark", required = false) String remarkParam) {
        String remark = null;
        if (shipData != null) {
            remark = shipData.get("remark");
        }
        if (remark == null) remark = remarkParam;

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

    /**
     * 捕获空请求体或不可读JSON导致的异常，返回一个友好的错误而非 400 原始异常。
     * 这里我们将其转换为一个可读的错误响应（前端可以在不发送 body 的情况下用 query param）。
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        // 记录日志（这里不引入 logger，交由框架日志记录）
        return ResponseUtil.error("请求体不可读或为空，请使用 query 参数或发送正确的 JSON 请求体");
    }

}
