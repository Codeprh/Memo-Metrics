/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : memo

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 28/01/2020 10:28:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for task_info
-- ----------------------------
DROP TABLE IF EXISTS `task_info`;
CREATE TABLE `task_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `id_path` varchar(233) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '路径id',
  `describe` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '待办事项描述',
  `status` int(4) DEFAULT NULL COMMENT '待办事项状态:100-完成，-1-删除',
  `sequence` int(11) unsigned NOT NULL DEFAULT '999' COMMENT '排序号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='任务-待办事项表';

SET FOREIGN_KEY_CHECKS = 1;
