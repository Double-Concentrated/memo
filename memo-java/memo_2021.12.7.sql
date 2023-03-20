/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : memo

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 07/12/2021 17:42:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for memo
-- ----------------------------
DROP TABLE IF EXISTS `memo`;
CREATE TABLE `memo`  (
  `memo_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `status` tinyint(4) NULL DEFAULT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`memo_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of memo
-- ----------------------------
INSERT INTO `memo` VALUES (1, 1, '记得吃早餐', '记得吃早餐', '记得吃早餐', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (2, 1, '记得吃午餐', '记得吃午餐', '记得吃午餐', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (3, 1, '记得吃晚餐', '记得吃晚餐', '记得吃晚餐', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (4, 1, '早点睡觉', '早点睡觉', '早点睡觉', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (5, 1, '好好生活', '好好生活', '好好生活', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (6, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (7, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (8, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (9, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (10, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (11, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (12, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (13, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (14, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (15, 1, 'ROOT', 'ROOT', 'ROOT', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (16, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (17, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (18, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (19, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (20, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (21, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (22, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (23, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (24, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');
INSERT INTO `memo` VALUES (25, 2, 'Test', 'Test', 'Test', 1, '2021-12-07 17:41:42', '2021-12-07 17:41:42');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(5) NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `UK_USERNAME`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', NULL, '63a9f0ea7bb98050796b649e85481845', 1, '2021-12-07 17:41:43', '2021-12-07 17:41:43');

SET FOREIGN_KEY_CHECKS = 1;
