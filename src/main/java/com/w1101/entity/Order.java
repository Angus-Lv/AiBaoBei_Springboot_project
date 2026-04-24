package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("order")
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("order_id")
    private String orderId;

    @TableField("member_id")
    private Integer memberId;

    @TableField("member_nickname")
    private String memberNickname;

    @TableField("member_phone")
    private String memberPhone;

    @TableField("total_price")
    private BigDecimal totalPrice;

    @TableField("total_quantity")
    private Integer totalQuantity;

    private String status;

    @TableField("pay_method")
    private String payMethod;

    @TableField("pay_time")
    private LocalDateTime payTime;

    @TableField("receiver_name")
    private String receiverName;

    @TableField("receiver_phone")
    private String receiverPhone;

    @TableField("receiver_address")
    private String receiverAddress;

    private String remark;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    // 以下是为了兼容前端API添加的方法
    public String getOrderNo() {
        return orderId;
    }

    public void setOrderNo(String orderNo) {
        this.orderId = orderNo;
    }

    public String getUserName() {
        return memberNickname;
    }

    public void setUserName(String userName) {
        this.memberNickname = userName;
    }

    public String getUserPhone() {
        return memberPhone;
    }

    public void setUserPhone(String userPhone) {
        this.memberPhone = userPhone;
    }

    public BigDecimal getTotalAmount() {
        return totalPrice;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalPrice = totalAmount;
    }

    public String getRemarks() {
        return remark;
    }

    public void setRemarks(String remarks) {
        this.remark = remarks;
    }
}
