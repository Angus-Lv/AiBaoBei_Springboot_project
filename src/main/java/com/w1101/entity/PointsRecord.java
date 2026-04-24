package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("points_record")
public class PointsRecord {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("member_id")
    private Integer memberId;

    @TableField("member_nickname")
    private String memberNickname;

    private Integer points;

    private String type;

    @TableField("type_detail")
    private String typeDetail;

    private Integer balance;

    @TableField("create_time")
    private LocalDateTime createTime;

    private String remark;

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

    public String getDescription() {
        return remark;
    }

    public void setDescription(String description) {
        this.remark = description;
    }

    public LocalDateTime getCreatedAt() {
        return createTime;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createTime = createdAt;
    }
}
