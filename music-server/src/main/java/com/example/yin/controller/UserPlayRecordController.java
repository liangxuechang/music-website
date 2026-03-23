package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.UserPlayRecordRequest;
import com.example.yin.service.UserPlayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserPlayRecordController {

    @Autowired
    private UserPlayRecordService userPlayRecordService;

    // 添加用户播放记录
    @PostMapping("/userPlayRecord/add")
    public R addUserPlayRecord(@RequestBody UserPlayRecordRequest userPlayRecordRequest) {
        return userPlayRecordService.addUserPlayRecord(userPlayRecordRequest);
    }

    // 返回所有用户播放记录
    @GetMapping("/userPlayRecord")
    public R allUserPlayRecord() {
        return userPlayRecordService.allUserPlayRecord();
    }

    // 返回指定用户ID的播放记录
    @GetMapping("/userPlayRecord/user/detail")
    public R userPlayRecordOfUserId(@RequestParam Integer userId) {
        return userPlayRecordService.userPlayRecordOfUserId(userId);
    }

    // 返回指定歌曲ID的播放记录
    @GetMapping("/userPlayRecord/song/detail")
    public R userPlayRecordOfSongId(@RequestParam Integer songId) {
        return userPlayRecordService.userPlayRecordOfSongId(songId);
    }

    // 删除播放记录
    @DeleteMapping("/userPlayRecord/delete")
    public R deleteUserPlayRecord(@RequestParam Integer id) {
        return userPlayRecordService.deleteUserPlayRecord(id);
    }
}
