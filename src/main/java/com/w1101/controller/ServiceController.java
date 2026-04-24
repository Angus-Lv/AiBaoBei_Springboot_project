package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.ServiceItem;
import com.w1101.service.ServiceItemService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/services")
public class ServiceController {

    @Autowired
    private ServiceItemService serviceItemService;

    @GetMapping("/list")
    public Map<String, Object> getServiceList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false) String status) {
        IPage<ServiceItem> servicePage = serviceItemService.getServiceList(page, pageSize, keyword, category, status);

        Map<String, Object> data = new HashMap<>();
        data.put("list", servicePage.getRecords());
        data.put("total", servicePage.getTotal());

        return ResponseUtil.success("success", data);
    }

    @PostMapping
    public Map<String, Object> addService(@RequestBody ServiceItem service) {
        boolean success = serviceItemService.addService(service);
        if (success) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", service.getId());
            return ResponseUtil.success("success", data);
        }
        return ResponseUtil.error("添加失败");
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateService(@PathVariable Integer id, @RequestBody ServiceItem service) {
        service.setId(id);
        boolean success = serviceItemService.updateService(service);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("更新失败");
    }

    @PutMapping("/{id}/status")
    public Map<String, Object> updateServiceStatus(@PathVariable Integer id, @RequestBody Map<String, String> statusData) {
        String status = statusData.get("status");
        if (status == null) {
            return ResponseUtil.error("状态参数不能为空");
        }

        boolean success = serviceItemService.updateServiceStatus(id, status);
        if (success) {
            return ResponseUtil.success("success");
        }
        return ResponseUtil.error("更新失败");
    }
}
