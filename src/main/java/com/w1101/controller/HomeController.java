package com.w1101.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.w1101.entity.*;
import com.w1101.mapper.*;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ServiceItemMapper serviceMapper;

    @Autowired
    private StoreInfoMapper storeInfoMapper;

    @Autowired
    private SeckillProductMapper seckillProductMapper;

    @GetMapping("/home")
    public Map<String, Object> getHomeData() {
        Map<String, Object> data = new HashMap<>();

        QueryWrapper<Banner> bannerWrapper = new QueryWrapper<>();
        bannerWrapper.eq("status", 1);
        bannerWrapper.orderByAsc("sort_order");
        bannerWrapper.last("LIMIT 5");
        List<Banner> banners = bannerMapper.selectList(bannerWrapper);

        List<Map<String, Object>> bannerList = new ArrayList<>();
        for (Banner banner : banners) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", banner.getId());
            item.put("title", banner.getTitle());
            item.put("image", banner.getImage());
            item.put("linkType", banner.getLinkType());
            item.put("linkId", banner.getLinkId());
            bannerList.add(item);
        }
        data.put("banners", bannerList);

        QueryWrapper<SeckillProduct> seckillWrapper = new QueryWrapper<>();
        seckillWrapper.eq("status", 1);
        seckillWrapper.gt("stock", 0);
        seckillWrapper.le("start_time", new java.util.Date());
        seckillWrapper.ge("end_time", new java.util.Date());
        seckillWrapper.last("LIMIT 10");
        List<SeckillProduct> seckillProducts = seckillProductMapper.selectList(seckillWrapper);

        List<Map<String, Object>> seckillList = new ArrayList<>();
        for (SeckillProduct sp : seckillProducts) {
            Product product = productMapper.selectById(sp.getProductId());
            if (product == null) {
                continue;
            }
            Map<String, Object> item = new HashMap<>();
            item.put("id", product.getId());
            item.put("name", product.getName());
            item.put("image", product.getImage());
            item.put("originalPrice", product.getOriginalPrice());
            item.put("currentPrice", sp.getSeckillPrice());
            item.put("sales", product.getSales());
            item.put("stock", sp.getStock());
            seckillList.add(item);
        }
        data.put("seckillProducts", seckillList);

        QueryWrapper<Category> categoryWrapper = new QueryWrapper<>();
        categoryWrapper.eq("parent_id", 0);
        categoryWrapper.orderByAsc("sort_order");
        List<Category> categories = categoryMapper.selectList(categoryWrapper);

        List<Map<String, Object>> categoryList = new ArrayList<>();
        for (Category category : categories) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", category.getId());
            item.put("name", category.getName());
            item.put("icon", category.getIcon());
            categoryList.add(item);
        }
        data.put("categories", categoryList);

        QueryWrapper<Product> hotWrapper = new QueryWrapper<>();
        hotWrapper.eq("is_hot", 1);
        hotWrapper.eq("status", 1);
        hotWrapper.orderByDesc("sales");
        hotWrapper.last("LIMIT 10");
        List<Product> hotProducts = productMapper.selectList(hotWrapper);

        List<Map<String, Object>> hotList = new ArrayList<>();
        for (Product product : hotProducts) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", product.getId());
            item.put("name", product.getName());
            item.put("image", product.getImage());
            item.put("price", product.getPrice());
            item.put("sales", product.getSales());
            hotList.add(item);
        }
        data.put("hotProducts", hotList);

        QueryWrapper<ServiceItem> serviceWrapper = new QueryWrapper<>();
        serviceWrapper.eq("status", 1);
        List<ServiceItem> services = serviceMapper.selectList(serviceWrapper);

        List<Map<String, Object>> serviceList = new ArrayList<>();
        for (ServiceItem service : services) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", service.getId());
            item.put("name", service.getName());
            item.put("icon", service.getIcon());
            item.put("price", service.getPrice());
            String type = service.getType();
            String url = "/pages/service/" + type + "/" + type;
            item.put("url", url);
            serviceList.add(item);
        }
        data.put("services", serviceList);

        QueryWrapper<StoreInfo> storeWrapper = new QueryWrapper<>();
        storeWrapper.last("LIMIT 1");
        StoreInfo storeInfo = storeInfoMapper.selectOne(storeWrapper);

        if (storeInfo != null) {
            Map<String, Object> storeData = new HashMap<>();
            storeData.put("name", storeInfo.getName());
            storeData.put("address", storeInfo.getAddress());
            storeData.put("phone", storeInfo.getPhone());
            storeData.put("businessHours", storeInfo.getBusinessHours());
            data.put("storeInfo", storeData);
        } else {
            Map<String, Object> storeData = new HashMap<>();
            storeData.put("name", "爱宝贝儿孕婴生活馆");
            storeData.put("address", "河南省新乡市马庄乡常兴集村");
            storeData.put("phone", "13273721553");
            storeData.put("businessHours", "07:30-21:00");
            data.put("storeInfo", storeData);
        }

        return ResponseUtil.success(data);
    }

    @GetMapping("/categories")
    public Map<String, Object> getCategories() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        wrapper.orderByAsc("sort_order");
        List<Category> categories = categoryMapper.selectList(wrapper);

        List<Map<String, Object>> list = new ArrayList<>();
        for (Category category : categories) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", category.getId());
            item.put("name", category.getName());
            item.put("icon", category.getIcon());
            list.add(item);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);

        return ResponseUtil.success(data);
    }
}
