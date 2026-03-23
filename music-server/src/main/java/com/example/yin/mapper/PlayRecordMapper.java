package com.example.yin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yin.model.domain.PlayRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayRecordMapper extends BaseMapper<PlayRecord> {

    /**
     * 分页查询听歌记录，支持用户名和歌曲名称筛选
     *
     * @param startIndex 起始索引
     * @param pageSize   每页大小
     * @param username   用户名（可选）
     * @param songName   歌曲名称（可选）
     * @return 听歌记录列表
     */
    List<PlayRecord> selectPlayRecordPage(@Param("startIndex") int startIndex,
                                          @Param("pageSize") int pageSize,
                                          @Param("username") String username,
                                          @Param("songName") String songName);

    /**
     * 查询听歌记录总数，支持用户名和歌曲名称筛选
     *
     * @param username 用户名（可选）
     * @param songName 歌曲名称（可选）
     * @return 总数
     */
    int selectPlayRecordCount(@Param("username") String username,
                              @Param("songName") String songName);
}
