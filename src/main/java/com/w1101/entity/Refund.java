package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("refund")
public class Refund {
    private Integer id;
    
    @TableField("order_id")
    private String orderId;
    
    @TableField("member_id")
    private Integer memberId;
    
    @TableField("member_nickname")
    private String memberNickname;
    
    @TableField("refund_amount")
    private BigDecimal refundAmount;
    
    private String reason;
    private String status;
    
    @TableField("create_time")
    private LocalDateTime createTime;
    
    @TableField("process_time")
    private LocalDateTime processTime;
    
    private String remark;
}