package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.PointsRecord;
import java.util.Map;

public interface PointsRecordService {

    IPage<PointsRecord> getPointsRecordList(int page, int pageSize, Integer memberId, String type, String startDate, String endDate);

    IPage<PointsRecord> getPointsRecords(int page, int pageSize, Integer userId, String type);

    boolean addPointsRecord(PointsRecord pointsRecord);

    boolean adjustPoints(Integer memberId, Integer points, String remark);

    Map<String, Object> getPointsStats();

    Map<String, Object> getPointsSettings();

    boolean updatePointsSettings(Integer signInPoints, Double purchasePointsRate);
}
