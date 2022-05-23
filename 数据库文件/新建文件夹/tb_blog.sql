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

 Date: 14/05/2022 22:35:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_blog
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog`;
CREATE TABLE `tb_blog`  (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `title` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `like_num` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `release_time` datetime NULL DEFAULT NULL,
  `collect_num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`blog_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog
-- ----------------------------
INSERT INTO `tb_blog` VALUES (42, '输给前端大佬了2131', '', '人工智能', '输', 1, 1, NULL, 0);
INSERT INTO `tb_blog` VALUES (49, '最后一天', '', '人工智能', 'ddl', 0, 3, '2022-05-05 18:13:18', 0);
INSERT INTO `tb_blog` VALUES (50, '123', '', '人工智能', '123', 0, 3, '2022-05-06 00:24:41', 0);
INSERT INTO `tb_blog` VALUES (51, '123', '', '人工智能', '213', 1, 2, '2022-05-06 12:45:03', 0);
INSERT INTO `tb_blog` VALUES (52, '123231', '', '人工智能', '123', 0, 3, '2022-05-06 12:45:23', 0);
INSERT INTO `tb_blog` VALUES (53, '2131', '', '人工智能', '123', 0, 2, '2022-05-06 12:49:01', 0);
INSERT INTO `tb_blog` VALUES (66, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:37', 2);
INSERT INTO `tb_blog` VALUES (67, '最后一天', NULL, '后端', '大碟吧友', 1, 1, '2022-05-11 22:16:37', 0);
INSERT INTO `tb_blog` VALUES (68, '最后一天', NULL, '后端', '大碟吧友', 1, 1, '2022-05-11 22:16:37', 1);
INSERT INTO `tb_blog` VALUES (69, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:37', 1);
INSERT INTO `tb_blog` VALUES (70, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:38', 1);
INSERT INTO `tb_blog` VALUES (71, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:38', 0);
INSERT INTO `tb_blog` VALUES (72, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:38', 1);
INSERT INTO `tb_blog` VALUES (73, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:38', 1);
INSERT INTO `tb_blog` VALUES (74, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:38', 0);
INSERT INTO `tb_blog` VALUES (75, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:38', 0);
INSERT INTO `tb_blog` VALUES (76, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:38', 0);
INSERT INTO `tb_blog` VALUES (77, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:39', 0);
INSERT INTO `tb_blog` VALUES (78, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:39', 0);
INSERT INTO `tb_blog` VALUES (79, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:39', 0);
INSERT INTO `tb_blog` VALUES (80, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:39', 0);
INSERT INTO `tb_blog` VALUES (81, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:39', 0);
INSERT INTO `tb_blog` VALUES (82, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:39', 0);
INSERT INTO `tb_blog` VALUES (83, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:40', 0);
INSERT INTO `tb_blog` VALUES (84, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:40', 0);
INSERT INTO `tb_blog` VALUES (85, '最后一天', NULL, '后端', '大碟吧友', 0, 1, '2022-05-11 22:16:40', 0);
INSERT INTO `tb_blog` VALUES (86, '最后一天', NULL, '后端', '大碟吧友', 1, 1, '2022-05-11 22:16:40', 0);
INSERT INTO `tb_blog` VALUES (115, '12312', '', '人工智能', '123', 0, 2, '2022-05-13 21:10:49', 0);
INSERT INTO `tb_blog` VALUES (116, '333', '', '人工智能', '333', 0, 2, '2022-05-13 21:11:13', 0);
INSERT INTO `tb_blog` VALUES (117, '213', '', '人工智能', '123', 0, 2, '2022-05-13 21:12:31', 0);
INSERT INTO `tb_blog` VALUES (118, '123', '', '人工智能', '123', 0, 2, '2022-05-13 21:13:20', 0);
INSERT INTO `tb_blog` VALUES (119, '111', '', '人工智能', '111', 0, 2, '2022-05-13 21:14:05', 0);
INSERT INTO `tb_blog` VALUES (120, '1111', '', '人工智能', '111', 0, 2, '2022-05-13 21:20:21', 0);
INSERT INTO `tb_blog` VALUES (121, '魔仙城堡', '', '前端', '巴拉巴拉', 0, 2, '2022-05-13 21:33:14', 0);
INSERT INTO `tb_blog` VALUES (123, '你好', '', '人工智能', '你好', 0, 14, '2022-05-14 21:01:33', 0);
INSERT INTO `tb_blog` VALUES (125, '213231213122121312我去饿', '', '人工智能', '213213', 0, 15, '2022-05-14 21:52:32', 0);

SET FOREIGN_KEY_CHECKS = 1;
