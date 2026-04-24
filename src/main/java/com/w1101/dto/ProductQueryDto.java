package com.w1101.dto;

import lombok.Data;

@Data
public class ProductQueryDto {
    
    private Integer page = 1;
    
    private Integer pageSize = 20;
    
    private String category;
    
    private String keyword;
    
    private String status;
    
    private Boolean isHot;
    
    private Boolean isSeckill;
}
