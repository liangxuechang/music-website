package com.example.yin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yin.model.domain.UserListenRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserListenRecordMapper extends BaseMapper<UserListenRecord> {

    @Select("<script>" +
            "SELECT r.*, c.username as consumer_username FROM user_listen_record r " +
            "LEFT JOIN consumer c ON r.user_id = c.id " +
            "WHERE 1=1 " +
            "<if test='username != null and username != \"\"'>" +
            "AND r.username LIKE CONCAT('%', #{username}, '%') " +
            "</if>" +
            "<if test='songName != null and songName != \"\"'>" +
            "AND r.song_name LIKE CONCAT('%', #{songName}, '%') " +
            "</if>" +
            "ORDER BY r.listen_time DESC " +
            "</script>")
    List<Map<String, Object>> selectRecordList(@Param("username") String username, @Param("songName") String songName);
}
