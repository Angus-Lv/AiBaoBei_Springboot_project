package com.w1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("banners")
public class Banner {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String image;

    private String linkType;

    private Long linkId;

    private Integer sortOrder;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
