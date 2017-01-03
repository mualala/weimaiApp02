/*
Navicat MySQL Data Transfer

Source Server         : yanghm
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : schoolapp

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-01-03 20:19:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for active
-- ----------------------------
DROP TABLE IF EXISTS `active`;
CREATE TABLE `active` (
  `active_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `type_a` varchar(255) DEFAULT NULL COMMENT '首页动态大类，校园生活、工作生活、秀一秀等',
  `type_b` varchar(255) DEFAULT NULL COMMENT '用于扩展分类',
  `saysay` varchar(255) DEFAULT NULL COMMENT '发表说说的内容',
  `active_pic` varchar(9000) DEFAULT NULL,
  `docum` varchar(9000) DEFAULT NULL COMMENT '上传的资源文件',
  `docum_size` varchar(500) DEFAULT '0' COMMENT '每个文件的大小',
  `state` varchar(255) DEFAULT '0' COMMENT '状态数据的状态：0=审核中，1=审核通过，2=审核不通过',
  `see` int(11) DEFAULT '0' COMMENT '查看',
  `doc_down_count` varchar(500) DEFAULT '0' COMMENT '文件下载次数',
  `active_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`active_user_id`),
  FULLTEXT KEY `saysay` (`saysay`)
) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of active
-- ----------------------------
INSERT INTO `active` VALUES ('208', '181d4d3239e449b2938821243327048c', '校园生活#', '', 'its ok，yeah，ok', '1480560551417-0.jpg,1480560551417-0-1.jpg,', null, '0', '1', '2', '0', '2016-12-13 10:49:11');
INSERT INTO `active` VALUES ('209', '181d4d3239e449b2938821243327048c', '校园生活#', '', '摩擦摩擦', '1480560566732-0.jpg,1480560566732-0-1.jpg,1480560566732-0-1-2.jpg,', '__shiro-all-1.2.3 (1).jar,__spring-web.zip,', '523KB,11KB,', '1', '0', '5,2,', '2016-11-27 10:49:27');
INSERT INTO `active` VALUES ('210', '181d4d3239e449b2938821243327048c', '秀一秀#', '', 'its ok，yeah，im ok!', '1480560579196-0.jpg,1480560579196-0-1.jpg,', null, '0', '1', '2', '0', '2016-12-07 10:49:39');
INSERT INTO `active` VALUES ('211', '6340428319bc4b0db3446725634f369b', '校园生活#', '', '海鸟跟鱼相爱', '1480560596388-0.jpg,1480560596388-0-1.jpg,', null, '0', '1', '1', '0', '2016-05-18 10:49:56');
INSERT INTO `active` VALUES ('212', '6340428319bc4b0db3446725634f369b', '秀一秀#', '', '海鸟跟鱼相爱', '1480560614368-0.jpg,', null, '0', '1', '1', '0', '2016-04-20 10:50:14');
INSERT INTO `active` VALUES ('213', '02312dwqewq1231', '校园生活#', '', '海鸟跟鱼相爱', '1480560630030-0.jpg,', null, '0', '1', '1', '0', '2016-12-05 10:50:30');
INSERT INTO `active` VALUES ('214', '02312dwqewq1231', '资源分享#', '', '', '1480560647990-0.jpg,1480560647990-0-1.jpg,', null, '0', '1', '1', '0', '2016-06-21 10:50:48');
INSERT INTO `active` VALUES ('215', '02312dwqewq1231', '毕业结伴#', '', 'its ok，yeah，ok', '1480560662382-0.jpg,1480560662382-0-1.jpg,', null, '0', '1', '1', '0', '2016-03-30 10:51:02');
INSERT INTO `active` VALUES ('216', '02312dwqewq1232', '资源分享#', '', '', '1480560677624-0.jpg,1480560677624-0-1.jpg,', null, '0', '1', '3', '0', '2016-11-22 10:51:18');
INSERT INTO `active` VALUES ('217', '02312dwqewq1232', '校园生活#', '', 'its ok，yeah，ok', '1480560689925-0.jpg,1480560689925-0-1.jpg,', null, '0', '1', '1', '0', '2016-12-01 10:51:30');
INSERT INTO `active` VALUES ('218', '02312dwqewq1232', '秀一秀#', '', '', '1480560712179-0.jpg,1480560712179-0-1.jpg,', null, '0', '1', '2', '0', '2016-12-01 10:51:52');
INSERT INTO `active` VALUES ('219', '02312dwqewq1233', '毕业结伴#', '', '摩擦摩擦', '1480560728952-0.jpg,1480560728952-0-1.jpg,', null, '0', '1', '1', '0', '2016-12-01 10:52:09');
INSERT INTO `active` VALUES ('220', '02312dwqewq1233', '校园生活#', '', '摩擦摩擦', '1480560740820-0.jpg,', null, '0', '1', '2', '0', '2016-12-01 10:52:21');
INSERT INTO `active` VALUES ('221', '02312dwqewq1233', '资源分享#', '', 'its ok，yeah，ok', '1480560757699-0.jpg,1480560757699-0-1.jpg,1480560757699-0-1-2.jpg,', null, '0', '1', '2', '0', '2016-11-07 10:52:38');
INSERT INTO `active` VALUES ('222', '02312dwqewq1238', '秀一秀#', '', '摩擦摩擦', '1480560775230-0.jpg,', null, '0', '1', '1', '0', '2016-11-30 10:52:55');
INSERT INTO `active` VALUES ('223', '02312dwqewq1238', '资源分享#', '', '摩擦摩擦', '1480560787555-0.jpg,1480560787555-0-2.jpg,', null, '0', '1', '1', '0', '2016-11-17 10:53:08');
INSERT INTO `active` VALUES ('224', '02312dwqewq1238', '秀一秀#', '', '摩擦摩擦', '1480560800657-0.jpg,', null, '0', '1', '1', '0', '2016-11-14 10:53:21');
INSERT INTO `active` VALUES ('225', '181d4d3239e449b2938821243327048c', '校园生活#', '', '摩擦摩擦', '1480577220268-0.jpg,1480577220268-0-1.jpg,1480577220268-0-1-2.jpg,1480577220268-0-1-2-3.jpg,1480577220268-0-1-2-3-4.jpg,1480577220268-0-1-2-3-4-5.jpg,', null, '0', '1', '1', '0', '2016-12-02 15:27:00');
INSERT INTO `active` VALUES ('226', '181d4d3239e449b2938821243327048c', '校园生活#', '', '', '1480668106008-0.jpg,1480668106008-0-1.jpg,1480668106008-0-1-2.jpg,1480668106008-0-1-2-3.gif,1480668106008-0-1-2-3-4.jpg,1480668106008-0-1-2-3-4-5.jpg,1480668106008-0-1-2-3-4-5-6.jpg,1480668106008-0-1-2-3-4-5-6-7.jpg,', null, '0', '1', '0', '0', '2016-12-02 16:41:46');
INSERT INTO `active` VALUES ('227', '02312dwqewq1232', '校园生活#', null, '说你为什么', '1480668106008-0.jpg,1480668106008-0-1.jpg,1480668106008-0-1-2.jpg,', null, '0', '0', '0', '0', '2016-12-06 15:48:23');
INSERT INTO `active` VALUES ('228', '02312dwqewq1231', '校园生活#', null, '不要相约来世见', '1480560787555-0.jpg,1480560787555-0-2.jpg,', null, '0', '0', '0', '0', '2016-12-01 15:48:52');
INSERT INTO `active` VALUES ('233', '02312dwqewq1233', '校园生活#', '', '伞外朦胧可是你', '1481075781899-0.jpg,1481075781899-0-1.jpg,1481075781899-0-1-2.jpg,', null, '0', '0', '0', '0', '2016-12-07 09:56:22');
INSERT INTO `active` VALUES ('234', '02312dwqewq1235', '校园生活#', '', 'can you feel my world', '1481075805790-0.jpg,1481075805790-0-1.jpg,1481075805790-0-1-2.jpg,1481075805790-0-1-2-7.gif,', null, '0', '0', '0', '0', '2016-12-07 09:56:46');
INSERT INTO `active` VALUES ('235', '02312dwqewq1236', '校园生活#', '', '最假的是眼泪,最美的是誓言', '1481075840357-0.jpg,1481075840357-0-1.jpg,1481075840357-0-1-2.jpg,1481075840357-0-1-2-3.jpg,1481075840357-0-1-2-3-4.jpg,1481075840357-0-1-2-3-4-5.jpg,1481075840357-0-1-2-3-4-5-6.jpg,', null, '0', '0', '0', '0', '2016-12-07 09:57:20');
INSERT INTO `active` VALUES ('236', '02312dwqewq1238', '校园生活#', '', '伞外朦胧可是你', '1481075866197-0.jpg,1481075866197-0-4.jpg,1481075866197-0-4-5.jpg,1481075866197-0-4-5-6.jpg,', null, '0', '0', '0', '0', '2016-12-07 09:57:46');
INSERT INTO `active` VALUES ('237', '02312dwqewq1233', '校园生活#', '', '伞外朦胧可是你', '1481075896344-0.jpg,1481075896344-0-1.jpg,1481075896344-0-1-2.jpg,', null, '0', '0', '0', '0', '2016-12-07 09:58:16');
INSERT INTO `active` VALUES ('238', '6340428319bc4b0db3446725634f369b', '毕业结伴#', '', '说说吧', '1481270522749-0.jpg,1481270522749-0-1.jpg,1481270522749-0-1-2.jpg,', null, '0', '0', '0', '0', '2016-12-09 16:02:03');
INSERT INTO `active` VALUES ('239', '181d4d3239e449b2938821243327048c', '毕业结伴#', '', '那些无法的改变就在放下举起间', '1481270630518-0.jpg,1481270630518-0-1.jpg,1481270630518-0-1-2.jpg,1481270630518-0-1-2-3.jpg,', null, '0', '1', '1', '0', '2016-12-09 16:03:51');
INSERT INTO `active` VALUES ('240', '181d4d3239e449b2938821243327048c', '校园生活#', '', '', '1481508789315-0.jpg,', null, '0', '0', '0', '0', '2016-12-12 10:13:09');
INSERT INTO `active` VALUES ('241', '181d4d3239e449b2938821243327048c', '校园生活#', '', '', '1481508853147-0.jpg,', null, '0', '0', '0', '0', '2016-12-12 10:14:13');
INSERT INTO `active` VALUES ('242', '6340428319bc4b0db3446725634f369b', '校园生活#', '', '', '1481508948649-0.jpg,', null, '0', '0', '0', '0', '2016-12-12 10:15:49');
INSERT INTO `active` VALUES ('243', '6340428319bc4b0db3446725634f369b', '校园生活#', '', '', '1481509036499-0.jpg,', null, '0', '0', '0', '0', '2016-12-12 10:17:17');
INSERT INTO `active` VALUES ('248', '181d4d3239e449b2938821243327048c', '校园生活#', '', '', '', null, '0', '1', '0', '0', '2016-12-15 17:59:28');
INSERT INTO `active` VALUES ('249', '2b83723b61cb463bbd8e7754b43be123', '工作生活#', null, 'zzzzzzzzzzzzzzzzzzzzzzzzz', '', null, '0', '1', '0', '0', '2016-12-15 20:50:38');
INSERT INTO `active` VALUES ('250', '181d4d3239e449b2938821243327048c', '校园生活#', null, 'zzzzzzzzzzz', '', null, '0', '0', '0', '0', '2016-12-16 15:04:01');
INSERT INTO `active` VALUES ('251', '181d4d3239e449b2938821243327048c', '校园生活#', null, 'zzzzzzzzzzzzzzzzzzzzzzz', '', null, '0', '0', '0', '0', '2016-12-16 15:04:17');
INSERT INTO `active` VALUES ('252', '181d4d3239e449b2938821243327048c', '校园生活#', null, '', '', null, '0', '0', '0', '0', '2016-12-16 15:06:06');
INSERT INTO `active` VALUES ('253', '181d4d3239e449b2938821243327048c', '校园生活#', null, '', '', null, '0', '0', '0', '0', '2016-12-16 15:06:44');
INSERT INTO `active` VALUES ('254', '181d4d3239e449b2938821243327048c', '校园生活#', null, '', '', null, '0', '0', '0', '0', '2016-12-16 15:18:34');
INSERT INTO `active` VALUES ('255', '181d4d3239e449b2938821243327048c', '校园生活#', null, 'zzzzzzzzzzzzzzzzz', '1481873010282-0.jpg,', null, '0', '0', '0', '0', '2016-12-16 15:23:30');
INSERT INTO `active` VALUES ('256', '181d4d3239e449b2938821243327048c', '校园生活#', null, '456454564654654', '1481873086212-0.jpg,', null, '0', '0', '0', '0', '2016-12-16 15:24:46');
INSERT INTO `active` VALUES ('257', '6340428319bc4b0db3446725634f369b', '旅行游记#', null, '你说', '1481873910309-0.jpg,1481873910309-0-1.jpg,1481873910309-0-1-2.jpeg,', null, '0', '0', '0', '0', '2016-12-16 15:38:30');
INSERT INTO `active` VALUES ('258', '6340428319bc4b0db3446725634f369b', '旅行游记#', null, '', '', null, '0', '0', '0', '0', '2016-12-16 15:39:15');
INSERT INTO `active` VALUES ('273', '02312dwqewq1231', '校园生活#', '', '', '', '》shiro-all-1.2.3 (1).jar,》spring-template.zip,', '523KB,11KB,', '1', '0', '0,0,', '2016-12-19 17:05:22');
INSERT INTO `active` VALUES ('274', '02312dwqewq1239', '校园生活#', '', '', '', '', '', '1', '0', '', '2016-12-20 10:59:35');
INSERT INTO `active` VALUES ('276', '181d4d3239e449b2938821243327048c', '校园生活#', null, '456454444444445455411111111111111111111111111111111111111111111111111111111111111111111111111111111111', '1482233856966-0.jpg,', '__762822440-1878145892,__-1367039482-2109271228,', '21KB,348KB,', '1', '0', '0,0,', '2016-12-20 19:37:37');
INSERT INTO `active` VALUES ('277', '181d4d3239e449b2938821243327048c', '校园生活#', null, 'zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz', '1482234693450-0.jpg,', '', '', '1', '0', '', '2016-12-20 19:51:33');
INSERT INTO `active` VALUES ('278', '181d4d3239e449b2938821243327048c', '校园生活#', null, 'qwqewqewqeqw', '1482306203608-0.jpg,', '__.tcookieid,', '0KB,', '1', '0', '0,', '2016-12-21 15:43:24');
INSERT INTO `active` VALUES ('279', '181d4d3239e449b2938821243327048c', '校园生活#', '', 'hello，baby', '1482306687074-0.jpg,', '', '', '1', '0', '', '2016-12-21 15:51:27');
INSERT INTO `active` VALUES ('280', '181d4d3239e449b2938821243327048c', '校园生活#', '', '说什么', '1482306774294-0.jpg,1482306774294-0-1.jpg,', '__shiro-root-1.2.3-source-release源码.zip,__bootstrap-table-editable.js,', '1282KB,4KB,', '0', '0', '0,0,', '2016-12-21 15:52:54');
INSERT INTO `active` VALUES ('281', '181d4d3239e449b2938821243327048c', '校园生活#', '', '', '1482485896952-5.jpg,', '', '', '0', '0', '', '2016-12-23 17:38:17');
INSERT INTO `active` VALUES ('282', '181d4d3239e449b2938821243327048c', '校园生活#', '', '摩擦摩擦', '', '', '', '0', '0', '', '2016-12-23 19:59:54');
INSERT INTO `active` VALUES ('283', '181d4d3239e449b2938821243327048c', '校园生活#', '', 'its ok，yeah，ok', '', '', '', '0', '0', '', '2016-12-23 20:04:57');
INSERT INTO `active` VALUES ('284', '181d4d3239e449b2938821243327048c', '工作生活#', null, '', '', '', '', '0', '0', '', '2016-12-23 20:10:00');
INSERT INTO `active` VALUES ('285', '181d4d3239e449b2938821243327048c', '校园生活#', null, '', '', '', '', '0', '0', '', '2016-12-23 20:19:21');
INSERT INTO `active` VALUES ('286', '181d4d3239e449b2938821243327048c', '校园生活#', null, 'zzzzzzzzzzzzzzzzzzzz', '', '', '', '0', '0', '', '2016-12-23 20:20:23');
INSERT INTO `active` VALUES ('287', '181d4d3239e449b2938821243327048c', '校园生活#', '', '你为我送别，我为你送别', '1482822002742-0.jpg,1482822002742-0-1.jpg,', '__shiro-root-1.3.2-source-release.zip,__spring-web.zip,', '1441KB,13KB,', '0', '0', '0,0,', '2016-12-27 15:00:03');

-- ----------------------------
-- Table structure for active_verify
-- ----------------------------
DROP TABLE IF EXISTS `active_verify`;
CREATE TABLE `active_verify` (
  `act_verify_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_active` varchar(255) DEFAULT NULL,
  `stu_verify` varchar(255) DEFAULT '0' COMMENT '0=学生验证关闭，1=学生验证开启',
  `certi_verify` varchar(255) DEFAULT '0' COMMENT '0=毕业证验证关闭，1=毕业证验证开启',
  PRIMARY KEY (`act_verify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of active_verify
-- ----------------------------
INSERT INTO `active_verify` VALUES ('1', '校园生活#', '0', '0');
INSERT INTO `active_verify` VALUES ('2', '工作生活#', '0', '0');
INSERT INTO `active_verify` VALUES ('3', '旅行游记#', '0', '0');
INSERT INTO `active_verify` VALUES ('4', '秀一秀#', '0', '0');
INSERT INTO `active_verify` VALUES ('5', '校园商品#', '0', '0');
INSERT INTO `active_verify` VALUES ('6', '资源分享#', '0', '0');
INSERT INTO `active_verify` VALUES ('7', '有问必答#', '0', '0');
INSERT INTO `active_verify` VALUES ('8', '毕业结伴#', '0', '0');
INSERT INTO `active_verify` VALUES ('9', '首页动态#', '0', '0');

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
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `ans_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL COMMENT '回答问题用户的uuid',
  `ques_id` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT '0' COMMENT '1=评论；2=点赞；',
  `name_type` varchar(255) DEFAULT NULL COMMENT '实名；匿名',
  `content` varchar(255) DEFAULT NULL COMMENT '回答的内容',
  `see` int(11) DEFAULT '0' COMMENT '浏览量',
  `ans_creatime` timestamp NULL DEFAULT NULL COMMENT '回答的时间',
  PRIMARY KEY (`ans_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1', '181d4d3239e449b2938821243327048c', '3', '1', '实名', '不要相约，来世见', '2', '2016-12-23 11:24:18');
INSERT INTO `answer` VALUES ('2', '02312dwqewq1238', '3', '1', '匿名', '最丑的是誓言', '0', '2016-12-23 11:24:35');
INSERT INTO `answer` VALUES ('3', '02312dwqewq1238', '2', '1', '匿名', '挂不敢算尽', '0', '2016-12-23 11:24:36');
INSERT INTO `answer` VALUES ('7', '181d4d3239e449b2938821243327048c', '5', '1', '实名', '谓天道无偿', '0', '2016-12-23 11:24:26');
INSERT INTO `answer` VALUES ('8', '181d4d3239e449b2938821243327048c', '2', '1', '实名', '你丑你先睡', '1', '2016-12-23 11:24:26');
INSERT INTO `answer` VALUES ('10', '02312dwqewq1232', '3', '1', '实名', '爱你爱不够', '0', '2016-12-23 11:24:26');
INSERT INTO `answer` VALUES ('11', '02312dwqewq1234', '2', '1', '实名', '说你为什么', '0', '2016-12-23 11:24:26');
INSERT INTO `answer` VALUES ('12', '02312dwqewq1231', '1', '1', '实名', '情不敢至深', '0', '2016-12-23 11:24:26');
INSERT INTO `answer` VALUES ('13', '02312dwqewq1232', '1', '1', '实名', '挂不敢算尽', '0', '2016-12-23 11:24:26');
INSERT INTO `answer` VALUES ('14', '02312dwqewq1233', '1', '1', '实名', '谓天道无偿', '0', '2016-12-23 11:24:26');
INSERT INTO `answer` VALUES ('15', '02312dwqewq1231', '2', '1', '实名', '恐大梦一场', '0', '2016-12-23 11:24:26');
INSERT INTO `answer` VALUES ('16', '02312dwqewq1231', '4', '1', '匿名', '情不敢至深', '0', '2016-12-23 11:24:39');
INSERT INTO `answer` VALUES ('17', '02312dwqewq1232', '4', '1', '匿名', '恐大梦一场', '0', '2016-12-23 11:24:39');
INSERT INTO `answer` VALUES ('18', '02312dwqewq1233', '4', '1', '匿名', '挂不敢算尽', '0', '2016-12-23 11:24:39');
INSERT INTO `answer` VALUES ('19', '02312dwqewq1231', '5', '1', '匿名', '谓天道无偿', '0', '2016-12-23 11:24:39');
INSERT INTO `answer` VALUES ('20', '02312dwqewq1232', '5', '1', '匿名', '挂不敢算尽', '0', '2016-12-23 11:24:39');
INSERT INTO `answer` VALUES ('21', '02312dwqewq1231', '6', '1', '实名', '谓天道无偿', '0', '2016-12-23 11:24:48');
INSERT INTO `answer` VALUES ('22', '02312dwqewq1232', '6', '1', '实名', '恐大梦一场', '0', '2016-12-23 11:24:49');
INSERT INTO `answer` VALUES ('23', '02312dwqewq1233', '6', '1', '匿名', '情不敢至深', '0', '2016-12-23 11:24:42');
INSERT INTO `answer` VALUES ('24', '02312dwqewq1231', '8', '1', '匿名', '情不敢至深', '0', '2016-12-23 11:24:42');
INSERT INTO `answer` VALUES ('25', '02312dwqewq1232', '8', '1', '匿名', '恐大梦一场', '0', '2016-12-23 11:24:42');
INSERT INTO `answer` VALUES ('26', '02312dwqewq1233', '8', '1', '实名', '恐大梦一场', '0', '2016-12-23 11:24:50');
INSERT INTO `answer` VALUES ('27', '02312dwqewq1231', '9', '1', '实名', '挂不敢算尽', '0', '2016-12-23 11:24:53');
INSERT INTO `answer` VALUES ('28', '02312dwqewq1232', '9', '1', '实名', '谓天道无偿', '0', '2016-12-23 11:24:53');
INSERT INTO `answer` VALUES ('29', '02312dwqewq1233', '9', '1', '匿名', '挂不敢算尽', '0', '2016-12-23 11:24:56');
INSERT INTO `answer` VALUES ('30', '02312dwqewq1235', '5', '1', '实名', '那些无法的改变，就在放下举起间', '0', '2016-12-23 14:29:51');
INSERT INTO `answer` VALUES ('31', '02312dwqewq1232', '3', '1', '匿名', '情不敢至深，恐大梦一场', '0', '2016-12-23 16:03:16');

-- ----------------------------
-- Table structure for checkcode
-- ----------------------------
DROP TABLE IF EXISTS `checkcode`;
CREATE TABLE `checkcode` (
  `id` varchar(11) NOT NULL,
  `code` varchar(6) DEFAULT NULL,
  `creatime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of checkcode
-- ----------------------------

-- ----------------------------
-- Table structure for child_answer
-- ----------------------------
DROP TABLE IF EXISTS `child_answer`;
CREATE TABLE `child_answer` (
  `child_ans_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `ans_id` int(11) DEFAULT NULL,
  `child_type` varchar(255) DEFAULT '0' COMMENT '1=子评论；2=点赞；',
  `content` varchar(255) DEFAULT NULL,
  `child_ans_creatime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`child_ans_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of child_answer
-- ----------------------------
INSERT INTO `child_answer` VALUES ('1', '02312dwqewq1231', '1', '1', '说你爱我，就是爱', '2016-12-12 14:16:51');
INSERT INTO `child_answer` VALUES ('2', '02312dwqewq1232', '1', '1', '挂不敢算尽，谓天道无偿', '2016-12-13 14:18:22');
INSERT INTO `child_answer` VALUES ('3', '02312dwqewq1233', '1', '1', '情不敢至深，恐大梦一场', '2016-12-14 14:18:22');
INSERT INTO `child_answer` VALUES ('4', '02312dwqewq1235', '5', '2', null, '2016-12-23 16:33:36');
INSERT INTO `child_answer` VALUES ('5', '02312dwqewq1234', '1', '2', null, '2016-12-26 20:19:29');
INSERT INTO `child_answer` VALUES ('6', '181d4d3239e449b2938821243327048c', '8', '2', '', '2016-12-23 21:03:37');
INSERT INTO `child_answer` VALUES ('7', '02312dwqewq1237', '7', '1', '解不开的是心门', '2016-12-23 21:08:48');
INSERT INTO `child_answer` VALUES ('8', '02312dwqewq1235', '16', '1', '解不开的是心门', '2016-12-23 21:09:36');
INSERT INTO `child_answer` VALUES ('9', '181d4d3239e449b2938821243327048c', '1', '1', '情不敢至深，恐大梦一场', '2016-12-26 16:38:11');

-- ----------------------------
-- Table structure for child_commont
-- ----------------------------
DROP TABLE IF EXISTS `child_commont`;
CREATE TABLE `child_commont` (
  `child_id` int(11) NOT NULL AUTO_INCREMENT,
  `child_user_id` varchar(255) DEFAULT NULL COMMENT '子评论的uuid',
  `com_id` int(11) DEFAULT NULL COMMENT 'commont表的id',
  `parent_user_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT '0' COMMENT '1=评论；2=点赞；',
  `child_content` varchar(255) DEFAULT NULL,
  `child_creatime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`child_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of child_commont
-- ----------------------------
INSERT INTO `child_commont` VALUES ('1', '02312dwqewq1239', '3', '02312dwqewq1233', '1', '说，说你为什么？？', '2016-12-20 09:48:13');
INSERT INTO `child_commont` VALUES ('2', '6340428319bc4b0db3446725634f369b', '4', '02312dwqewq1232', '1', '各自安好', '2016-12-20 09:48:13');
INSERT INTO `child_commont` VALUES ('3', '02312dwqewq1239', '11', '02312dwqewq1231', '1', '不请自来贪欲念', '2016-12-20 09:48:13');
INSERT INTO `child_commont` VALUES ('4', '02312dwqewq1239', '3', '02312dwqewq1231', '1', '不要相约来世见', '2016-12-20 09:48:13');
INSERT INTO `child_commont` VALUES ('5', '02312dwqewq1238', '3', '02312dwqewq1231', '1', '最美的是遗言，最丑的是誓言', '2016-12-20 09:48:13');
INSERT INTO `child_commont` VALUES ('6', '02312dwqewq1234', '6', '02312dwqewq1231', '1', '那些无法的改变就在放下举起间', '2016-12-20 09:48:13');
INSERT INTO `child_commont` VALUES ('7', '181d4d3239e449b2938821243327048c', '3', '02312dwqewq1231', '2', null, '2016-12-20 10:34:59');

-- ----------------------------
-- Table structure for commont
-- ----------------------------
DROP TABLE IF EXISTS `commont`;
CREATE TABLE `commont` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_user_id` varchar(255) DEFAULT NULL COMMENT '评论用户的uuid',
  `user_id` varchar(255) DEFAULT NULL COMMENT '发动态用户的uuid',
  `active_user_id` int(11) DEFAULT NULL COMMENT '动态的的id',
  `type` varchar(255) DEFAULT '0' COMMENT '1=评论；2=点赞；',
  `content` varchar(255) DEFAULT NULL COMMENT '评论的内容',
  `com_creatime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commont
-- ----------------------------
INSERT INTO `commont` VALUES ('3', '02312dwqewq1231', null, '209', '1', '说，说你为什么？？', '2016-12-05 11:38:29');
INSERT INTO `commont` VALUES ('4', '02312dwqewq1232', null, '209', '1', '说个牙刷！！！', '2016-12-02 20:42:25');
INSERT INTO `commont` VALUES ('5', '02312dwqewq1233', null, '209', '1', 'can you feel my world!!', '2016-12-05 11:38:32');
INSERT INTO `commont` VALUES ('6', '02312dwqewq1231', null, '209', '1', 'are you ok!', '2016-12-05 13:35:38');
INSERT INTO `commont` VALUES ('11', '02312dwqewq1231', null, '214', '2', null, '2016-12-02 13:33:06');
INSERT INTO `commont` VALUES ('12', '02312dwqewq1237', null, '214', '1', 'aaaaa', '2016-12-02 17:56:27');
INSERT INTO `commont` VALUES ('13', '02312dwqewq1233', null, '221', '2', null, '2016-12-05 10:48:34');
INSERT INTO `commont` VALUES ('14', '02312dwqewq1233', null, '221', '1', '搜嘎', '2016-12-05 10:48:34');
INSERT INTO `commont` VALUES ('15', '02312dwqewq1232', null, '218', '1', '数艘爱神的箭阿萨德', '2016-12-02 13:58:25');
INSERT INTO `commont` VALUES ('16', '02312dwqewq1233', null, '210', '1', '的撒多', '2016-12-05 11:37:30');
INSERT INTO `commont` VALUES ('17', '02312dwqewq1233', null, '208', '2', null, '2016-12-05 10:48:34');
INSERT INTO `commont` VALUES ('18', '02312dwqewq1232', null, '209', '2', null, '2016-12-02 20:40:14');
INSERT INTO `commont` VALUES ('19', '02312dwqewq1231', null, '210', '2', '说你为什么', '2016-12-05 11:46:20');
INSERT INTO `commont` VALUES ('20', '02312dwqewq1234', null, '214', '2', null, '2016-12-02 17:57:15');
INSERT INTO `commont` VALUES ('21', '02312dwqewq1235', null, '214', '2', null, '2016-12-02 17:57:11');
INSERT INTO `commont` VALUES ('22', '02312dwqewq1239', null, '216', '1', null, '2016-12-05 11:36:48');
INSERT INTO `commont` VALUES ('23', '02312dwqewq1233', null, '239', '1', '情不敢至深，恐大梦一场', '2016-12-20 20:28:37');
INSERT INTO `commont` VALUES ('24', '02312dwqewq1235', null, '210', '1', '挂不敢算尽，谓天道无常', '2016-12-20 20:29:32');

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `f_user_id` varchar(255) DEFAULT NULL COMMENT '好友的uuid',
  `f_see_state` varchar(255) DEFAULT '1' COMMENT '朋友是否可查看动态，0=不能看动态，1=能看动态',
  `fans_see_state` varchar(255) DEFAULT '1' COMMENT 'fans是否可查看动态，0=不能看动态，1=能看动态',
  `type` varchar(255) DEFAULT NULL COMMENT '0=朋友；1=关注',
  `f_creatime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('1', '181d4d3239e449b2938821243327048c', '6340428319bc4b0db3446725634f369b', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('2', '6340428319bc4b0db3446725634f369b', '181d4d3239e449b2938821243327048c', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('3', '181d4d3239e449b2938821243327048c', '02312dwqewq1231', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('4', '02312dwqewq1231', '181d4d3239e449b2938821243327048c', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('5', '181d4d3239e449b2938821243327048c', '02312dwqewq1232', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('6', '02312dwqewq1232', '181d4d3239e449b2938821243327048c', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('7', '181d4d3239e449b2938821243327048c', '02312dwqewq1233', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('8', '02312dwqewq1233', '181d4d3239e449b2938821243327048c', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('9', '02312dwqewq1232', '02312dwqewq1238', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('10', '02312dwqewq1238', '02312dwqewq1232', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('11', '181d4d3239e449b2938821243327048c', '181d4d3239e449b2938821243327048c', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('12', '6340428319bc4b0db3446725634f369b', '6340428319bc4b0db3446725634f369b', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('13', '02312dwqewq1231', '02312dwqewq1231', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('14', '02312dwqewq1232', '02312dwqewq1232', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('15', '02312dwqewq1233', '02312dwqewq1233', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('16', '02312dwqewq1238', '02312dwqewq1238', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('17', '02312dwqewq1232', '02312dwqewq1239', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('18', '02312dwqewq1233', '02312dwqewq1240', '1', '1', '0', '2016-12-14 10:45:54');
INSERT INTO `friends` VALUES ('21', '02312dwqewq1239', '02312dwqewq1233', '1', '1', '1', '2016-12-14 11:51:20');
INSERT INTO `friends` VALUES ('25', '02312dwqewq1239', '02312dwqewq1238', '1', '1', '1', '2016-12-14 14:29:37');
INSERT INTO `friends` VALUES ('26', '02312dwqewq1235', '02312dwqewq1231', '1', '1', '0', '2016-12-14 14:36:52');
INSERT INTO `friends` VALUES ('28', '02312dwqewq1232', '02312dwqewq1238', '1', '1', '1', '2016-12-14 14:41:38');
INSERT INTO `friends` VALUES ('30', '02312dwqewq1231', '02312dwqewq1238', '1', '1', '1', '2016-12-14 14:57:28');
INSERT INTO `friends` VALUES ('31', '2b83723b61cb463bbd8e7754b43be123', '2b83723b61cb463bbd8e7754b43be123', '1', '1', '0', '2016-12-15 15:36:55');
INSERT INTO `friends` VALUES ('32', '02312dwqewq1231', '181d4d3239e449b2938821243327048c', '1', '1', '1', '2016-12-12 11:08:39');

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `ques_id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL COMMENT '问题',
  `ques_creatime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '问题创建的时间',
  `ques_lastmodifytime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '问题的最后修改时间',
  PRIMARY KEY (`ques_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES ('1', '海鸟跟鱼相爱，只是一场意外吗？', '2016-12-13 16:02:44', null);
INSERT INTO `questions` VALUES ('2', '不要相约，来世见。来世还能再见吗？', '2016-12-13 16:03:47', null);
INSERT INTO `questions` VALUES ('3', '打开地狱的大门，不请自来贪欲念；\n无偿路上买命钱，是生是畜黄泉见；', '2016-12-13 16:14:33', null);
INSERT INTO `questions` VALUES ('4', '萌妹子好，还是女汉子好？', '2016-12-16 15:16:23', null);
INSERT INTO `questions` VALUES ('5', '喜欢肌肉男还是大叔？', '2016-12-16 15:17:20', null);
INSERT INTO `questions` VALUES ('6', '分手后为啥子不能做朋友？', '2016-12-16 15:17:49', null);
INSERT INTO `questions` VALUES ('8', '搞基搞不搞？', '2016-12-16 15:20:10', null);
INSERT INTO `questions` VALUES ('9', '我吧肛门塞满', '2016-12-16 15:23:18', null);
INSERT INTO `questions` VALUES ('10', '过年，过年', '2016-12-22 11:53:21', null);
INSERT INTO `questions` VALUES ('11', '过年，过年好玩吗？', '2016-12-23 14:41:41', null);
INSERT INTO `questions` VALUES ('12', '不要相约来世见，到不到的是永远，接不开的是心门', '2016-12-23 15:35:18', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `schoolId` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL DEFAULT '0',
  `phoneNum` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_nickname` varchar(255) DEFAULT NULL,
  `profile` varchar(512) DEFAULT NULL,
  `stu_state` varchar(255) DEFAULT '0' COMMENT '学生证验证；0=审核中，1=审核通过，2=审核不通过',
  `certi_state` varchar(255) DEFAULT '0' COMMENT '毕业证验证；0=审核中，1=审核通过，2=审核不通过',
  `stu_verify` varchar(255) DEFAULT NULL COMMENT '验证的图片',
  `certi_verify` varchar(255) DEFAULT NULL,
  `verify_state` varchar(255) DEFAULT '0' COMMENT '0=不是认证用户；1是认证用户',
  `gender` varchar(255) DEFAULT NULL,
  `star` varchar(255) DEFAULT NULL,
  `e_state` varchar(255) DEFAULT NULL COMMENT '情感状态',
  `level` int(11) DEFAULT '1',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `county` varchar(255) DEFAULT NULL COMMENT '县',
  `home` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT '0',
  `grade` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `school` varchar(255) DEFAULT NULL,
  `highschool` varchar(255) DEFAULT NULL,
  `lable` varchar(255) DEFAULT NULL,
  `skill` varchar(255) DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `creatime` timestamp NULL DEFAULT NULL,
  `lastmodifytime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`schoolId`),
  UNIQUE KEY `phoneNum` (`phoneNum`)
) ENGINE=InnoDB AUTO_INCREMENT=100020 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', '87a4579206dd468a9cc4635299ba7c2b', '18782951708', '4QrcOUm6Wau+VuBX8g+IPg==', '大哥', '1479901220644.jpg', '0', '0', null, null, '0', '男', null, null, '1', null, null, null, '柬埔寨', '1996-05-03', '研一', null, '通信工程', '柬埔寨大学', 'cc', null, null, 'e472769b18d96ca464bb784674dfec06', '2016-09-12 15:43:10', '2016-11-25 15:45:03');
INSERT INTO `user` VALUES ('100000', 'qq213', '12322334', '21321ewq', '画画', '1479901246692.jpg', '0', '0', null, null, '0', '女', null, null, '1', null, null, null, '璧山', '1994-05-07', '研二', null, '计算机信息', '柬埔寨大学', '大路中学', null, null, 'null', '2016-11-25 15:48:03', '2016-11-25 15:48:03');
INSERT INTO `user` VALUES ('100004', '02312dwqewq1231', '10000000000', '4QrcOUm6Wau+VuBX8g+IPg==', '画画', '1480560775230-0.jpg', '0', '0', null, null, '0', '男', null, null, '1', null, null, null, '荷兰', '2014-10-03', '大二', null, '供应链管理', '刚果大学', 'cc', null, null, '1', '2016-11-25 15:55:21', '2016-11-25 15:55:21');
INSERT INTO `user` VALUES ('100005', '02312dwqewq1232', '10000000001', '4QrcOUm6Wau+VuBX8g+IPg==', '花阿虎', '1480560800657-0.jpg', '0', '0', null, null, '0', '男', '水瓶座', '', '1', null, null, null, '璧山', '1992-11-02', '大三', '计算机', '会计电算化', '刚果大学', '大路中学', '', '', '2', '2016-04-11 16:07:12', '2016-11-25 16:07:12');
INSERT INTO `user` VALUES ('100006', '02312dwqewq1233', '10000000002', '4QrcOUm6Wau+VuBX8g+IPg==', '泥煤', '1480560662382-0.jpg', '0', '0', null, null, '0', '女', '天蝎', '耍朋友', '1', null, null, null, '柬埔寨', '1995-05-05', '大一', '通信工程', '经济管理', '柬埔寨大学', '来凤中学', '人生的代词有很多，而我始终是我', '', '3', '2016-11-25 16:07:12', '2016-11-25 16:07:12');
INSERT INTO `user` VALUES ('100007', '02312dwqewq1234', '10000000003', '4QrcOUm6Wau+VuBX8g+IPg==', '奥巴马', '1480051190810.jpg', '0', '0', null, null, '0', '女', '', '', '1', null, null, null, '荷兰', '1994-05-03', '研二', '', '酒店管理', '刚果大学', '来凤中学', '', '', '4', '2016-11-07 16:42:01', '2016-11-25 16:42:01');
INSERT INTO `user` VALUES ('100008', '02312dwqewq1235', '10000000004', '4QrcOUm6Wau+VuBX8g+IPg==', '草泥马０', '1480411626127.jpg', '0', '0', null, null, '0', '女', '', '', '1', null, null, null, '柬埔寨', '1998-05-12', '大二', '', '土木工程', '刚果大学', '大路中学', '', '', '8', '2016-11-25 16:12:42', '2016-11-25 16:12:42');
INSERT INTO `user` VALUES ('100009', '02312dwqewq1236', '10000000005', '4QrcOUm6Wau+VuBX8g+IPg==', '搞基搞不搞', '1480411626128.jpg', '0', '0', null, null, '0', '女', '', '', '1', null, null, null, '荷兰', '1997-05-03', '大四', '', '供应链管理', '柬埔寨大学', '来凤中学', '', '', '1', '2016-08-14 16:07:12', '2016-11-25 16:07:12');
INSERT INTO `user` VALUES ('100010', '02312dwqewq1237', '10000000006', '4QrcOUm6Wau+VuBX8g+IPg==', '测试等级1', '1480073248691.jpg', '0', '0', null, null, '0', '男', '', '', '1', null, null, null, '荷兰', '2000-09-23', '大一', '', '计算机信息', '柬埔寨大学', 'cc', '', '', '6', '2016-09-29 16:07:12', '2016-11-25 16:07:12');
INSERT INTO `user` VALUES ('100011', '02312dwqewq1238', '10000000007', '4QrcOUm6Wau+VuBX8g+IPg==', '测试等级2', '1480058823385.jpg', '0', '0', null, null, '0', '男', '', '', '1', null, null, null, '柬埔寨', '1993-08-12', '大二', '', '计算机信息', '柬埔寨大学', 'cc', '', '', '2', '2016-01-10 16:07:12', '2016-11-25 16:07:12');
INSERT INTO `user` VALUES ('100013', '02312dwqewq1239', '10000000008', '4QrcOUm6Wau+VuBX8g+IPg==', 'nick', '1480058823386.jpg', '0', '0', null, null, '0', '女', '', '', '1', null, null, null, '荷兰', '1995-05-03', '大四', '', '供应链管理', '柬埔寨大学', '来凤中学', '', '', '2', '2016-01-10 16:07:12', '2016-10-10 16:07:12');
INSERT INTO `user` VALUES ('100014', '02312dwqewq1240', '10000000009', '4QrcOUm6Wau+VuBX8g+IPg==', 'nick', '1480058823387.jpg', '0', '0', null, null, '0', '女', '', '', '12', null, null, null, '老挝', '2001-06-13', '大三', '', '通信工程', '刚果大学', 'cc', '', '', '2', '2016-01-01 16:07:12', '2016-11-02 16:07:12');
INSERT INTO `user` VALUES ('100016', '181d4d3239e449b2938821243327048c', '15828247975', '4QrcOUm6Wau+VuBX8g+IPg==', '周', '1480560647990-0.jpg', '0', '0', '1481198174298.jpg', '1481198114039.jpg', '1', '男', '摩羯座', 'lonly', '1', '四川', '成都', '高新区', '柬埔寨', '1990-12-31', '大二', '互联网/软件', '专业', '大学', '成都七中', '666666666666666666666666', '技能1,技能2,技能3,技能4', 'be8058f5b19249e7a062e3f247e2870e', '2016-11-29 17:03:25', '2016-12-30 17:42:25');
INSERT INTO `user` VALUES ('100017', '6340428319bc4b0db3446725634f369b', '13883261153', '4QrcOUm6Wau+VuBX8g+IPg==', '杨', '1480560757699-0.jpg', '0', '0', '1481200501035.jpg', null, '0', '男', null, null, '1', null, null, null, '刚果', '1996-10-08', '研一', null, '通信工程', '越南大学', '来凤中学', null, null, 'b992473f2250b8db185806467ba7b98c', '2016-11-29 17:06:34', '2016-12-08 20:35:01');
INSERT INTO `user` VALUES ('100018', '100001', '13666666666', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, '0', '0', null, null, '0', '女', null, null, '1', null, null, null, '老挝', '1999-11-10', '研二', null, '通信工程', '刚果大学', '大路中学', null, null, 'bf401f0a9419d342dbcec70a6952f9cb', '2016-12-06 15:45:12', null);
INSERT INTO `user` VALUES ('100019', '2b83723b61cb463bbd8e7754b43be123', '15708496430', '4QrcOUm6Wau+VuBX8g+IPg==', 'dww', null, '0', '0', null, null, '0', null, null, null, '1', null, null, null, '刚果', '1992-11-10', '研三', '计算机', null, '路透社', '来凤中学', null, null, '10f58ffecf18d6b2c3662bdbd71ba417', '2016-12-15 15:36:55', null);

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `v_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL COMMENT '访客的云信id',
  `v_user_id` varchar(255) DEFAULT NULL COMMENT '被访问的云信id',
  `v_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`v_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of visitor
-- ----------------------------
INSERT INTO `visitor` VALUES ('1', '02312dwqewq1232', '181d4d3239e449b2938821243327048c', '2016-12-30 16:27:09');
INSERT INTO `visitor` VALUES ('2', '02312dwqewq1237', '181d4d3239e449b2938821243327048c', '2016-12-30 16:28:56');
INSERT INTO `visitor` VALUES ('3', '181d4d3239e449b2938821243327048c', '02312dwqewq1231', '2017-01-03 11:15:13');
INSERT INTO `visitor` VALUES ('4', '181d4d3239e449b2938821243327048c', '02312dwqewq1239', '2017-01-03 11:16:02');
INSERT INTO `visitor` VALUES ('5', '181d4d3239e449b2938821243327048c', '02312dwqewq1240', '2017-01-03 11:16:40');
INSERT INTO `visitor` VALUES ('6', '181d4d3239e449b2938821243327048c', '02312dwqewq1232', '2017-01-03 11:22:44');

-- ----------------------------
-- View structure for class_active
-- ----------------------------
DROP VIEW IF EXISTS `class_active`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `class_active` AS select `a`.`active_user_id` AS `active_user_id`,`a`.`user_id` AS `user_id`,`u`.`level` AS `level`,`u`.`user_nickname` AS `user_nickname`,`u`.`verify_state` AS `verify_state`,`a`.`type_a` AS `type_a`,`a`.`type_b` AS `type_b`,`a`.`saysay` AS `saysay`,`a`.`active_pic` AS `active_pic`,`a`.`docum` AS `docum`,`a`.`docum_size` AS `docum_size`,`a`.`state` AS `state`,`a`.`see` AS `see`,`a`.`doc_down_count` AS `doc_down_count`,`a`.`active_creatime` AS `active_creatime` from (`active` `a` join `user` `u` on((`a`.`user_id` = `u`.`user_id`))) where (`a`.`state` = '1') order by `a`.`active_creatime` desc ;

-- ----------------------------
-- View structure for cycle_friends_active
-- ----------------------------
DROP VIEW IF EXISTS `cycle_friends_active`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cycle_friends_active` AS select `a`.`active_user_id` AS `active_user_id`,`w`.`user_id` AS `user_id`,`w`.`user_nickname` AS `user_nickname`,`w`.`f_user_id` AS `f_user_id`,`w`.`level` AS `level`,`a`.`type_a` AS `type_a`,`a`.`type_b` AS `type_b`,`a`.`saysay` AS `saysay`,`a`.`active_pic` AS `active_pic`,`a`.`docum` AS `dcoum`,`a`.`state` AS `state`,`w`.`fans_see_state` AS `fans_see_state`,`a`.`see` AS `see`,`a`.`active_creatime` AS `active_creatime` from (`schoolapp`.`active` `a` join (select distinct `u`.`user_id` AS `user_id`,`f`.`f_user_id` AS `f_user_id`,`f`.`fans_see_state` AS `fans_see_state`,`u`.`level` AS `level`,`u`.`user_nickname` AS `user_nickname` from (`schoolapp`.`user` `u` join `schoolapp`.`friends` `f` on((`u`.`user_id` = `f`.`user_id`)))) `w` on((`a`.`user_id` = `w`.`f_user_id`))) where ((`a`.`state` = '1') and (`w`.`fans_see_state` = '1')) order by `a`.`active_creatime` desc ;

-- ----------------------------
-- View structure for pengpeng_main
-- ----------------------------
DROP VIEW IF EXISTS `pengpeng_main`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pengpeng_main` AS select `w`.`ques_id` AS `ques_id`,`w`.`question` AS `question`,`w`.`user_id` AS `user_id`,`w`.`type` AS `type`,`w`.`name_type` AS `name_type`,`w`.`content` AS `content`,`w`.`ans_creatime` AS `ans_creatime`,`u`.`user_nickname` AS `user_nickname`,`u`.`profile` AS `profile`,`u`.`gender` AS `gender`,`u`.`birthday` AS `birthday`,`u`.`province` AS `province`,`u`.`city` AS `city` from (((select `a`.`user_id` AS `user_id`,`a`.`ques_id` AS `ques_id`,`a`.`type` AS `type`,`a`.`name_type` AS `name_type`,`a`.`content` AS `content`,`a`.`ans_creatime` AS `ans_creatime`,`q`.`question` AS `question` from (`schoolapp`.`answer` `a` join `schoolapp`.`questions` `q` on((`a`.`ques_id` = `q`.`ques_id`))))) `w` join `schoolapp`.`user` `u` on((`w`.`user_id` = `u`.`user_id`))) order by `w`.`ans_creatime` desc ;

-- ----------------------------
-- Event structure for create_user_level
-- ----------------------------
DROP EVENT IF EXISTS `create_user_level`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `create_user_level` ON SCHEDULE EVERY 1 DAY STARTS '2017-01-04 03:00:00' ON COMPLETION NOT PRESERVE ENABLE DO -- 更新用户等级
UPDATE user SET
	level=CASE 
					when TO_DAYS(NOW())-TO_DAYS(creatime)<6 THEN 1 -- <6
					when TO_DAYS(NOW())-TO_DAYS(creatime)<16 THEN 2 -- <16
					when TO_DAYS(NOW())-TO_DAYS(creatime)<31 then 3 -- <31
					when TO_DAYS(NOW())-TO_DAYS(creatime)<51 then 4 -- <51
					when TO_DAYS(NOW())-TO_DAYS(creatime)<76 then 5 -- <76
					when TO_DAYS(NOW())-TO_DAYS(creatime)<106 then 6 -- <106
					when TO_DAYS(NOW())-TO_DAYS(creatime)<141 then 7 -- <141
					when TO_DAYS(NOW())-TO_DAYS(creatime)<181 then 8 -- <181
					when TO_DAYS(NOW())-TO_DAYS(creatime)<226 then 9 -- <226
					when TO_DAYS(NOW())-TO_DAYS(creatime)<276 then 10 -- <276
					when TO_DAYS(NOW())-TO_DAYS(creatime)<331 then 11 -- <331
					when TO_DAYS(NOW())-TO_DAYS(creatime)<391 then 12 -- <391
					when TO_DAYS(NOW())-TO_DAYS(creatime)<456 then 13 -- <456
					when TO_DAYS(NOW())-TO_DAYS(creatime)<526 then 14 -- <526
					when TO_DAYS(NOW())-TO_DAYS(creatime)<601 then 15 -- <601
				END
;;
DELIMITER ;
