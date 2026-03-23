package com.example.yin.model.request;

import lombok.Data;

@Data
public class UserListenRecordRequest {
    private Integer userId;
    private String username;
    private Integer songId;
    private String songName;
    private String singerName;
}
