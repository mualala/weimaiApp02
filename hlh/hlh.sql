/*
Navicat MySQL Data Transfer

Source Server         : yanghm
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : hlh

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-01-20 18:05:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` varchar(255) NOT NULL,
  `admin_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `logintime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `exitime` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('3beae873207b4a1e93c0f6fe3ed5ce66', 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', '2016-11-07 15:00:37', '2016-10-25 14:51:17');

-- ----------------------------
-- Table structure for banner_pic
-- ----------------------------
DROP TABLE IF EXISTS `banner_pic`;
CREATE TABLE `banner_pic` (
  `ban_id` int(11) NOT NULL AUTO_INCREMENT,
  `pic_id` int(11) DEFAULT NULL COMMENT '图片的位置',
  `ban_pic` varchar(255) DEFAULT NULL COMMENT '图片的名称',
  `type` varchar(255) DEFAULT NULL COMMENT 'banner图的类别，有首页还有论坛',
  `ban_creatime` timestamp NULL DEFAULT NULL COMMENT 'banner图的创建时间',
  `ban_lastmodify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'banner图的最后修改时间',
  PRIMARY KEY (`ban_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banner_pic
-- ----------------------------
INSERT INTO `banner_pic` VALUES ('1', '1', '14848323875342.jpg', '首页', null, '2017-01-19 21:26:28');
INSERT INTO `banner_pic` VALUES ('2', '2', null, '首页', null, '2017-01-19 20:45:52');
INSERT INTO `banner_pic` VALUES ('3', '3', null, '首页', null, '2017-01-19 20:44:36');
INSERT INTO `banner_pic` VALUES ('4', '1', null, '论坛', null, '2017-01-19 20:43:22');
INSERT INTO `banner_pic` VALUES ('5', '2', null, '论坛', null, '2017-01-19 20:43:24');
INSERT INTO `banner_pic` VALUES ('6', '3', null, '论坛', null, '2017-01-19 20:43:26');

-- ----------------------------
-- Table structure for checkcode
-- ----------------------------
DROP TABLE IF EXISTS `checkcode`;
CREATE TABLE `checkcode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkcode
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL DEFAULT '0',
  `phoneNum` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `creatime` timestamp NULL DEFAULT NULL,
  `lastmodifytime` timestamp NULL DEFAULT NULL,
  UNIQUE KEY `phoneNum` (`phoneNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2db4429520a14a5ba7b2a74ea37856ab', 'highgoods@163.com', 'yang', '4QrcOUm6Wau+VuBX8g+IPg==', '2017-01-16 20:26:16', null);
INSERT INTO `user` VALUES ('34618a508cb44a37866c7d6123a3435d', '15680078351', 'yang', '4QrcOUm6Wau+VuBX8g+IPg==', '2017-01-18 17:09:19', null);
