package com.w1101.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w1101.entity.Refund;

import java.util.Map;

public interface RefundService {
    /**
     * 获取退款列表
     * @param page 页码
     * @param pageSize 每页大小
     * @param orderId 订单ID
     * @param status 退款状态
     * @return 分页结果
     */
    Page<Refund> getRefundList(int page, int pageSize, String orderId, String status);
    
    /**
     * 处理退款
     * @param id 退款ID
     * @param status 处理状态
     * @param remark 备注
     * @return 是否处理成功
     */
    boolean processRefund(Integer id, String status, String remark);
    
    /**
     * 获取退款详情
     * @param id 退款ID
     * @return 退款详情
     */
    Refund getRefundById(Integer id);
}