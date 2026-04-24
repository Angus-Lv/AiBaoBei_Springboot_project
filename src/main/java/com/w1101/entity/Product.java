package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product")
public class Product {
    
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String name;
    
    private String category;
    
    private String image;
    
    private String images;
    
    private BigDecimal price;
    
    private BigDecimal originalPrice;
    
    private Integer stock;
    
    private Integer sales;
    
    private String status;
    
    private Boolean isSeckill;
    
    private Boolean isHot;
    
    private BigDecimal seckillPrice;
    
    private Integer seckillStock;
    
    private String spec;
    
    private String description;
    
    private String detailImages;
    
    private String packages;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
}
