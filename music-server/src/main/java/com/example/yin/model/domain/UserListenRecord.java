package com.example.yin.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@TableName(value = "user_listen_record")
@Data
public class UserListenRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String username;

    private Integer songId;

    private String songName;

    private String singerName;

    private Date listenTime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
