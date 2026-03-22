-- 用户听歌记录表
-- 用于记录用户在什么时间听了什么歌曲

DROP TABLE IF EXISTS `user_listen_record`;
CREATE TABLE `user_listen_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `song_id` int(10) unsigned NOT NULL COMMENT '歌曲ID',
  `song_name` varchar(255) NOT NULL COMMENT '歌曲名称',
  `singer_name` varchar(255) DEFAULT NULL COMMENT '歌手名称',
  `listen_time` datetime NOT NULL COMMENT '听歌时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_song_id` (`song_id`),
  KEY `idx_listen_time` (`listen_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户听歌记录表';
