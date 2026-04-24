package com.w1101.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Gift;
import com.w1101.service.GiftService;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/gifts")
public class GiftController {

    @Autowired
    private GiftService giftService;

    @GetMapping
    public Map<String, Object> getGiftList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize,
            @RequestParam(required = false) String status) {
        IPage<Gift> giftPage = giftService.getGiftList(page, pageSize, status);

        Map<String, Object> data = new HashMap<>();
        data.put("list", giftPage.getRecords());
        data.put("total", giftPage.getTotal());
        data.put("page", giftPage.getCurrent());
        data.put("pageSize", giftPage.getSize());

        return ResponseUtil.success("获取成功", data);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getGiftById(@PathVariable Integer id) {
        Gift gift = giftService.getGiftById(id);
        if (gift == null) {
            return ResponseUtil.notFound();
        }
        return ResponseUtil.success("获取成功", gift);
    }

    @PostMapping
    public Map<String, Object> addGift(@RequestBody Gift gift) {
        boolean success = giftService.addGift(gift);
        if (success) {
            return ResponseUtil.success("添加成功", gift);
        }
        return ResponseUtil.error("添加失败");
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateGift(@PathVariable Integer id, @RequestBody Gift gift) {
        gift.setId(id);
        boolean success = giftService.updateGift(gift);
        if (success) {
            return ResponseUtil.success("更新成功", gift);
        }
        return ResponseUtil.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteGift(@PathVariable Integer id) {
        boolean success = giftService.deleteGift(id);
        if (success) {
            return ResponseUtil.success("删除成功");
        }
        return ResponseUtil.error("删除失败");
    }
}
