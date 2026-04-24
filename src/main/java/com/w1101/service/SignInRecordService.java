package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.SignInRecord;
import java.util.Map;

public interface SignInRecordService {

    IPage<SignInRecord> getSignInRecordList(int page, int pageSize, Integer memberId, String startDate, String endDate);

    IPage<SignInRecord> getSignInRecords(int page, int pageSize, Integer userId);

    boolean addSignInRecord(SignInRecord signInRecord);

    Map<String, Object> getSignInStats();

    Map<String, Object> getSignInSettings();

    boolean updateSignInSettings(Integer signInPoints, Object continuousSignInBonus);
}
