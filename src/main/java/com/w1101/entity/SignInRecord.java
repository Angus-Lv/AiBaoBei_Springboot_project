package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sign_in_record")
public class SignInRecord {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer memberId;

    private String memberNickname;

    private Integer points;

    private String signinDate;

    private LocalDateTime createTime;
}
