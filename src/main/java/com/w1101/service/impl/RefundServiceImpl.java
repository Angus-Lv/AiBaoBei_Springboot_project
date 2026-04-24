package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w1101.entity.Refund;
import com.w1101.mapper.RefundMapper;
import com.w1101.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RefundServiceImpl implements RefundService {
    
    @Autowired
    private RefundMapper refundMapper;
    
    @Override
    public Page<Refund> getRefundList(int page, int pageSize, String orderId, String status) {
        Page<Refund> pageInfo = new Page<>(page, pageSize);
        QueryWrapper<Refund> wrapper = new QueryWrapper<>();
        
        if (orderId != null && !orderId.isEmpty()) {
            wrapper.eq("order_id", orderId);
        }
        
        if (status != null && !status.isEmpty()) {
            wrapper.eq("status", status);
        }
        
        wrapper.orderByDesc("create_time");
        return this.refundMapper.selectPage(pageInfo, wrapper);
    }
    
    @Override
    public boolean processRefund(Integer id, String status, String remark) {
        Refund refund = new Refund();
        refund.setId(id);
        refund.setStatus(status);
        refund.setProcessTime(LocalDateTime.now());
        refund.setRemark(remark);
        return this.refundMapper.updateById(refund) > 0;
    }
    
    @Override
    public Refund getRefundById(Integer id) {
        return this.refundMapper.selectById(id);
    }
}