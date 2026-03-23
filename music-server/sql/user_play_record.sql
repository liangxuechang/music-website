-- 用户播放记录表
-- 用于记录用户在什么时间听了什么歌曲

DROP TABLE IF EXISTS `user_play_record`;

CREATE TABLE `user_play_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户ID',
  `song_id` int(10) unsigned NOT NULL COMMENT '歌曲ID',
  `song_name` varchar(45) DEFAULT NULL COMMENT '歌曲名称',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `play_time` datetime NOT NULL COMMENT '播放时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_song_id` (`song_id`),
  KEY `idx_play_time` (`play_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户播放记录表';
