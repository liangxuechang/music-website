package com.example.yin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.R;
import com.example.yin.mapper.UserListenRecordMapper;
import com.example.yin.model.domain.UserListenRecord;
import com.example.yin.model.request.UserListenRecordRequest;
import com.example.yin.service.UserListenRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserListenRecordServiceImpl extends ServiceImpl<UserListenRecordMapper, UserListenRecord> implements UserListenRecordService {

    @Autowired
    private UserListenRecordMapper userListenRecordMapper;

    @Override
    public R addRecord(UserListenRecordRequest recordRequest) {
        UserListenRecord record = new UserListenRecord();
        BeanUtils.copyProperties(recordRequest, record);
        record.setListenTime(new Date());
        if (userListenRecordMapper.insert(record) > 0) {
            return R.success("记录成功", true);
        } else {
            return R.error("记录失败");
        }
    }

    @Override
    public R getRecordList(String username, String songName) {
        List<Map<String, Object>> records = userListenRecordMapper.selectRecordList(username, songName);
        return R.success("查询成功", records);
    }
}
