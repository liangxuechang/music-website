package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.PlayRecordRequest;
import com.example.yin.service.PlayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayRecordController {

    @Autowired
    private PlayRecordService playRecordService;

    /**
     * 添加听歌记录
     * 前台用户播放歌曲时调用
     */
    @PostMapping("/playRecord/add")
    public R addPlayRecord(@RequestBody PlayRecordRequest request) {
        return playRecordService.addPlayRecord(request);
    }

    /**
     * 分页查询听歌记录
     * 后台管理系统查询使用
     */
    @GetMapping("/playRecord/page")
    public R getPlayRecordPage(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize,
                               @RequestParam(required = false) String username,
                               @RequestParam(required = false) String songName) {
        return playRecordService.getPlayRecordPage(pageNum, pageSize, username, songName);
    }
}
