package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Booking;
import com.w1101.service.BookingService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // 后台管理 - 获取预约列表
    @GetMapping("/admin/bookings/list")
    public Map<String, Object> getBookingList(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String serviceType,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        IPage<Booking> bookingPage = bookingService.getBookingList(page, pageSize, keyword, startDate, endDate, status, serviceType);

        Map<String, Object> data = new java.util.HashMap<>();
        data.put("list", bookingPage.getRecords());
        data.put("total", bookingPage.getTotal());

        return ResponseUtil.success(data);
    }

    // 后台管理 - 确认预约
    @PutMapping("/admin/bookings/{id}/confirm")
    public Map<String, Object> confirmBooking(@PathVariable Integer id) {
        boolean success = bookingService.confirmBooking(id);
        if (success) {
            return ResponseUtil.success("预约确认成功");
        } else {
            return ResponseUtil.error("预约确认失败");
        }
    }

    // 后台管理 - 取消预约
    @PutMapping("/admin/bookings/{id}/cancel")
    public Map<String, Object> cancelBooking(@PathVariable Integer id) {
        boolean success = bookingService.cancelBooking(id);
        if (success) {
            return ResponseUtil.success("预约取消成功");
        } else {
            return ResponseUtil.error("预约取消失败");
        }
    }

    // 后台管理 - 获取预约详情
    @GetMapping("/admin/bookings/{id}")
    public Map<String, Object> getBookingById(@PathVariable Integer id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            return ResponseUtil.success(booking);
        } else {
            return ResponseUtil.error("预约不存在");
        }
    }

    // 前端小程序 - 提交预约
    @PostMapping("/booking")
    public Map<String, Object> submitBooking(@RequestBody Map<String, Object> bookingData) {
        return bookingService.submitBooking(bookingData);
    }
}
