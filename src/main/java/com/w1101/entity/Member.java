package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("member")
public class Member {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String nickname;

    private String phone;

    private String vipLevel;

    private Integer points;

    private BigDecimal balance;

    private String status;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    private String avatar;

    // 以下是为了兼容前端API添加的方法
    public Integer getUserId() {
        return id;
    }

    public void setUserId(Integer userId) {
        this.id = userId;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public LocalDateTime getCreatedAt() {
        return createTime;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createTime = createdAt;
    }
}
