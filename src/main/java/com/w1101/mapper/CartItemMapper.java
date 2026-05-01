package com.w1101.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.w1101.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {
}
