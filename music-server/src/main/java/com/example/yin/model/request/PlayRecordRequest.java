package com.example.yin.model.request;

import lombok.Data;

@Data
public class PlayRecordRequest {
    private Integer userId;
    private Integer songId;
    private String songName;
}
