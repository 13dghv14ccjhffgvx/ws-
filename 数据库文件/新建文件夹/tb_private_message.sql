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

 Date: 14/05/2022 22:36:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_private_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_private_message`;
CREATE TABLE `tb_private_message`  (
  `private_message_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(5) NULL DEFAULT NULL,
  `receiver_id` int(5) NULL DEFAULT NULL,
  `message` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `belong` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `message_time` datetime NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`private_message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_private_message
-- ----------------------------
INSERT INTO `tb_private_message` VALUES (1, 1, 2, '222', NULL, '1-2', '2022-05-14 22:14:18', 1);
INSERT INTO `tb_private_message` VALUES (59, 2, 3, '尼在', '', '2-3', '2022-05-14 16:20:21', 1);
INSERT INTO `tb_private_message` VALUES (60, 2, 3, '尼在', '', '2-3', '2022-05-14 16:20:23', 1);
INSERT INTO `tb_private_message` VALUES (61, 2, 3, '尼在', '', '2-3', '2022-05-14 16:20:42', 1);
INSERT INTO `tb_private_message` VALUES (62, 2, 3, '尼在', '', '2-3', '2022-05-14 16:20:56', 1);
INSERT INTO `tb_private_message` VALUES (63, 2, 3, '21', '', '2-3', '2022-05-14 16:21:08', 1);
INSERT INTO `tb_private_message` VALUES (64, 2, 3, 'nihaop', '', '2-3', '2022-05-14 16:22:55', 1);
INSERT INTO `tb_private_message` VALUES (65, 2, 3, 'dsedas', '', '2-3', '2022-05-14 16:23:09', 1);
INSERT INTO `tb_private_message` VALUES (68, 2, 3, '21212', '', '2-3', '2022-05-14 16:24:39', 1);
INSERT INTO `tb_private_message` VALUES (69, 2, 3, '222', '', '2-3', '2022-05-14 16:28:16', 1);
INSERT INTO `tb_private_message` VALUES (70, 1, 2, 'gagaga', '', '1-2', '2022-05-14 16:31:06', 1);
INSERT INTO `tb_private_message` VALUES (73, 2, 3, '22121', '', '2-3', '2022-05-14 16:35:53', 1);
INSERT INTO `tb_private_message` VALUES (78, 2, 3, '21321', '', '2-3', '2022-05-14 16:50:41', 1);
INSERT INTO `tb_private_message` VALUES (85, 2, 3, '21221', '', '2-3', '2022-05-14 17:30:07', 1);
INSERT INTO `tb_private_message` VALUES (98, 3, 1, '21321', '', '1-3', '2022-05-14 17:49:31', 1);
INSERT INTO `tb_private_message` VALUES (99, 3, 1, '21321', '', '1-3', '2022-05-14 17:49:44', 1);
INSERT INTO `tb_private_message` VALUES (100, 3, 1, '21321', '', '1-3', '2022-05-14 17:50:11', 1);
INSERT INTO `tb_private_message` VALUES (103, 1, 15, '好吗', '', '1-15', '2022-05-14 21:29:34', 1);
INSERT INTO `tb_private_message` VALUES (107, 1, 2, '3333', NULL, '1-2', '2022-05-14 22:14:43', 1);
INSERT INTO `tb_private_message` VALUES (108, 2, 1, '在吗', '', '1-2', '2022-05-14 22:20:01', 0);
INSERT INTO `tb_private_message` VALUES (109, 2, 1, '在吗', '', '1-2', '2022-05-14 22:20:09', 0);
INSERT INTO `tb_private_message` VALUES (110, 2, 1, '', 'img/8c6ef1b1-8a8e-4da7-bd1e-9d323b5c34fd.jpeg', '1-2', '2022-05-14 22:20:27', 0);
INSERT INTO `tb_private_message` VALUES (111, 2, 1, '21321', 'img/7c2d8cfd-83b0-4b40-8cb7-cb6baf214885.jpeg', '1-2', '2022-05-14 22:22:07', 0);
INSERT INTO `tb_private_message` VALUES (112, 2, 1, '21321', '', '1-2', '2022-05-14 22:22:24', 0);
INSERT INTO `tb_private_message` VALUES (113, 2, 1, '21321', 'img/644d0faa-394e-4f6b-8671-71a41d5dce6c.jpg', '1-2', '2022-05-14 22:22:33', 0);

SET FOREIGN_KEY_CHECKS = 1;
