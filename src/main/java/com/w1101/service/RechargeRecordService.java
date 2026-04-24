package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.RechargeRecord;
import java.util.Map;

public interface RechargeRecordService {

    IPage<RechargeRecord> getRechargeRecordList(int page, int pageSize, Integer memberId, String status, String startDate, String endDate);

    IPage<RechargeRecord> getRechargeRecords(int page, int pageSize, Integer userId, String status);

    RechargeRecord getRechargeRecordById(Integer id);

    boolean cancelRecharge(Integer id, String remark);

    boolean cancelRecharge(Integer id);

    Map<String, Object> getRechargeStats();
}
