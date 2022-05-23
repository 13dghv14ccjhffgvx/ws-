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

 Date: 14/05/2022 22:36:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_sex` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '田古', '男', '太帅了', '2022-04-15', 2, 'img/2b62f0e2-d072-4728-aa33-b316cc24af9c.jpg');
INSERT INTO `tb_user` VALUES (2, 'nige', 'e10adc3949ba59abbe56e057f20f883e', '田大古', '男', ' 嘿嘿我去饿我去', '2022-04-20', 1, 'img/4f63d65b-31dd-45b0-98e8-fc8a77a2c74a.jpg');
INSERT INTO `tb_user` VALUES (3, 'tian', 'd85f5f874e578e195a73da7e161d647c', '大古', '男', '迪迦', '2022-05-05', 1, 'img/4ed7b71f-d8bf-4410-a5c3-5df56102fd8f.jpg');
INSERT INTO `tb_user` VALUES (4, '213', 'e10adc3949ba59abbe56e057f20f883e', 'li', '', '', '2022-05-06', 1, '');
INSERT INTO `tb_user` VALUES (5, '86', 'e10adc3949ba59abbe56e057f20f883e', 'wei', '', '', '2022-05-06', 1, '');
INSERT INTO `tb_user` VALUES (8, '2222', 'a30179c69a76e3b5fbef8d9339053000', '武器去二无群', '男', '额温枪武器', '2022-05-06', 1, '');
INSERT INTO `tb_user` VALUES (9, 'dagu', 'd85f5f874e578e195a73da7e161d647c', '大古超人', '', '', '2022-05-09', 1, '');
INSERT INTO `tb_user` VALUES (10, '1234', '9cae7919f590409f114a78d9d8d65da0', '大古', '', '', '2022-05-14', 1, '');
INSERT INTO `tb_user` VALUES (11, '1234', 'd85f5f874e578e195a73da7e161d647c', '大古', '', '', '2022-05-14', 1, '');
INSERT INTO `tb_user` VALUES (12, '2134', 'd85f5f874e578e195a73da7e161d647c', '大古', '', '', '2022-05-14', 1, '');
INSERT INTO `tb_user` VALUES (14, '12345', 'd85f5f874e578e195a73da7e161d647c', '小小球', '男', '你好', '2022-05-14', 1, 'img/0f72266c-1ae2-495f-9a2d-a0912b08d367.jpeg');
INSERT INTO `tb_user` VALUES (15, 'admin1', 'd85f5f874e578e195a73da7e161d647c', '派大星', '男', '大家好', '2022-05-14', 1, 'img/a4b5b667-4f6f-46ae-8795-3d6a8433cdd4.jpg');

SET FOREIGN_KEY_CHECKS = 1;
