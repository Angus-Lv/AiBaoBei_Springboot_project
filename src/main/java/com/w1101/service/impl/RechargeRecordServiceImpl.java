package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w1101.entity.RechargeRecord;
import com.w1101.mapper.RechargeRecordMapper;
import com.w1101.service.RechargeRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class RechargeRecordServiceImpl extends ServiceImpl<RechargeRecordMapper, RechargeRecord> implements RechargeRecordService {

    @Override
    public IPage<RechargeRecord> getRechargeRecordList(int page, int pageSize, Integer memberId, String status, String startDate, String endDate) {
        Page<RechargeRecord> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<RechargeRecord> wrapper = new LambdaQueryWrapper<>();

        // 会员ID筛选
        if (memberId != null) {
            wrapper.eq(RechargeRecord::getUserId, memberId);
        }

        // 状态筛选
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(RechargeRecord::getStatus, status);
        }

        // 日期范围筛选
        if (StringUtils.isNotBlank(startDate)) {
            LocalDateTime start = LocalDateTime.parse(startDate + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            wrapper.ge(RechargeRecord::getCreatedAt, start);
        }
        if (StringUtils.isNotBlank(endDate)) {
            LocalDateTime end = LocalDateTime.parse(endDate + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            wrapper.le(RechargeRecord::getCreatedAt, end);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(RechargeRecord::getCreatedAt);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public RechargeRecord getRechargeRecordById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean cancelRecharge(Integer id, String remark) {
        RechargeRecord rechargeRecord = this.getById(id);
        if (rechargeRecord == null) {
            return false;
        }

        rechargeRecord.setStatus("cancelled");
        rechargeRecord.setCompleteTime(LocalDateTime.now());
        return this.updateById(rechargeRecord);
    }

    @Override
    public IPage<RechargeRecord> getRechargeRecords(int page, int pageSize, Integer userId, String status) {
        Page<RechargeRecord> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<RechargeRecord> wrapper = new LambdaQueryWrapper<>();

        // 用户ID筛选
        if (userId != null) {
            wrapper.eq(RechargeRecord::getUserId, userId);
        }

        // 状态筛选
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(RechargeRecord::getStatus, status);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(RechargeRecord::getCreatedAt);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public boolean cancelRecharge(Integer id) {
        RechargeRecord rechargeRecord = this.getById(id);
        if (rechargeRecord == null) {
            return false;
        }

        rechargeRecord.setStatus("cancelled");
        rechargeRecord.setCompleteTime(LocalDateTime.now());
        return this.updateById(rechargeRecord);
    }

    @Override
    public Map<String, Object> getRechargeStats() {
        Map<String, Object> stats = new HashMap<>();

        // 统计总充值金额（只统计已完成状态的充值）
        LambdaQueryWrapper<RechargeRecord> completedWrapper = new LambdaQueryWrapper<>();
        completedWrapper.eq(RechargeRecord::getStatus, "completed");
        completedWrapper.select(RechargeRecord::getAmount);
        completedWrapper.select(RechargeRecord::getBonus);
        java.util.List<RechargeRecord> records = this.list(completedWrapper);

        BigDecimal totalRecharge = BigDecimal.ZERO;
        BigDecimal totalBonus = BigDecimal.ZERO;
        for (RechargeRecord record : records) {
            if (record.getAmount() != null) {
                totalRecharge = totalRecharge.add(record.getAmount());
            }
            if (record.getBonus() != null) {
                totalBonus = totalBonus.add(record.getBonus());
            }
        }

        // 统计充值用户数
        LambdaQueryWrapper<RechargeRecord> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(RechargeRecord::getStatus, "completed");
        userWrapper.select(RechargeRecord::getUserId);
        userWrapper.groupBy(RechargeRecord::getUserId);
        long totalUsers = this.count(userWrapper);

        // 统计充值笔数
        LambdaQueryWrapper<RechargeRecord> countWrapper = new LambdaQueryWrapper<>();
        countWrapper.eq(RechargeRecord::getStatus, "completed");
        long rechargeCount = this.count(countWrapper);

        stats.put("totalRecharge", totalRecharge);
        stats.put("totalBonus", totalBonus);
        stats.put("totalAmount", totalRecharge.add(totalBonus));
        stats.put("totalUsers", totalUsers);
        stats.put("rechargeCount", rechargeCount);

        return stats;
    }
}
