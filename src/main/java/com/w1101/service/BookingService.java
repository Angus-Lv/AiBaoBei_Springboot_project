package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Booking;

import java.util.Map;

public interface BookingService {

    IPage<Booking> getBookingList(int page, int pageSize, String keyword, String startDate, String endDate, String status, String serviceType);

    boolean confirmBooking(Integer id);

    boolean cancelBooking(Integer id);

    Booking getBookingById(Integer id);

    Map<String, Object> submitBooking(Map<String, Object> bookingData);
}
