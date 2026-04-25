package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("booking")
public class Booking {

    @TableId(type = IdType.AUTO)
    private Integer id;
    
    @TableField("service_id")
    private Integer serviceId;
    
    @TableField("service_name")
    private String serviceName;
    
    @TableField("member_id")
    private Integer memberId;
    
    @TableField("member_nickname")
    private String memberNickname;
    
    @TableField("member_phone")
    private String memberPhone;
    
    @TableField("baby_name")
    private String babyName;
    
    @TableField("baby_age")
    private String babyAge;
    
    private Date date;
    private String time;
    private String status;
    private String remark;
    
    @TableField("create_time")
    private Date createTime;
    
    // 用于前端展示的字段
    @TableField(exist = false)
    private String bookingNo;
    
    @TableField(exist = false)
    private String memberName;
    
    @TableField(exist = false)
    private String serviceType;
    
    @TableField(exist = false)
    private String serviceTime;
    
    @TableField(exist = false)
    private String bookingTime;
}
