package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w1101.entity.ServiceItem;
import com.w1101.mapper.ServiceItemMapper;
import com.w1101.service.ServiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceItemServiceImpl implements ServiceItemService {

    @Autowired
    private ServiceItemMapper serviceItemMapper;

    @Override
    public IPage<ServiceItem> getServiceList(int page, int pageSize, String keyword, Integer category, String status) {
        Page<ServiceItem> servicePage = new Page<>(page, pageSize);
        QueryWrapper<ServiceItem> queryWrapper = new QueryWrapper<>();

        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.like("name", keyword);
        }

        if (category != null) {
            queryWrapper.eq("type", category);
        }

        if (status != null && !status.isEmpty()) {
            queryWrapper.eq("status", status);
        }

        queryWrapper.orderByDesc("create_time");

        return serviceItemMapper.selectPage(servicePage, queryWrapper);
    }

    @Override
    public boolean addService(ServiceItem service) {
        return serviceItemMapper.insert(service) > 0;
    }

    @Override
    public boolean updateService(ServiceItem service) {
        return serviceItemMapper.updateById(service) > 0;
    }

    @Override
    public boolean updateServiceStatus(Integer id, String status) {
        ServiceItem service = new ServiceItem();
        service.setId(id);
        service.setStatus(status);
        return serviceItemMapper.updateById(service) > 0;
    }

    @Override
    public ServiceItem getServiceById(Integer id) {
        return serviceItemMapper.selectById(id);
    }

    @Override
    public boolean deleteService(Integer id) {
        return serviceItemMapper.deleteById(id) > 0;
    }
}
