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

 Date: 14/05/2022 22:35:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `blog_id` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `comment_time` datetime NULL DEFAULT NULL,
  `parent_comment_id` int(11) NULL DEFAULT NULL,
  `user_nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES (30, 1, 42, '1', '2022-05-04 17:00:16', 0, '田古');
INSERT INTO `tb_comment` VALUES (31, 1, 42, '2', '2022-05-04 17:00:31', 30, '田古');
INSERT INTO `tb_comment` VALUES (35, 3, 42, '2', '2022-05-05 18:13:24', 30, 'tian');
INSERT INTO `tb_comment` VALUES (39, 9, 42, '4', '2022-05-09 17:42:21', 36, NULL);
INSERT INTO `tb_comment` VALUES (41, 1, 42, '4', '2022-05-09 23:23:31', 37, '田古');
INSERT INTO `tb_comment` VALUES (42, 3, 42, '5', '2022-05-10 09:28:26', 39, 'tian');
INSERT INTO `tb_comment` VALUES (43, 1, 42, '222', '2022-05-10 12:19:39', 0, '田古');
INSERT INTO `tb_comment` VALUES (44, 1, 42, '1', '2022-05-10 12:20:03', 43, '田古');
INSERT INTO `tb_comment` VALUES (45, 1, 42, '2', '2022-05-10 12:20:28', 44, '田古');
INSERT INTO `tb_comment` VALUES (46, 1, 42, '2', '2022-05-10 12:20:41', 44, '田古');
INSERT INTO `tb_comment` VALUES (49, 3, 42, '3', '2022-05-10 22:53:14', 35, 'tian');
INSERT INTO `tb_comment` VALUES (50, 1, 42, '511', '2022-05-11 09:00:30', 31, '田古');
INSERT INTO `tb_comment` VALUES (51, 1, 42, '你好吗', '2022-05-11 09:04:03', 30, '田古');
INSERT INTO `tb_comment` VALUES (52, 1, 42, '111', '2022-05-11 09:10:11', 37, '田古');
INSERT INTO `tb_comment` VALUES (53, 1, 42, '1234567', '2022-05-11 09:11:30', 0, '田古');
INSERT INTO `tb_comment` VALUES (54, 1, 60, '123', '2022-05-11 09:14:01', 0, '田古');
INSERT INTO `tb_comment` VALUES (55, 1, 60, '1245', '2022-05-11 09:15:44', 0, '田古');
INSERT INTO `tb_comment` VALUES (56, 1, 42, 'ok', '2022-05-11 09:19:44', 50, '田古');
INSERT INTO `tb_comment` VALUES (57, 1, 42, '1231312', '2022-05-11 10:19:52', 48, '田古');
INSERT INTO `tb_comment` VALUES (58, 1, 42, '哇荡秋千荡秋千', '2022-05-11 10:20:31', 30, '田古');
INSERT INTO `tb_comment` VALUES (59, 1, 68, '123', '2022-05-11 22:17:22', 0, '田古');
INSERT INTO `tb_comment` VALUES (71, 1, 70, '11', '2022-05-12 19:21:18', 0, '田古');
INSERT INTO `tb_comment` VALUES (94, 2, 42, '输', '2022-05-13 21:35:33', 0, '田大古');
INSERT INTO `tb_comment` VALUES (95, 2, 121, '大大', '2022-05-13 21:35:39', 0, '田大古');
INSERT INTO `tb_comment` VALUES (96, 2, 68, '嘎嘎', '2022-05-13 21:35:48', 0, '田大古');
INSERT INTO `tb_comment` VALUES (101, 2, 71, '讷讷', '2022-05-13 21:36:37', 0, '田大古');
INSERT INTO `tb_comment` VALUES (102, 2, 71, '威威', '2022-05-13 21:36:54', 0, '田大古');
INSERT INTO `tb_comment` VALUES (103, 2, 71, '那你', '2022-05-13 21:36:58', 0, '田大古');
INSERT INTO `tb_comment` VALUES (107, 1, 83, 'dada', '2022-05-13 21:45:12', 0, '田古');
INSERT INTO `tb_comment` VALUES (108, 1, 83, '大大', '2022-05-13 21:45:15', 0, '田古');
INSERT INTO `tb_comment` VALUES (109, 1, 83, '喜爱小', '2022-05-13 21:45:21', 108, '田古');
INSERT INTO `tb_comment` VALUES (110, 1, 83, '123', '2022-05-13 21:46:04', 107, '田古');
INSERT INTO `tb_comment` VALUES (111, 1, 83, '123', '2022-05-13 21:46:07', 109, '田古');
INSERT INTO `tb_comment` VALUES (112, 1, 83, '222', '2022-05-13 21:47:12', 110, '田古');
INSERT INTO `tb_comment` VALUES (113, 1, 83, '333', '2022-05-13 21:47:19', 107, '田古');
INSERT INTO `tb_comment` VALUES (120, 1, 66, 'qweeeeeeeeeeeeeeeeeee', '2022-05-14 13:33:47', 0, '田古');
INSERT INTO `tb_comment` VALUES (124, 15, 86, '大', '2022-05-14 21:52:26', 0, '派大星');
INSERT INTO `tb_comment` VALUES (125, 15, 73, '', '2022-05-14 22:04:54', 0, '派大星');
INSERT INTO `tb_comment` VALUES (126, 1, 67, 'wqewq ', '2022-05-14 22:18:07', 0, '田古');
INSERT INTO `tb_comment` VALUES (127, 1, 67, 'wqewq', '2022-05-14 22:18:10', 126, '田古');
INSERT INTO `tb_comment` VALUES (128, 1, 67, 'qwewq', '2022-05-14 22:18:14', 0, '田古');

SET FOREIGN_KEY_CHECKS = 1;
