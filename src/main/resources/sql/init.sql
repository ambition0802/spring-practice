CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT primary key AUTO_INCREMENT NOT NULL COMMENT 'id',
    `name` varchar(64) NOT NULL COMMENT '姓名',
    `age` SMALLINT NOT NULL DEFAULT 0 COMMENT '年龄',
    `city` varchar(64) NOT NULL COMMENT '城市'
);
