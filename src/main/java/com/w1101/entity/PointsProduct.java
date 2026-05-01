package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("points_products")
public class PointsProduct {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String image;

    private Integer points;

    private Integer stock;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
