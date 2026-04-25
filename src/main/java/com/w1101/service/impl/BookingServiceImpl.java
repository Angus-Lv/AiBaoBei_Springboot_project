package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w1101.entity.Booking;
import com.w1101.entity.ServiceItem;
import com.w1101.mapper.BookingMapper;
import com.w1101.mapper.ServiceItemMapper;
import com.w1101.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private ServiceItemMapper serviceItemMapper;

    @Override
    public IPage<Booking> getBookingList(int page, int pageSize, String keyword, String startDate, String endDate, String status, String serviceType) {
        Page<Booking> bookingPage = new Page<>(page, pageSize);
        QueryWrapper<Booking> queryWrapper = new QueryWrapper<>();

        // 关键词搜索（会员姓名、手机号）
        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.and(wrapper -> wrapper
                    .like("member_nickname", keyword)
                    .or()
                    .like("member_phone", keyword)
            );
        }

        // 日期范围
        if (startDate != null && !startDate.isEmpty()) {
            queryWrapper.ge("date", startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            queryWrapper.le("date", endDate);
        }

        // 状态筛选
        if (status != null && !status.isEmpty()) {
            queryWrapper.eq("status", status);
        }

        // 服务类型筛选
        if (serviceType != null && !serviceType.isEmpty()) {
            // 这里需要根据实际情况处理服务类型的映射
            // 暂时不做处理，后续可以根据需要添加
        }

        // 按创建时间倒序排序
        queryWrapper.orderByDesc("create_time");

        IPage<Booking> result = bookingMapper.selectPage(bookingPage, queryWrapper);

        // 处理返回数据，添加前端需要的字段
        result.getRecords().forEach(booking -> {
            booking.setBookingNo("BOOK" + String.format("%06d", booking.getId()));
            booking.setMemberName(booking.getMemberNickname());
            booking.setServiceTime(booking.getDate() + " " + booking.getTime());
            booking.setBookingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(booking.getCreateTime()));
        });

        return result;
    }

    @Override
    public boolean confirmBooking(Integer id) {
        Booking booking = bookingMapper.selectById(id);
        if (booking != null) {
            booking.setStatus("confirmed");
            return bookingMapper.updateById(booking) > 0;
        }
        return false;
    }

    @Override
    public boolean cancelBooking(Integer id) {
        Booking booking = bookingMapper.selectById(id);
        if (booking != null) {
            booking.setStatus("cancelled");
            return bookingMapper.updateById(booking) > 0;
        }
        return false;
    }

    @Override
    public Booking getBookingById(Integer id) {
        Booking booking = bookingMapper.selectById(id);
        if (booking != null) {
            booking.setBookingNo("BOOK" + String.format("%06d", booking.getId()));
            booking.setMemberName(booking.getMemberNickname());
            booking.setServiceTime(booking.getDate() + " " + booking.getTime());
            booking.setBookingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(booking.getCreateTime()));
        }
        return booking;
    }

    @Override
    public Map<String, Object> submitBooking(Map<String, Object> bookingData) {
        Map<String, Object> result = new HashMap<>();

        try {
            // 检查该时段是否已被预约
            String date = (String) bookingData.get("date");
            String time = (String) bookingData.get("time");

            QueryWrapper<Booking> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("date", date);
            queryWrapper.eq("time", time);
            queryWrapper.in("status", "pending", "confirmed");

            if (bookingMapper.selectCount(queryWrapper) > 0) {
                result.put("code", 400);
                result.put("message", "预约失败，该时段已被预约");
                return result;
            }

            // 获取服务信息
            String serviceType = (String) bookingData.get("serviceType");
            QueryWrapper<ServiceItem> serviceWrapper = new QueryWrapper<>();
            serviceWrapper.eq("type", serviceType);
            ServiceItem service = serviceItemMapper.selectOne(serviceWrapper);

            // 创建新预约
            Booking booking = new Booking();
            if (service != null) {
                booking.setServiceId(service.getId());
                booking.setServiceName(service.getName());
            }
            booking.setServiceType(serviceType);
            booking.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            booking.setTime(time);
            booking.setBabyName((String) bookingData.get("babyName"));
            booking.setBabyAge((String) bookingData.get("babyAge"));
            booking.setMemberId(0); // 小程序端预约，设置默认member_id为0
            booking.setMemberNickname((String) bookingData.get("contactName"));
            booking.setMemberPhone((String) bookingData.get("contactPhone"));
            booking.setStatus("pending");
            booking.setCreateTime(new Date());

            // 保存预约
            if (bookingMapper.insert(booking) > 0) {
                result.put("code", 200);
                result.put("message", "预约成功");

                Map<String, Object> data = new HashMap<>();
                data.put("bookingId", booking.getId());
                data.put("serviceType", booking.getServiceType());
                data.put("date", date);
                data.put("time", time);
                data.put("status", booking.getStatus());

                result.put("data", data);
            } else {
                result.put("code", 400);
                result.put("message", "预约失败，请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 400);
            result.put("message", "预约失败，请稍后重试: " + e.getMessage());
        }

        return result;
    }
}
