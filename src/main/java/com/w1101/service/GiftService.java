package com.w1101.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.w1101.entity.Gift;

public interface GiftService {

    IPage<Gift> getGiftList(int page, int pageSize, String status);

    java.util.List<Gift> getGifts();

    Gift getGiftById(Integer id);

    boolean addGift(Gift gift);

    boolean updateGift(Gift gift);

    boolean deleteGift(Integer id);
}
