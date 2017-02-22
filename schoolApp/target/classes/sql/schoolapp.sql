/*
Navicat MySQL Data Transfer

Source Server         : yang_local
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : schoolapp

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-02-22 10:55:10
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
  `saysay` mediumtext,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `active_pic` varchar(500) DEFAULT NULL,
  `docum` varchar(500) DEFAULT NULL COMMENT '上传的资源文件',
  `docum_size` varchar(500) DEFAULT '0' COMMENT '每个文件的大小',
  `state` varchar(255) DEFAULT '0' COMMENT '状态数据的状态：0=审核中，1=审核通过，2=审核不通过',
  `see` int(11) DEFAULT '0' COMMENT '查看',
  `doc_down_count` varchar(500) DEFAULT '0' COMMENT '文件下载次数',
  `active_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`active_user_id`),
  FULLTEXT KEY `saysay` (`saysay`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for active_verify
-- ----------------------------
DROP TABLE IF EXISTS `active_verify`;
CREATE TABLE `active_verify` (
  `act_verify_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_active` varchar(255) DEFAULT NULL,
  `theme_pic` varchar(255) DEFAULT NULL COMMENT '主题的图标',
  `two_class` varchar(255) DEFAULT NULL COMMENT '二级分类',
  `two_pic` varchar(255) DEFAULT NULL,
  `stu_verify` varchar(255) DEFAULT '0' COMMENT '0=学生验证关闭，1=学生验证开启',
  `certi_verify` varchar(255) DEFAULT '0' COMMENT '0=毕业证验证关闭，1=毕业证验证开启',
  `act_creatime` timestamp NULL DEFAULT NULL,
  `act_lastmodifytime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`act_verify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

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
-- Table structure for admin_login_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_login_info`;
CREATE TABLE `admin_login_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `login_ip` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL COMMENT '来源',
  `browser_info` varchar(255) DEFAULT NULL COMMENT '浏览器信息',
  `login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `exit_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`ans_id`),
  KEY `ques_id` (`ques_id`),
  CONSTRAINT `ques_id` FOREIGN KEY (`ques_id`) REFERENCES `questions` (`ques_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for banner_pic
-- ----------------------------
DROP TABLE IF EXISTS `banner_pic`;
CREATE TABLE `banner_pic` (
  `ban_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `ban_pic` varchar(255) DEFAULT NULL COMMENT '图片的名称',
  `in_jump` varchar(255) DEFAULT NULL COMMENT '内部跳转',
  `ban_url` varchar(255) DEFAULT NULL COMMENT 'banner图外部的跳转路径',
  `total_see` int(255) DEFAULT '0' COMMENT '点击页面跳转的数量',
  `ban_creatime` timestamp NULL DEFAULT NULL COMMENT 'banner图的创建时间',
  `ban_lastmodify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'banner图的最后修改时间',
  PRIMARY KEY (`ban_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

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
-- Table structure for child_answer
-- ----------------------------
DROP TABLE IF EXISTS `child_answer`;
CREATE TABLE `child_answer` (
  `child_ans_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `ans_id` int(11) DEFAULT NULL,
  `parent_user_id` varchar(255) DEFAULT NULL COMMENT '父评论的用户id',
  `child_type` varchar(255) DEFAULT '0' COMMENT '1=子评论；2=点赞；',
  `content` varchar(255) DEFAULT NULL,
  `child_ans_creatime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`child_ans_id`),
  KEY `ans_id` (`ans_id`),
  CONSTRAINT `ans_id` FOREIGN KEY (`ans_id`) REFERENCES `answer` (`ans_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for commont
-- ----------------------------
DROP TABLE IF EXISTS `commont`;
CREATE TABLE `commont` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_user_id` varchar(255) DEFAULT NULL COMMENT '评论用户的uuid',
  `user_id` varchar(255) DEFAULT NULL COMMENT '发动态用户的uuid',
  `active_user_id` int(11) NOT NULL COMMENT '动态的的id',
  `type` varchar(255) DEFAULT '0' COMMENT '1=评论；2=点赞；',
  `content` varchar(255) DEFAULT NULL COMMENT '评论的内容',
  `see_state` varchar(255) DEFAULT '0' COMMENT '0=未查看；1=查看',
  `com_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dunbar_circle
-- ----------------------------
DROP TABLE IF EXISTS `dunbar_circle`;
CREATE TABLE `dunbar_circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '顿巴同心圆的内容',
  `last_modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites` (
  `favor_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL COMMENT '收藏的人',
  `type` varchar(255) DEFAULT NULL COMMENT '扩展分类',
  `active_user_id` int(11) DEFAULT NULL COMMENT '动态的id',
  `favor_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`favor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
  `see_type` varchar(255) DEFAULT '0' COMMENT '0=没看过朋友的朋友；1=看过碰过的朋友',
  `f_creatime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `ques_id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL COMMENT '问题',
  `ques_creatime` timestamp NULL DEFAULT NULL COMMENT '问题创建的时间',
  `ques_lastmodifytime` timestamp NULL DEFAULT NULL COMMENT '问题的最后修改时间',
  PRIMARY KEY (`ques_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for see_control
-- ----------------------------
DROP TABLE IF EXISTS `see_control`;
CREATE TABLE `see_control` (
  `see_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `other_user_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '1 不让TA看我动态 2 不看他的动态',
  `state` int(11) DEFAULT NULL COMMENT '0 关闭 1开启',
  `see_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`see_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for textkey
-- ----------------------------
DROP TABLE IF EXISTS `textkey`;
CREATE TABLE `textkey` (
  `text_id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL COMMENT '检索的内容',
  `text_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`text_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for two_child_answer
-- ----------------------------
DROP TABLE IF EXISTS `two_child_answer`;
CREATE TABLE `two_child_answer` (
  `two_child_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL COMMENT '再评论用户的uuid',
  `child_ans_id` int(11) DEFAULT NULL COMMENT 'child_answer的自增id',
  `grand_user_id` varchar(255) DEFAULT NULL COMMENT '父评论的uuid',
  `content` varchar(255) DEFAULT NULL COMMENT '评论的内容',
  `two_child_ans_creatime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`two_child_id`),
  KEY `child_ans_id` (`child_ans_id`),
  CONSTRAINT `child_ans_id` FOREIGN KEY (`child_ans_id`) REFERENCES `child_answer` (`child_ans_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for university
-- ----------------------------
DROP TABLE IF EXISTS `university`;
CREATE TABLE `university` (
  `university` varchar(255) DEFAULT NULL,
  `val` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `birthday` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `school` varchar(255) DEFAULT NULL,
  `highschool` varchar(255) DEFAULT NULL,
  `lable` varchar(255) DEFAULT NULL,
  `skill` varchar(255) DEFAULT NULL,
  `life_see` int(11) DEFAULT '0' COMMENT '生活圈动态粉丝可见',
  `add_switch` int(11) DEFAULT '0' COMMENT '加好友是否需要验证,0 关闭 1 开启',
  `send_msg` int(11) DEFAULT '1' COMMENT '是否可以发消息',
  `token` varchar(255) NOT NULL,
  `creatime` timestamp NULL DEFAULT NULL,
  `lastmodifytime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`schoolId`),
  UNIQUE KEY `phoneNum` (`phoneNum`)
) ENGINE=InnoDB AUTO_INCREMENT=100027 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
-- View structure for class_active
-- ----------------------------
DROP VIEW IF EXISTS `class_active`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `class_active` AS select `a`.`active_user_id` AS `active_user_id`,`a`.`user_id` AS `user_id`,`u`.`level` AS `level`,`u`.`user_nickname` AS `user_nickname`,`u`.`profile` AS `profile`,`u`.`verify_state` AS `verify_state`,`a`.`type_a` AS `type_a`,`a`.`type_b` AS `type_b`,`a`.`saysay` AS `saysay`,`a`.`title` AS `title`,`a`.`active_pic` AS `active_pic`,`a`.`docum` AS `docum`,`a`.`docum_size` AS `docum_size`,`a`.`state` AS `state`,`a`.`see` AS `see`,`a`.`doc_down_count` AS `doc_down_count`,`a`.`active_creatime` AS `active_creatime` from (`active` `a` join `user` `u` on((`a`.`user_id` = convert(`u`.`user_id` using utf8mb4)))) where (`a`.`state` = '1') order by `a`.`active_creatime` desc ;

-- ----------------------------
-- View structure for clause_2
-- ----------------------------
DROP VIEW IF EXISTS `clause_2`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `clause_2` AS select `a`.`active_user_id` AS `active_user_id`,`w`.`user_id` AS `user_id`,`w`.`user_nickname` AS `user_nickname`,`w`.`f_user_id` AS `f_user_id`,`w`.`level` AS `level`,`a`.`type_a` AS `type_a`,`a`.`type_b` AS `type_b`,`a`.`saysay` AS `saysay`,`a`.`active_pic` AS `active_pic`,`a`.`docum` AS `docum`,`a`.`state` AS `state`,`w`.`fans_see_state` AS `fans_see_state`,`a`.`see` AS `see`,`a`.`active_creatime` AS `active_creatime` from (`active` `a` join `user_info` `w` on((`a`.`user_id` = convert(`w`.`user_id` using utf8mb4)))) ;

-- ----------------------------
-- View structure for cycle_friends_active
-- ----------------------------
DROP VIEW IF EXISTS `cycle_friends_active`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cycle_friends_active` AS select `m`.`active_user_id` AS `active_user_id`,`m`.`user_id` AS `user_id`,`n`.`user_nickname` AS `user_nickname`,`n`.`profile` AS `profile`,`m`.`level` AS `level`,`m`.`type_a` AS `type_a`,`m`.`type_b` AS `type_b`,`m`.`saysay` AS `saysay`,`m`.`active_pic` AS `active_pic`,`m`.`docum` AS `docum`,`m`.`state` AS `state`,`m`.`fans_see_state` AS `fans_see_state`,`m`.`see` AS `see`,`m`.`active_creatime` AS `active_creatime` from (`clause_2` `m` join `user` `n` on((`m`.`user_id` = `n`.`user_id`))) where ((`m`.`state` = '1') and (`m`.`fans_see_state` = '1') and (`m`.`type_a` <> '匿名#')) order by `m`.`active_creatime` desc ;

-- ----------------------------
-- View structure for pengpeng_main
-- ----------------------------
DROP VIEW IF EXISTS `pengpeng_main`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pengpeng_main` AS select `w`.`ques_id` AS `ques_id`,`w`.`question` AS `question`,`w`.`user_id` AS `user_id`,`w`.`type` AS `type`,`w`.`name_type` AS `name_type`,`w`.`content` AS `content`,`w`.`ans_creatime` AS `ans_creatime`,`u`.`user_nickname` AS `user_nickname`,`u`.`profile` AS `profile`,`u`.`gender` AS `gender`,`u`.`birthday` AS `birthday`,`u`.`province` AS `province`,`u`.`city` AS `city` from (((select `a`.`user_id` AS `user_id`,`a`.`ques_id` AS `ques_id`,`a`.`type` AS `type`,`a`.`name_type` AS `name_type`,`a`.`content` AS `content`,`a`.`ans_creatime` AS `ans_creatime`,`q`.`question` AS `question` from (`schoolapp`.`answer` `a` join `schoolapp`.`questions` `q` on((`a`.`ques_id` = `q`.`ques_id`))))) `w` join `schoolapp`.`user` `u` on((`w`.`user_id` = `u`.`user_id`))) order by `w`.`ans_creatime` desc ;

-- ----------------------------
-- View structure for user_info
-- ----------------------------
DROP VIEW IF EXISTS `user_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_info` AS select distinct `u`.`user_id` AS `user_id`,`f`.`f_user_id` AS `f_user_id`,`f`.`fans_see_state` AS `fans_see_state`,`u`.`level` AS `level`,`u`.`user_nickname` AS `user_nickname` from (`user` `u` join `friends` `f` on((`u`.`user_id` = `f`.`user_id`))) ;

-- ----------------------------
-- View structure for 创建朋友圈的子查询
-- ----------------------------
DROP VIEW IF EXISTS `创建朋友圈的子查询`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `创建朋友圈的子查询` AS select distinct `u`.`user_id` AS `user_id`,`f`.`f_user_id` AS `f_user_id`,`f`.`fans_see_state` AS `fans_see_state`,`u`.`level` AS `level`,`u`.`user_nickname` AS `user_nickname` from (`user` `u` join `friends` `f` on((`u`.`user_id` = `f`.`user_id`))) ;

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

-- ----------------------------
-- Event structure for timing_delete_questions
-- ----------------------------
DROP EVENT IF EXISTS `timing_delete_questions`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `timing_delete_questions` ON SCHEDULE EVERY 1 DAY STARTS '2017-01-19 03:00:00' ON COMPLETION NOT PRESERVE ENABLE DO -- 每天删除碰碰的问题发布中超过30天的问题
DELETE from questions where DATEDIFF(CURRENT_DATE(),(select DATE_FORMAT(ques_creatime,"%Y-%m-%d")))>30
;;
DELIMITER ;
