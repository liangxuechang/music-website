package com.example.yin.service;

import com.example.yin.common.R;
import com.example.yin.model.request.UserPlayRecordRequest;

public interface UserPlayRecordService {

    R addUserPlayRecord(UserPlayRecordRequest userPlayRecordRequest);

    R allUserPlayRecord();

    R userPlayRecordOfUserId(Integer userId);

    R userPlayRecordOfSongId(Integer songId);

    R deleteUserPlayRecord(Integer id);
}
