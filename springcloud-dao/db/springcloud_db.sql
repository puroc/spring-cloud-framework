/*
 Navicat Premium Data Transfer

 Source Server         : localhost mysql
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : springcloud_db

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 29/03/2018 17:34:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for org
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of org
-- ----------------------------
BEGIN;
INSERT INTO `org` VALUES (1, '沈阳数融科技有限公司', NULL);
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, '机构管理', NULL, 'org', 'menu', NULL);
INSERT INTO `permission` VALUES (2, '用户管理', NULL, 'user', 'menu', NULL);
INSERT INTO `permission` VALUES (3, '角色管理', NULL, 'role', 'menu', NULL);
INSERT INTO `permission` VALUES (4, '仪表盘', NULL, 'dashboard', 'menu', NULL);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '管理员');
COMMIT;

-- ----------------------------
-- Table structure for role_permission_bind
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_bind`;
CREATE TABLE `role_permission_bind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_permission_role_id` (`role_id`),
  KEY `fk_role_permission_permission_id` (`permission_id`),
  CONSTRAINT `fk_role_permission_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_role_permission_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_permission_bind
-- ----------------------------
BEGIN;
INSERT INTO `role_permission_bind` VALUES (1, 1, 1);
INSERT INTO `role_permission_bind` VALUES (2, 1, 2);
INSERT INTO `role_permission_bind` VALUES (3, 1, 3);
INSERT INTO `role_permission_bind` VALUES (4, 1, 4);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_org_id` (`org_id`),
  CONSTRAINT `fk_user_org_id` FOREIGN KEY (`org_id`) REFERENCES `org` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', 'admin', 'admin', '1981-09-24', '18840640000', '471049000@qq.com', '1', 1);
COMMIT;

-- ----------------------------
-- Table structure for user_role_bind
-- ----------------------------
DROP TABLE IF EXISTS `user_role_bind`;
CREATE TABLE `user_role_bind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_bind_user_id` (`user_id`),
  KEY `fk_user_role_bind_role_id` (`role_id`),
  CONSTRAINT `fk_user_role_bind_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_bind_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role_bind
-- ----------------------------
BEGIN;
INSERT INTO `user_role_bind` VALUES (1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for user_token_bind
-- ----------------------------
DROP TABLE IF EXISTS `user_token_bind`;
CREATE TABLE `user_token_bind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `expire` int(11) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_token_user_id` (`user_id`),
  CONSTRAINT `fk_token_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_token_bind
-- ----------------------------
BEGIN;
INSERT INTO `user_token_bind` VALUES (11, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjIyNDUzOTQzMDksInBheWxvYWQiOiJ7XCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInVzZXJJZFwiOlwiMVwiLFwiY3VycmVudFRpbWVcIjoxNTIyMjQxNzk0MzA5fSJ9.ghIbFyHya4FKYoFlTcGvQSGN_MXgMHcfOPhsuoPTz_8', 3600, '2018-03-28 20:56:34');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
