package com.example.yin.model.request;

import lombok.Data;

@Data
public class UserPlayRecordRequest {
    private Integer userId;
    private Integer songId;
}
