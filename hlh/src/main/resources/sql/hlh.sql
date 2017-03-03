/*
Navicat MySQL Data Transfer

Source Server         : yang-local
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : hlh

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-03 19:00:00
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
-- Table structure for luntan
-- ----------------------------
DROP TABLE IF EXISTS `luntan`;
CREATE TABLE `luntan` (
  `lt_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `lt_type` int(11) DEFAULT '0' COMMENT '1论坛  2 图文直播',
  `area` varchar(255) DEFAULT NULL COMMENT '发帖选择的地区',
  `lt_content` varchar(255) DEFAULT NULL,
  `pic` varchar(1000) DEFAULT NULL,
  `lt_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`lt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of luntan
-- ----------------------------
INSERT INTO `luntan` VALUES ('24', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 01', '1488273278395-0.jpg,1488273278395-0-1.jpg,', '2017-02-28 17:14:38');
INSERT INTO `luntan` VALUES ('25', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 02', '1488273302254-0.jpg,1488273302254-0-1.png,1488273302254-0-1-2.png,', '2017-02-28 17:15:02');
INSERT INTO `luntan` VALUES ('26', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 03', '1488273369603-0.jpg,', '2017-02-28 17:16:10');
INSERT INTO `luntan` VALUES ('27', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 04', '1488273396266-0.jpg,1488273396266-0-1.jpg,1488273396266-0-1-2.jpg,', '2017-02-28 17:16:36');
INSERT INTO `luntan` VALUES ('28', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 05', '1488273431915-0.jpg,1488273431915-0-1.jpg,1488273431915-0-1-2.jpg,1488273431915-0-1-2-3.jpg,', '2017-02-28 17:17:12');
INSERT INTO `luntan` VALUES ('29', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 06', '1488273464245-0.jpg,1488273464245-0-1.jpg,1488273464245-0-1-2.jpg,1488273464245-0-1-2-3.jpg,', '2017-02-28 17:17:44');
INSERT INTO `luntan` VALUES ('30', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 07', '1488337594112-0.jpg,1488337594112-0-1.jpg,', '2017-03-01 11:06:34');
INSERT INTO `luntan` VALUES ('31', '34618a508cb44a37866c7d6123a3435d', '川菜', '1', '内蒙古通辽市', 'cc 08', '1488337626386-0.jpg,1488337626386-0-1.jpg,1488337626386-0-1-2.jpg,1488337626386-0-1-2-3.jpg,', '2017-03-01 11:07:06');
INSERT INTO `luntan` VALUES ('32', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 08', '1488362843357-0.jpg,1488362843357-0-1.jpg,', '2017-03-01 18:07:23');
INSERT INTO `luntan` VALUES ('33', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 09', '1488362875539-0.jpg,1488362875539-0-1.jpg,', '2017-03-01 18:07:56');
INSERT INTO `luntan` VALUES ('34', '34618a508cb44a37866c7d6123a3435d', '海鲜', '1', '内蒙古通辽市', 'yes 10', '1488362885551-0.jpg,1488362885551-0-1.jpg,', '2017-03-01 18:08:06');
INSERT INTO `luntan` VALUES ('35', '34618a508cb44a37866c7d6123a3435d', '川菜', '1', '内蒙古通辽市', 'sadsa', '1488424532692-0.jpg,1488424532692-0-1.jpg,', '2017-03-02 11:15:33');
INSERT INTO `luntan` VALUES ('37', '34618a508cb44a37866c7d6123a3435d', null, '2', '内蒙古通辽市', 'tw01', '1488439763176-0.jpg,1488439763176-0-1.jpg,', '2017-03-02 15:29:23');
INSERT INTO `luntan` VALUES ('38', '34618a508cb44a37866c7d6123a3435d', null, '2', '内蒙古通辽市', 'tw02', '1488439918879-0.jpg,1488439918879-0-1.jpg,', '2017-03-02 15:31:59');
INSERT INTO `luntan` VALUES ('39', '34618a508cb44a37866c7d6123a3435d', null, '2', '内蒙古通辽市', 'tw03', '1488439934489-0.jpg,1488439934489-0-1.jpg,', '2017-03-02 15:32:14');
INSERT INTO `luntan` VALUES ('40', '34618a508cb44a37866c7d6123a3435d', null, '2', '内蒙古通辽市', 'tw04', '1488439995942-0.jpg,1488439995942-0-1.jpg,', '2017-03-02 15:33:16');
INSERT INTO `luntan` VALUES ('41', '34618a508cb44a37866c7d6123a3435d', null, '2', '内蒙古通辽市', 'tw05', '1488440068539-0.jpg,1488440068539-0-1.jpg,', '2017-03-02 15:34:29');
INSERT INTO `luntan` VALUES ('42', '34618a508cb44a37866c7d6123a3435d', null, '2', '内蒙古通辽市', 'tw06', '1488444449702-0.jpg,1488444449702-0-1.jpg,', '2017-03-02 16:47:30');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  `shop_type` varchar(255) DEFAULT NULL,
  `shop_profile` varchar(255) DEFAULT NULL,
  `shop_pics` varchar(255) DEFAULT NULL COMMENT '店铺的图片',
  `shop_describe` varchar(255) DEFAULT NULL,
  `shop_address` varchar(255) DEFAULT NULL COMMENT '联系人',
  `contacts_name` varchar(255) DEFAULT NULL,
  `contacts_phone` varchar(255) DEFAULT NULL,
  `shop_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '34618a508cb44a37866c7d6123a3435d', 'sadsa', '海鲜', '', null, 'dsa', 'sa', '输入联系人名称dsa', '输入联系人电话', '2017-03-03 18:27:29');
INSERT INTO `shop` VALUES ('2', '34618a508cb44a37866c7d6123a3435d', 'sadsa', '海鲜', '1488536892145.jpg,', null, 'dsa', 'sa', '输入联系人名称dsa', '输入联系人电话', '2017-03-03 18:28:12');
INSERT INTO `shop` VALUES ('3', '34618a508cb44a37866c7d6123a3435d', 'dsadsa', '海鲜', '1488537053805.jpg,', null, '', '', '输入联系人名称', '输入联系人电话', '2017-03-03 18:30:54');
INSERT INTO `shop` VALUES ('4', '34618a508cb44a37866c7d6123a3435d', 'sdad', '风味小吃', '', null, 'aaa', 'sda', '输入联dsad系人名称', '输入联系sa人电话', '2017-03-03 18:41:25');

-- ----------------------------
-- Table structure for shopping_address
-- ----------------------------
DROP TABLE IF EXISTS `shopping_address`;
CREATE TABLE `shopping_address` (
  `addr_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `addr_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`addr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopping_address
-- ----------------------------
INSERT INTO `shopping_address` VALUES ('1', '34618a508cb44a37866c7d6123a3435d', '杨', '12345678910', 'cq', '璧山', '2017-03-03 11:57:10');
INSERT INTO `shopping_address` VALUES ('2', '34618a508cb44a37866c7d6123a3435d', '杨', '123456', '成都', '还是打开金黄色即可电话圣诞节卡号的进口纱角度看撒娇客户端撒UIU盾由于气温IE后好我去基恩的快乐撒娇的你看撒喝多了卡萨激烈的空间撒来看大家撒', '2017-03-03 11:58:19');
INSERT INTO `shopping_address` VALUES ('3', '34618a508cb44a37866c7d6123a3435d', '杨', '123456', '成都', '还是打开金黄色即可电话圣诞节卡号的进口纱角度看撒娇客户端撒UIU盾由于气温IE后好我去基恩的快乐撒娇的你看撒喝多了卡萨激烈的空间撒来看大家撒', '2017-03-03 11:58:19');
INSERT INTO `shopping_address` VALUES ('4', '34618a508cb44a37866c7d6123a3435d', '杨', '123456', '成都', '还是打开金黄色即可电话圣诞节卡号的进口纱角度看撒娇客户端撒UIU盾由于气温IE后好我去基恩的快乐撒娇的你看撒喝多了卡萨激烈的空间撒来看大家撒', '2017-03-03 11:58:19');
INSERT INTO `shopping_address` VALUES ('5', '34618a508cb44a37866c7d6123a3435d', '杨', '123456', '成都', '还是打开金黄色即可电话圣诞节卡号的进口纱角度看撒娇客户端撒UIU盾由于气温IE后好我去基恩的快乐撒娇的你看撒喝多了卡萨激烈的空间撒来看大家撒', '2017-03-03 11:58:19');
INSERT INTO `shopping_address` VALUES ('6', '34618a508cb44a37866c7d6123a3435d', '杨', '123456', '成都', '还是打开金黄色即可电话圣诞节卡号的进口纱角度看撒娇客户端撒UIU盾由于气温IE后好我去基恩的快乐撒娇的你看撒喝多了卡萨激烈的空间撒来看大家撒', '2017-03-03 11:58:19');
INSERT INTO `shopping_address` VALUES ('7', '34618a508cb44a37866c7d6123a3435d', '杨', '123456', '成都', '还是打开金黄色即可电话圣诞节卡号的进口纱角度看撒娇客户端撒UIU盾由于气温IE后好我去基恩的快乐撒娇的你看撒喝多了卡萨激烈的空间撒来看大家撒', '2017-03-03 11:58:19');
INSERT INTO `shopping_address` VALUES ('8', '34618a508cb44a37866c7d6123a3435d', '杨', '123456', '成都', '还是打开金黄色即可电话圣诞节卡号的进口纱角度看撒娇客户端撒UIU盾由于气温IE后好我去基恩的快乐撒娇的你看撒喝多了卡萨激烈的空间撒来看大家撒', '2017-03-03 11:58:19');
INSERT INTO `shopping_address` VALUES ('9', '34618a508cb44a37866c7d6123a3435d', '杨', '123456', '成都', '还是打开金黄色即可电话圣诞节卡号的进口纱角度看撒娇客户端撒UIU盾由于气温IE后好我去基恩的快乐撒娇的你看撒喝多了卡萨激烈的空间撒来看大家撒', '2017-03-03 11:58:19');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL DEFAULT '0',
  `phoneNum` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT '0',
  `age` int(11) DEFAULT '0',
  `gender` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `homeland` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `creatime` timestamp NULL DEFAULT NULL,
  `lastmodifytime` timestamp NULL DEFAULT NULL,
  UNIQUE KEY `phoneNum` (`phoneNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2db4429520a14a5ba7b2a74ea37856ab', 'highgoods@163.com', 'yang', '4QrcOUm6Wau+VuBX8g+IPg==', null, '3', '0', '', null, null, null, null, '2017-01-16 20:26:16', null);
INSERT INTO `user` VALUES ('34618a508cb44a37866c7d6123a3435d', '15680078351', 'acer', '4QrcOUm6Wau+VuBX8g+IPg==', null, '5', '20', '女', '15680078351', 'cq.璧山', 'java', 'yes i do', '2017-01-18 17:09:19', '2017-03-02 18:56:37');
