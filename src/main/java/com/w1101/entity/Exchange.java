package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("exchange")
public class Exchange {

    @TableId(type = IdType.AUTO)
    private Integer id;
    
    @TableField("order_no")
    private String orderNo;
    
    @TableField("member_id")
    private Integer memberId;
    
    @TableField("member_nickname")
    private String memberNickname;
    
    @TableField("gift_id")
    private Integer giftId;
    
    @TableField("gift_name")
    private String giftName;
    
    @TableField("gift_points")
    private Integer giftPoints;
    
    @TableField("receiver_name")
    private String receiverName;
    
    @TableField("receiver_phone")
    private String receiverPhone;
    
    @TableField("receiver_address")
    private String receiverAddress;
    
    private String status;
    
    @TableField("exchange_time")
    private Date exchangeTime;
    
    @TableField("complete_time")
    private Date completeTime;

    // 以下是为了兼容前端API添加的方法
    public Integer getUserId() {
        return memberId;
    }

    public void setUserId(Integer userId) {
        this.memberId = userId;
    }

    public String getUserName() {
        return memberNickname;
    }

    public void setUserName(String userName) {
        this.memberNickname = userName;
    }

    public Integer getPoints() {
        return giftPoints;
    }

    public void setPoints(Integer points) {
        this.giftPoints = points;
    }
}
