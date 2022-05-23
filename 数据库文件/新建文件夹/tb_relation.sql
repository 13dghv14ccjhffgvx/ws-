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

 Date: 14/05/2022 22:36:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_relation`;
CREATE TABLE `tb_relation`  (
  `relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `concern_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`relation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_relation
-- ----------------------------
INSERT INTO `tb_relation` VALUES (8, 1, 2);
INSERT INTO `tb_relation` VALUES (10, 3, 2);
INSERT INTO `tb_relation` VALUES (12, 1, 3);
INSERT INTO `tb_relation` VALUES (13, 2, 3);
INSERT INTO `tb_relation` VALUES (14, 3, 1);
INSERT INTO `tb_relation` VALUES (17, 2, 1);
INSERT INTO `tb_relation` VALUES (19, 1, 15);
INSERT INTO `tb_relation` VALUES (20, 15, 1);

SET FOREIGN_KEY_CHECKS = 1;
