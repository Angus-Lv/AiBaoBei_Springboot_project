package com.w1101.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.w1101.entity.Address;
import com.w1101.mapper.AddressMapper;
import com.w1101.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressMapper addressMapper;

    @GetMapping
    public Map<String, Object> getAddressList(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.orderByDesc("is_default");
        wrapper.orderByDesc("id");
        List<Address> addresses = addressMapper.selectList(wrapper);

        List<Map<String, Object>> list = new ArrayList<>();
        for (Address address : addresses) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", address.getId());
            item.put("name", address.getName());
            item.put("phone", address.getPhone());
            item.put("province", address.getProvince());
            item.put("city", address.getCity());
            item.put("district", address.getDistrict());
            item.put("detail", address.getDetail());
            item.put("isDefault", address.getIsDefault());
            list.add(item);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);

        return ResponseUtil.success(data);
    }

    @PostMapping
    public Map<String, Object> addAddress(HttpServletRequest request,
                                          @RequestBody Map<String, Object> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        Address address = new Address();
        address.setUserId(Long.valueOf(userId));
        address.setName((String) params.get("name"));
        address.setPhone((String) params.get("phone"));
        address.setProvince((String) params.get("province"));
        address.setCity((String) params.get("city"));
        address.setDistrict((String) params.get("district"));
        address.setDetail((String) params.get("detail"));

        Boolean isDefault = (Boolean) params.get("isDefault");
        if (isDefault != null && isDefault) {
            UpdateWrapper<Address> wrapper = new UpdateWrapper<>();
            wrapper.eq("user_id", userId);
            wrapper.set("is_default", 0);
            addressMapper.update(null, wrapper);
            address.setIsDefault(true);
        } else {
            address.setIsDefault(false);
        }

        addressMapper.insert(address);

        Map<String, Object> data = new HashMap<>();
        data.put("id", address.getId());

        return ResponseUtil.success(data);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateAddress(HttpServletRequest request,
                                              @PathVariable Long id,
                                              @RequestBody Map<String, Object> params) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        Address address = addressMapper.selectById(id);
        if (address == null || !address.getUserId().equals(Long.valueOf(userId))) {
            return ResponseUtil.fail("地址不存在");
        }

        if (params.containsKey("name")) {
            address.setName((String) params.get("name"));
        }
        if (params.containsKey("phone")) {
            address.setPhone((String) params.get("phone"));
        }
        if (params.containsKey("province")) {
            address.setProvince((String) params.get("province"));
        }
        if (params.containsKey("city")) {
            address.setCity((String) params.get("city"));
        }
        if (params.containsKey("district")) {
            address.setDistrict((String) params.get("district"));
        }
        if (params.containsKey("detail")) {
            address.setDetail((String) params.get("detail"));
        }

        Boolean isDefault = (Boolean) params.get("isDefault");
        if (isDefault != null && isDefault) {
            UpdateWrapper<Address> wrapper = new UpdateWrapper<>();
            wrapper.eq("user_id", userId);
            wrapper.set("is_default", 0);
            addressMapper.update(null, wrapper);
            address.setIsDefault(true);
        }

        addressMapper.updateById(address);

        return ResponseUtil.success("更新成功");
    }

    @PutMapping("/{id}/default")
    public Map<String, Object> setDefaultAddress(HttpServletRequest request,
                                                  @PathVariable Long id) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        Address address = addressMapper.selectById(id);
        if (address == null || !address.getUserId().equals(Long.valueOf(userId))) {
            return ResponseUtil.fail("地址不存在");
        }

        UpdateWrapper<Address> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.set("is_default", 0);
        addressMapper.update(null, wrapper);

        address.setIsDefault(true);
        addressMapper.updateById(address);

        return ResponseUtil.success("设置成功");
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteAddress(HttpServletRequest request,
                                              @PathVariable Long id) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseUtil.fail("请先登录");
        }

        Address address = addressMapper.selectById(id);
        if (address == null || !address.getUserId().equals(Long.valueOf(userId))) {
            return ResponseUtil.fail("地址不存在");
        }

        addressMapper.deleteById(id);

        return ResponseUtil.success("删除成功");
    }
}
