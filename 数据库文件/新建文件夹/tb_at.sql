/*
 Navicat Premium Data Transfer

 Source Server         : user
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : db_csdn

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 14/05/2022 22:34:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_at
-- ----------------------------
DROP TABLE IF EXISTS `tb_at`;
CREATE TABLE `tb_at`  (
  `at_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NULL DEFAULT NULL,
  `receiver_id` int(11) NULL DEFAULT NULL,
  `message` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `status` int(1) NULL DEFAULT NULL,
  `at_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`at_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_at
-- ----------------------------
INSERT INTO `tb_at` VALUES (1, 1, 2, 'hello', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (2, 1, 2, 'hell', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (3, 1, 2, 'hell', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (4, 1, 2, 'hi', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (5, 1, 2, 'ww', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (6, 3, 2, '奥利给', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (7, 3, 2, '打不牛', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (8, 3, 2, '委屈屈无而且', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (9, 3, 2, '231123', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (12, 2, 1, 'hello你好吗', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (13, 2, 1, '你好吗', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (14, 2, 1, '123213', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (16, 1, 1, '', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (17, 1, 1, 'ni hao ma', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (18, 1, 1, '123', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (19, 1, 1, '123222', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (20, 1, 1, '12322221212121', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (21, 1, 1, '1232222121212121122112', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (22, 1, 1, '你好不好', 1, '2022-05-11 22:58:37');
INSERT INTO `tb_at` VALUES (23, 2, 1, '123456', 1, '2022-05-13 21:51:16');
INSERT INTO `tb_at` VALUES (24, 2, 1, '123', 1, '2022-05-13 22:02:42');
INSERT INTO `tb_at` VALUES (25, 15, 1, '你好', 1, '2022-05-14 21:25:15');
INSERT INTO `tb_at` VALUES (26, 1, 2, 'nihao', 1, '2022-05-14 22:19:20');

SET FOREIGN_KEY_CHECKS = 1;
