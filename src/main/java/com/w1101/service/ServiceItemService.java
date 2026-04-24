package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.ServiceItem;

public interface ServiceItemService {

    IPage<ServiceItem> getServiceList(int page, int pageSize, String keyword, Integer category, String status);

    boolean addService(ServiceItem service);

    boolean updateService(ServiceItem service);

    boolean updateServiceStatus(Integer id, String status);

    ServiceItem getServiceById(Integer id);

    boolean deleteService(Integer id);
}
