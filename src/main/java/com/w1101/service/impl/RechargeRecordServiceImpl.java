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
            wrapper.eq(RechargeRecord::getMemberId, memberId);
        }

        // 状态筛选
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(RechargeRecord::getStatus, status);
        }

        // 日期范围筛选
        if (StringUtils.isNotBlank(startDate)) {
            LocalDateTime start = LocalDateTime.parse(startDate + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            wrapper.ge(RechargeRecord::getCreateTime, start);
        }
        if (StringUtils.isNotBlank(endDate)) {
            LocalDateTime end = LocalDateTime.parse(endDate + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            wrapper.le(RechargeRecord::getCreateTime, end);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(RechargeRecord::getCreateTime);

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
            wrapper.eq(RechargeRecord::getMemberId, userId);
        }

        // 状态筛选
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(RechargeRecord::getStatus, status);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(RechargeRecord::getCreateTime);

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

        // 这里需要根据实际情况实现统计逻辑
        // 暂时返回模拟数据
        stats.put("totalRecharge", 50000.00);
        stats.put("totalUsers", 100);

        return stats;
    }
}
