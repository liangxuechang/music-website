package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.R;
import com.example.yin.model.domain.PlayRecord;
import com.example.yin.model.request.PlayRecordRequest;

public interface PlayRecordService extends IService<PlayRecord> {

    /**
     * 添加听歌记录
     *
     * @param request 请求参数
     * @return 结果
     */
    R addPlayRecord(PlayRecordRequest request);

    /**
     * 分页查询听歌记录
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param username 用户名（可选）
     * @param songName 歌曲名称（可选）
     * @return 结果
     */
    R getPlayRecordPage(int pageNum, int pageSize, String username, String songName);
}
