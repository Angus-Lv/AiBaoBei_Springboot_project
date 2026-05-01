package com.w1101.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.w1101.entity.Banner;
import com.w1101.mapper.BannerMapper;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerMapper bannerMapper;

    @GetMapping
    public Map<String, Object> getBannerList() {
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        wrapper.orderByAsc("sort_order");
        List<Banner> banners = bannerMapper.selectList(wrapper);

        List<Map<String, Object>> list = new ArrayList<>();
        for (Banner banner : banners) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", banner.getId());
            item.put("title", banner.getTitle());
            item.put("image", banner.getImage());
            item.put("linkType", banner.getLinkType());
            item.put("linkId", banner.getLinkId());
            item.put("sortOrder", banner.getSortOrder());
            list.add(item);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);

        return ResponseUtil.success(data);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getBannerById(@PathVariable Long id) {
        Banner banner = bannerMapper.selectById(id);
        if (banner == null) {
            return ResponseUtil.fail("轮播图不存在");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("id", banner.getId());
        data.put("title", banner.getTitle());
        data.put("image", banner.getImage());
        data.put("linkType", banner.getLinkType());
        data.put("linkId", banner.getLinkId());
        data.put("sortOrder", banner.getSortOrder());
        data.put("status", banner.getStatus());

        return ResponseUtil.success(data);
    }
}
