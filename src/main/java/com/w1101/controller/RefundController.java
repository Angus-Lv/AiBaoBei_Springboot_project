package com.w1101.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w1101.entity.Refund;
import com.w1101.service.RefundService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class RefundController {
    
    @Autowired
    private RefundService refundService;
    
    /**
     * 获取退款列表
     * @param page 页码
     * @param pageSize 每页大小
     * @param orderId 订单ID
     * @param status 退款状态
     * @return 退款列表
     */
    @GetMapping("/refunds/list")
    public Map<String, Object> getRefundList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) String orderId,
            @RequestParam(required = false) String status) {
        Page<Refund> refundPage = refundService.getRefundList(page, pageSize, orderId, status);
        Map<String, Object> result = new java.util.HashMap<>();
        result.put("list", refundPage.getRecords());
        result.put("total", refundPage.getTotal());
        return ResponseUtil.success(result);
    }
    
    /**
     * 处理退款
     * @param id 退款ID
     * @param data 处理数据
     * @return 处理结果
     */
    @PutMapping("/refunds/{id}/process")
    public Map<String, Object> processRefund(
            @PathVariable Integer id,
            @RequestBody Map<String, String> data) {
        String status = data.get("status");
        String remark = data.get("remark");
        
        if (status == null) {
            return ResponseUtil.error("状态参数不能为空");
        }
        
        boolean success = refundService.processRefund(id, status, remark);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("处理失败");
    }
    
    /**
     * 获取退款详情
     * @param id 退款ID
     * @return 退款详情
     */
    @GetMapping("/refunds/{id}")
    public Map<String, Object> getRefundById(@PathVariable Integer id) {
        Refund refund = refundService.getRefundById(id);
        if (refund == null) {
            return ResponseUtil.error("退款记录不存在");
        }
        return ResponseUtil.success(refund);
    }
}