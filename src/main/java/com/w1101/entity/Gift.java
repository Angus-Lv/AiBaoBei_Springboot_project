package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("gift")
public class Gift {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer points;

    private Integer stock;

    private Integer exchangedCount;

    private String image;

    private String description;

    private String status;

    private LocalDateTime createTime;
}
