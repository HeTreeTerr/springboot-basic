/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : springbootbasic

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-07-05 19:35:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(75) NOT NULL COMMENT '角色名',
  `NAMEZH` varchar(75) NOT NULL COMMENT '中文名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USERNAME` varchar(75) NOT NULL COMMENT '用户名',
  `NAME` varchar(75) DEFAULT NULL COMMENT '用户姓名',
  `PASSWORD` varchar(75) NOT NULL COMMENT '密码',
  `MOBILENUMBER` varchar(20) DEFAULT NULL COMMENT '手机号',
  `BRITHDAY` date DEFAULT NULL COMMENT '生日',
  `SEX` smallint(2) NOT NULL DEFAULT '1' COMMENT '性别：1男2女',
  `HEADIMGURL` varchar(150) DEFAULT NULL COMMENT '头像',
  `TFADMIN` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否管理员',
  `ENABLED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `LOCKED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定',
  `CREATEUSER` varchar(50) NOT NULL COMMENT '创建者名',
  `CREATEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATEUSER` varchar(50) DEFAULT NULL COMMENT '修改者名',
  `UPDATEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('6', 'hesensen', 'hss', '135E36E40EE942C112520DE8FDAE56902DA80E664A14F9141E36178D', '11111111111', '1996-06-20', '1', 'lala.jpg', '1', '1', '0', 'admin', '2020-06-20 19:01:40', 'admin', '2020-07-04 21:23:04');
INSERT INTO `users` VALUES ('7', 'hss', '何森森', '$2a$10$bvT1NJF4V5b0K6Qgx6eDY.Iqw.c/UNBe/uSJX2yFqpJSuJkcA6Vs.', '18628466845', '2020-07-05', '1', 'lalal.png', '0', '1', '0', 'admin', '2020-07-05 19:28:17', 'admin', '2020-07-05 19:28:17');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USERID` int(11) NOT NULL COMMENT '用户编号',
  `ROLEID` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
