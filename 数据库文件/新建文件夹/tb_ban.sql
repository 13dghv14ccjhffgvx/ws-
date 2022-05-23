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

 Date: 14/05/2022 22:35:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_ban
-- ----------------------------
DROP TABLE IF EXISTS `tb_ban`;
CREATE TABLE `tb_ban`  (
  `ban_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `ban_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`ban_id`) USING BTREE,
  UNIQUE INDEX `user_Id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_ban
-- ----------------------------
INSERT INTO `tb_ban` VALUES (7, 3, '2022-05-11 09:28:50');
INSERT INTO `tb_ban` VALUES (8, 6, '2022-05-12 22:19:58');
INSERT INTO `tb_ban` VALUES (15, 7, '2022-05-15 12:50:31');
INSERT INTO `tb_ban` VALUES (25, 5, '2022-05-15 18:53:52');
INSERT INTO `tb_ban` VALUES (31, 11, '2022-05-15 22:16:30');

SET FOREIGN_KEY_CHECKS = 1;
