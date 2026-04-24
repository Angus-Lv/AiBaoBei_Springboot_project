package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("recharge_record")
public class RechargeRecord {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String orderNo;

    private Integer memberId;

    private String memberNickname;

    private BigDecimal amount;

    private BigDecimal bonus;

    private BigDecimal totalAmount;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime completeTime;
}
