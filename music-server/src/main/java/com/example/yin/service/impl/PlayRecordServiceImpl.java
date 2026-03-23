package com.example.yin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.R;
import com.example.yin.mapper.PlayRecordMapper;
import com.example.yin.model.domain.PlayRecord;
import com.example.yin.model.request.PlayRecordRequest;
import com.example.yin.service.PlayRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayRecordServiceImpl extends ServiceImpl<PlayRecordMapper, PlayRecord> implements PlayRecordService {

    @Autowired
    private PlayRecordMapper playRecordMapper;

    @Override
    public R addPlayRecord(PlayRecordRequest request) {
        PlayRecord playRecord = new PlayRecord();
        BeanUtils.copyProperties(request, playRecord);
        playRecord.setCreateTime(new Date());
        boolean result = save(playRecord);
        if (result) {
            return R.success("听歌记录添加成功");
        } else {
            return R.error("听歌记录添加失败");
        }
    }

    @Override
    public R getPlayRecordPage(int pageNum, int pageSize, String username, String songName) {
        int startIndex = (pageNum - 1) * pageSize;
        List<PlayRecord> records = playRecordMapper.selectPlayRecordPage(startIndex, pageSize, username, songName);
        int total = playRecordMapper.selectPlayRecordCount(username, songName);

        Map<String, Object> result = new HashMap<>();
        result.put("records", records);
        result.put("total", total);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);

        return R.success("查询成功", result);
    }
}
