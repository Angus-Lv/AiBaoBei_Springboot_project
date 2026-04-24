package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w1101.entity.Member;
import com.w1101.entity.PointsRecord;
import com.w1101.mapper.MemberMapper;
import com.w1101.mapper.PointsRecordMapper;
import com.w1101.service.PointsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class PointsRecordServiceImpl extends ServiceImpl<PointsRecordMapper, PointsRecord> implements PointsRecordService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public IPage<PointsRecord> getPointsRecordList(int page, int pageSize, Integer memberId, String type, String startDate, String endDate) {
        Page<PointsRecord> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<PointsRecord> wrapper = new LambdaQueryWrapper<>();

        // 会员ID筛选
        if (memberId != null) {
            wrapper.eq(PointsRecord::getMemberId, memberId);
        }

        // 类型筛选
        if (StringUtils.isNotBlank(type)) {
            wrapper.eq(PointsRecord::getType, type);
        }

        // 日期范围筛选
        if (StringUtils.isNotBlank(startDate)) {
            LocalDateTime start = LocalDateTime.parse(startDate + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            wrapper.ge(PointsRecord::getCreateTime, start);
        }
        if (StringUtils.isNotBlank(endDate)) {
            LocalDateTime end = LocalDateTime.parse(endDate + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            wrapper.le(PointsRecord::getCreateTime, end);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(PointsRecord::getCreateTime);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public boolean addPointsRecord(PointsRecord pointsRecord) {
        return this.save(pointsRecord);
    }

    @Override
    @Transactional
    public boolean adjustPoints(Integer memberId, Integer points, String remark) {
        // 获取会员信息
        Member member = memberMapper.selectById(memberId);
        if (member == null) {
            return false;
        }

        // 计算新的积分余额
        int newBalance = member.getPoints() + points;
        if (newBalance < 0) {
            return false;
        }

        // 更新会员积分
        member.setPoints(newBalance);
        memberMapper.updateById(member);

        // 添加积分记录
        PointsRecord pointsRecord = new PointsRecord();
        pointsRecord.setMemberId(memberId);
        pointsRecord.setMemberNickname(member.getNickname());
        pointsRecord.setPoints(points);
        pointsRecord.setType(points > 0 ? "increase" : "decrease");
        pointsRecord.setTypeDetail("adjust");
        pointsRecord.setBalance(newBalance);
        pointsRecord.setCreateTime(LocalDateTime.now());
        pointsRecord.setRemark(remark);

        return this.save(pointsRecord);
    }

    @Override
    public IPage<PointsRecord> getPointsRecords(int page, int pageSize, Integer userId, String type) {
        Page<PointsRecord> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<PointsRecord> wrapper = new LambdaQueryWrapper<>();

        // 用户ID筛选
        if (userId != null) {
            wrapper.eq(PointsRecord::getMemberId, userId);
        }

        // 类型筛选
        if (StringUtils.isNotBlank(type)) {
            wrapper.eq(PointsRecord::getType, type);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(PointsRecord::getCreateTime);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public Map<String, Object> getPointsStats() {
        Map<String, Object> stats = new HashMap<>();

        // 这里需要根据实际情况实现统计逻辑
        // 暂时返回模拟数据
        stats.put("totalPoints", 10000);
        stats.put("usedPoints", 3000);
        stats.put("availablePoints", 7000);

        return stats;
    }

    @Override
    public Map<String, Object> getPointsSettings() {
        Map<String, Object> settings = new HashMap<>();

        // 这里需要根据实际情况从数据库获取设置
        // 暂时返回模拟数据
        settings.put("signInPoints", 10);
        settings.put("purchasePointsRate", 1.0);

        return settings;
    }

    @Override
    public boolean updatePointsSettings(Integer signInPoints, Double purchasePointsRate) {
        // 这里需要根据实际情况更新数据库中的设置
        // 暂时返回true表示更新成功
        return true;
    }
}
