package com.w1101.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w1101.entity.Gift;
import com.w1101.mapper.GiftMapper;
import com.w1101.service.GiftService;
import org.springframework.stereotype.Service;

@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements GiftService {

    @Override
    public IPage<Gift> getGiftList(int page, int pageSize, String status) {
        Page<Gift> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Gift> wrapper = new LambdaQueryWrapper<>();

        // 状态筛选
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(Gift::getStatus, status);
        }

        // 按创建时间倒序
        wrapper.orderByDesc(Gift::getCreateTime);

        return this.page(pageInfo, wrapper);
    }

    @Override
    public Gift getGiftById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean addGift(Gift gift) {
        gift.setExchangedCount(0);
        return this.save(gift);
    }

    @Override
    public boolean updateGift(Gift gift) {
        return this.updateById(gift);
    }

    @Override
    public boolean deleteGift(Integer id) {
        return this.removeById(id);
    }

    @Override
    public java.util.List<Gift> getGifts() {
        return this.list();
    }
}
