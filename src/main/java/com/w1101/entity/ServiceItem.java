package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("service")
public class ServiceItem {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private Double price;
    private String description;
    private Integer duration;
    private String status;
    
    @TableField("create_time")
    private Date createTime;
    
    @TableField("update_time")
    private Date updateTime;
}
