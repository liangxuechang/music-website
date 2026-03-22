package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.R;
import com.example.yin.model.domain.UserListenRecord;
import com.example.yin.model.request.UserListenRecordRequest;

public interface UserListenRecordService extends IService<UserListenRecord> {

    R addRecord(UserListenRecordRequest recordRequest);

    R getRecordList(String username, String songName);
}
