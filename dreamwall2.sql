/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : dreamwall2

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2017-05-28 00:40:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_bgs`
-- ----------------------------
DROP TABLE IF EXISTS `t_bgs`;
CREATE TABLE `t_bgs` (
  `bid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bgname` varchar(255) NOT NULL,
  `bgaddress` varchar(255) NOT NULL,
  `provider` int(10) unsigned NOT NULL,
  PRIMARY KEY (`bid`),
  KEY `provider` (`provider`),
  CONSTRAINT `t_bgs_ibfk_1` FOREIGN KEY (`provider`) REFERENCES `t_user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bgs
-- ----------------------------
INSERT INTO `t_bgs` VALUES ('1', '默认', 'wish.png', '1');
INSERT INTO `t_bgs` VALUES ('2', '深枫', 'wish_bg01.png', '1');
INSERT INTO `t_bgs` VALUES ('3', '橙枫', 'wish_bg02.png', '1');
INSERT INTO `t_bgs` VALUES ('4', '绿枫', 'wish_bg03.png', '1');
INSERT INTO `t_bgs` VALUES ('5', '灰枫', 'wish_bg04.png', '1');
INSERT INTO `t_bgs` VALUES ('6', 'xF0x9Fx8DxAC', 'ddd', '5');
INSERT INTO `t_bgs` VALUES ('7', '\\xF0\\x9F\\x8D\\xAC', 'ddd', '5');

-- ----------------------------
-- Table structure for `t_message`
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `detailtime` timestamp NULL DEFAULT NULL,
  `anonymity` tinyint(1) NOT NULL DEFAULT '0',
  `zantimes` int(11) unsigned NOT NULL DEFAULT '0',
  `mtype` varchar(45) NOT NULL DEFAULT 'pray',
  `uid` int(10) unsigned NOT NULL,
  `toyou` varchar(45) NOT NULL DEFAULT '匿名',
  `fromname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES ('76', '特别要注意的是有两个不同的Access Token，他们产生的方式不一样，一种是使用AppID和AppSecret获取特别要注意的是有两个不同的Access', '2017-04-17 22:06:50', '1', '0', 'sayhi', '2', '当当', '兮兮');
INSERT INTO `t_message` VALUES ('78', '他们产生的方式不一样，一种是使用AppID和AppSecre', '2017-04-17 22:10:42', '0', '2', 'pray', '2', '淘啊淘', '兮兮');
INSERT INTO `t_message` VALUES ('84', '方方土lz你YY', '2017-03-11 00:54:48', '0', '3', 'pray', '2', '埠村', '兮兮');
INSERT INTO `t_message` VALUES ('116', '真的', '2017-04-25 15:57:36', '0', '0', 'sayhi', '2', '你好', '兮兮');
INSERT INTO `t_message` VALUES ('118', '户口', '2017-04-25 15:58:04', '1', '0', 'pray', '2', '怪', '莫');
INSERT INTO `t_message` VALUES ('119', '兔子在真做最我', '2017-04-25 15:58:44', '0', '0', 'pray', '2', '你哈拖', '兮兮');
INSERT INTO `t_message` VALUES ('121', '啊啊啊啊啊具体', '2017-04-25 15:59:43', '0', '0', 'complain', '1', '测试', '管理员');
INSERT INTO `t_message` VALUES ('122', 'highhigh', '2017-04-25 15:59:59', '0', '0', 'pray', '1', '呱呱', '管理员');
INSERT INTO `t_message` VALUES ('123', '就考虑咯啦咯啦咯啦咯啦咯', '2017-04-25 16:00:16', '0', '0', 'sayhi', '1', '好的', '管理员');
INSERT INTO `t_message` VALUES ('125', '叭叭叭', '2017-04-25 16:00:49', '0', '1', 'complain', '1', '唠唠嗑', '管理员');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(45) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `headimgurl` varchar(256) NOT NULL DEFAULT '../img/default.jpg',
  `signature` varchar(512) NOT NULL DEFAULT '我的签名...',
  `bground` varchar(256) NOT NULL DEFAULT 'bg_default.jpg',
  `sysnotify` int(10) unsigned NOT NULL DEFAULT '1',
  `usenotify` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'g36R6Ff3GM', '小小', 'ddd', '管理员', 'wish.png', '1', '1');
INSERT INTO `t_user` VALUES ('2', 'ksjakflsafjienxnxlafooo', '兮兮', '../img/rBACE1TA2qfQ2FScAAAQoyZ33Vo309_200x200_3.jpg', '爱不完', 'wish.png', '1', '1');
INSERT INTO `t_user` VALUES ('3', 'sdafkooo', '3号', '../img/20141210221211_3BjNN.thumb.224_0.jpeg', '我的签名...', 'wish.png', '1', '1');
INSERT INTO `t_user` VALUES ('4', 'dddddddddd', 'didi', '../img/default.jpg', '我的签名...', 'wish.png', '1', '1');
INSERT INTO `t_user` VALUES ('5', 'hihiih', 'ozoz', '../img/default.jpg', '我的签名...', 'wish.png', '1', '1');

-- ----------------------------
-- Table structure for `t_zan`
-- ----------------------------
DROP TABLE IF EXISTS `t_zan`;
CREATE TABLE `t_zan` (
  `zid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) unsigned NOT NULL,
  `mid` int(11) unsigned NOT NULL,
  `sstate` tinyint(1) NOT NULL,
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_zan
-- ----------------------------
INSERT INTO `t_zan` VALUES ('87', '1', '55', '1');
INSERT INTO `t_zan` VALUES ('88', '1', '16', '1');
INSERT INTO `t_zan` VALUES ('92', '1', '58', '1');
INSERT INTO `t_zan` VALUES ('94', '1', '62', '1');
INSERT INTO `t_zan` VALUES ('95', '1', '61', '1');
INSERT INTO `t_zan` VALUES ('97', '1', '68', '1');
INSERT INTO `t_zan` VALUES ('98', '73', '1', '1');
INSERT INTO `t_zan` VALUES ('100', '2', '16', '1');
INSERT INTO `t_zan` VALUES ('105', '2', '60', '1');
INSERT INTO `t_zan` VALUES ('107', '2', '68', '1');
INSERT INTO `t_zan` VALUES ('111', '2', '73', '1');
INSERT INTO `t_zan` VALUES ('116', '2', '64', '1');
INSERT INTO `t_zan` VALUES ('132', '2', '78', '1');
INSERT INTO `t_zan` VALUES ('139', '2', '84', '1');
INSERT INTO `t_zan` VALUES ('140', '1', '78', '1');
INSERT INTO `t_zan` VALUES ('141', '2', '125', '1');
