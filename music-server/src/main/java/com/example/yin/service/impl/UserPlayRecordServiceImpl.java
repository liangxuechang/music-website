package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.R;
import com.example.yin.mapper.ConsumerMapper;
import com.example.yin.mapper.SongMapper;
import com.example.yin.mapper.UserPlayRecordMapper;
import com.example.yin.model.domain.Consumer;
import com.example.yin.model.domain.Song;
import com.example.yin.model.domain.UserPlayRecord;
import com.example.yin.model.request.UserPlayRecordRequest;
import com.example.yin.service.UserPlayRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserPlayRecordServiceImpl extends ServiceImpl<UserPlayRecordMapper, UserPlayRecord> implements UserPlayRecordService {

    @Autowired
    private UserPlayRecordMapper userPlayRecordMapper;

    @Autowired
    private ConsumerMapper consumerMapper;

    @Autowired
    private SongMapper songMapper;

    @Override
    public R addUserPlayRecord(UserPlayRecordRequest userPlayRecordRequest) {
        UserPlayRecord userPlayRecord = new UserPlayRecord();
        BeanUtils.copyProperties(userPlayRecordRequest, userPlayRecord);

        Consumer consumer = consumerMapper.selectById(userPlayRecordRequest.getUserId());
        Song song = songMapper.selectById(userPlayRecordRequest.getSongId());

        if (consumer == null) {
            return R.error("用户不存在");
        }
        if (song == null) {
            return R.error("歌曲不存在");
        }

        userPlayRecord.setUsername(consumer.getUsername());
        userPlayRecord.setSongName(song.getName());
        userPlayRecord.setPlayTime(new Date());

        if (userPlayRecordMapper.insert(userPlayRecord) > 0) {
            return R.success("记录成功");
        } else {
            return R.error("记录失败");
        }
    }

    @Override
    public R allUserPlayRecord() {
        List<UserPlayRecord> list = userPlayRecordMapper.selectList(null);
        return R.success(null, list);
    }

    @Override
    public R userPlayRecordOfUserId(Integer userId) {
        QueryWrapper<UserPlayRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserPlayRecord> list = userPlayRecordMapper.selectList(queryWrapper);
        return R.success(null, list);
    }

    @Override
    public R userPlayRecordOfSongId(Integer songId) {
        QueryWrapper<UserPlayRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id", songId);
        List<UserPlayRecord> list = userPlayRecordMapper.selectList(queryWrapper);
        return R.success(null, list);
    }

    @Override
    public R deleteUserPlayRecord(Integer id) {
        if (userPlayRecordMapper.deleteById(id) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
