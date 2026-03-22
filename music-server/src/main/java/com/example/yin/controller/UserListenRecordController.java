package com.example.yin.controller;

import com.example.yin.common.R;
import com.example.yin.model.request.UserListenRecordRequest;
import com.example.yin.service.UserListenRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserListenRecordController {

    @Autowired
    private UserListenRecordService userListenRecordService;

    @PostMapping("/listenRecord/add")
    public R addRecord(@RequestBody UserListenRecordRequest recordRequest) {
        return userListenRecordService.addRecord(recordRequest);
    }

    @GetMapping("/listenRecord/list")
    public R getRecordList(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String songName) {
        return userListenRecordService.getRecordList(username, songName);
    }
}
