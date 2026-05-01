package com.w1101.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.w1101.entity.Favorite;
import com.w1101.entity.Product;
import com.w1101.mapper.FavoriteMapper;
import com.w1101.mapper.ProductMapper;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public Map<String, Object> getFavoriteList(HttpServletRequest request,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "20") int pageSize) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.orderByDesc("created_at");

        List<Favorite> favorites = favoriteMapper.selectList(wrapper);

        List<Map<String, Object>> list = new ArrayList<>();
        for (Favorite favorite : favorites) {
            Product product = productMapper.selectById(favorite.getProductId());
            if (product == null) {
                continue;
            }

            Map<String, Object> item = new HashMap<>();
            item.put("id", favorite.getId());
            item.put("productId", product.getId());
            item.put("productName", product.getName());
            item.put("productImage", product.getImage());
            item.put("price", product.getPrice());
            item.put("originalPrice", product.getOriginalPrice());
            item.put("sales", product.getSales());
            item.put("createTime", favorite.getCreatedAt());
            list.add(item);
        }

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, list.size());
        List<Map<String, Object>> pageList = list.subList(start, end);

        Map<String, Object> data = new HashMap<>();
        data.put("list", pageList);
        data.put("total", list.size());
        data.put("page", page);
        data.put("pageSize", pageSize);

        return ResponseUtil.success(data);
    }

    @PostMapping
    public Map<String, Object> addFavorite(HttpServletRequest request,
                                             @RequestBody Map<String, Object> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        Long productId = Long.valueOf(params.get("productId").toString());
        Product product = productMapper.selectById(productId);
        if (product == null) {
            return ResponseUtil.fail("商品不存在");
        }

        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("product_id", productId);
        Favorite existFavorite = favoriteMapper.selectOne(wrapper);

        if (existFavorite != null) {
            return ResponseUtil.fail("已收藏过该商品");
        }

        Favorite favorite = new Favorite();
        favorite.setUserId(Long.valueOf(userId));
        favorite.setProductId(productId);
        favoriteMapper.insert(favorite);

        Map<String, Object> data = new HashMap<>();
        data.put("favoriteId", favorite.getId());

        return ResponseUtil.success(data);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> removeFavorite(HttpServletRequest request,
                                               @PathVariable Long id) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        Favorite favorite = favoriteMapper.selectById(id);
        if (favorite == null || !favorite.getUserId().equals(Long.valueOf(userId))) {
            return ResponseUtil.fail("收藏记录不存在");
        }

        favoriteMapper.deleteById(id);

        return ResponseUtil.success("取消收藏成功");
    }

    @DeleteMapping("/batch-delete")
    public Map<String, Object> batchRemoveFavorite(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        @SuppressWarnings("unchecked")
        List<Integer> ids = (List<Integer>) params.get("ids");
        if (ids == null || ids.isEmpty()) {
            return ResponseUtil.fail("请选择要取消收藏的商品");
        }

        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.in("id", ids);
        favoriteMapper.delete(wrapper);

        return ResponseUtil.success("取消收藏成功");
    }
}
