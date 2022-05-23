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

 Date: 14/05/2022 22:35:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_like
-- ----------------------------
DROP TABLE IF EXISTS `tb_like`;
CREATE TABLE `tb_like`  (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `blog_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`like_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 218 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_like
-- ----------------------------
INSERT INTO `tb_like` VALUES (46, 1, 3);
INSERT INTO `tb_like` VALUES (174, 1, 6);
INSERT INTO `tb_like` VALUES (182, 1, 10);
INSERT INTO `tb_like` VALUES (188, 1, 27);
INSERT INTO `tb_like` VALUES (190, 1, 51);
INSERT INTO `tb_like` VALUES (196, 1, 68);
INSERT INTO `tb_like` VALUES (205, 1, 42);
INSERT INTO `tb_like` VALUES (215, 15, 86);
INSERT INTO `tb_like` VALUES (217, 1, 67);

SET FOREIGN_KEY_CHECKS = 1;
