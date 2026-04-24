package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w1101.entity.SignInRecord;
import com.w1101.mapper.SignInRecordMapper;
import com.w1101.service.SignInRecordService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class SignInRecordServiceImpl extends ServiceImpl<SignInRecordMapper, SignInRecord> implements SignInRecordService {

    @Override
    public IPage<SignInRecord> getSignInRecordList(int page, int pageSize, Integer memberId, String startDate, String endDate) {
        Page<SignInRecord> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<SignInRecord> wrapper = new LambdaQueryWrapper<>();

        // 会员ID筛选
        if (memberId != null) {
            wrapper.eq(SignInRecord::getMemberId, memberId);
        }

        // 日期范围筛选
        if (StringUtils.isNotBlank(startDate)) {
            wrapper.ge(SignInRecord::getSigninDate, startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            wrapper.le(SignInRecord::getSigninDate, endDate);
        }

        // 按签到日期倒序
        wrapper.orderByDesc(SignInRecord::getSigninDate);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public boolean addSignInRecord(SignInRecord signInRecord) {
        return this.save(signInRecord);
    }

    @Override
    public IPage<SignInRecord> getSignInRecords(int page, int pageSize, Integer userId) {
        Page<SignInRecord> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<SignInRecord> wrapper = new LambdaQueryWrapper<>();

        // 用户ID筛选
        if (userId != null) {
            wrapper.eq(SignInRecord::getMemberId, userId);
        }

        // 按签到日期倒序
        wrapper.orderByDesc(SignInRecord::getSigninDate);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public Map<String, Object> getSignInStats() {
        Map<String, Object> stats = new HashMap<>();

        // 这里需要根据实际情况实现统计逻辑
        // 暂时返回模拟数据
        stats.put("totalSignIn", 1000);
        stats.put("activeUsers", 500);

        return stats;
    }

    @Override
    public Map<String, Object> getSignInSettings() {
        Map<String, Object> settings = new HashMap<>();

        // 这里需要根据实际情况从数据库获取设置
        // 暂时返回模拟数据
        settings.put("signInPoints", 10);
        settings.put("continuousSignInBonus", new int[]{5, 10, 15, 20, 25, 30, 50});

        return settings;
    }

    @Override
    public boolean updateSignInSettings(Integer signInPoints, Object continuousSignInBonus) {
        // 这里需要根据实际情况更新数据库中的设置
        // 暂时返回true表示更新成功
        return true;
    }
}
