package com.example.yin.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@TableName(value = "play_record")
@Data
public class PlayRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer songId;

    private String songName;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
