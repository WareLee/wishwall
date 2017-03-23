/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : dreamwall2

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2017-03-11 21:29:29
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES ('3', '你说的确实没错,三号', '2016-12-01 21:19:20', '1', '1', 'pray', '2', '是匿名的', '我是来自1的匿名');
INSERT INTO `t_message` VALUES ('15', '15', '2016-12-03 00:15:42', '0', '2', 'pray', '3', 'fa', null);
INSERT INTO `t_message` VALUES ('16', '16', '2016-12-03 00:16:12', '1', '2', 'pray', '3', 'g', null);
INSERT INTO `t_message` VALUES ('20', '20', '2016-12-03 00:17:06', '0', '0', 'pray', '3', 'iuu', null);
INSERT INTO `t_message` VALUES ('21', '21', '2016-12-03 00:17:20', '0', '2', 'pray', '2', 'ttt', null);
INSERT INTO `t_message` VALUES ('22', '22', '2016-12-03 00:17:32', '1', '1', 'pray', '3', 'ttty', null);
INSERT INTO `t_message` VALUES ('55', '厉害了', '2016-12-08 13:20:31', '1', '1', 'pray', '1', '哈哈', '。。');
INSERT INTO `t_message` VALUES ('58', 'sssssssssr', '2016-12-08 13:22:57', '0', '1', 'pray', '1', 'lll', '小七子');
INSERT INTO `t_message` VALUES ('59', '打字666', '2016-12-08 13:22:59', '0', '0', 'pray', '1', '刘宇林', '小七子');
INSERT INTO `t_message` VALUES ('60', '僵硬啊', '2016-12-24 00:12:07', '0', '1', 'complain', '1', 'you', '小七子');
INSERT INTO `t_message` VALUES ('61', '好像排序是实时更新的，不应该是存在application里的么', '2016-12-24 00:13:57', '0', '1', 'complain', '1', '排序', '小七子');
INSERT INTO `t_message` VALUES ('62', '抱怨类型', '2016-12-24 00:14:32', '1', '1', 'complain', '1', '抱怨', '小七子');
INSERT INTO `t_message` VALUES ('64', '呵呵哒', '2016-12-24 15:05:05', '0', '0', 'pray', '1', '油', '小七子');
INSERT INTO `t_message` VALUES ('65', 'emoji表情        ', '2016-12-24 15:31:12', '0', '0', 'pray', '1', '油', '小七子');
INSERT INTO `t_message` VALUES ('68', '真的烦', '2016-12-24 16:02:11', '0', '2', 'complain', '1', 'hh', '小七子');
INSERT INTO `t_message` VALUES ('69', '哈哈/::> /::> ', '2016-12-24 17:00:13', '0', '0', 'pray', '1', 'wandful', '小七子');
INSERT INTO `t_message` VALUES ('70', '哈图够我兔兔', '2016-12-24 17:00:59', '0', '0', 'pray', '1', '亲爱的小塔', '小七子');
INSERT INTO `t_message` VALUES ('71', '你说呐', '2016-12-24 17:04:28', '0', '0', 'sayhi', '1', 'the next', '小七子');
INSERT INTO `t_message` VALUES ('72', '嘎嘎嘎牛犊子裤子在KTV曲目兔兔KKKKKK\r\nKKK你54789只\r\ngcvmFM墨汁\r\n\r\n\r\n\r\n\r\nfox祝我哦哟哟\r\n麻辣香锅', '2016-12-24 17:07:53', '0', '0', 'pray', '1', '/:kn 刀', '小七子');
INSERT INTO `t_message` VALUES ('73', '最新', '2017-03-10 09:43:41', '0', '13', 'pray', '1', '匿名', '当当');
INSERT INTO `t_message` VALUES ('76', '特别要注意的是有两个不同的Access Token，他们产生的方式不一样，一种是使用AppID和AppSecret获取特别要注意的是有两个不同的Access', '2017-03-10 22:06:50', '1', '1', 'sayhi', '2', '当当', '兮兮');
INSERT INTO `t_message` VALUES ('78', '他们产生的方式不一样，一种是使用AppID和AppSecre', '2017-03-10 22:10:42', '0', '1', 'pray', '2', '淘啊淘', '兮兮');
INSERT INTO `t_message` VALUES ('84', '方方土lz你YY', '2017-03-11 00:54:48', '0', '0', 'pray', '2', '埠村', '兮兮');
INSERT INTO `t_message` VALUES ('85', 'adfasffda嘎达刚打过郭德纲', '2017-03-11 11:33:25', '0', '0', 'sayhi', '2', 'ddd', '兮兮');

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
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'oXxykvyvykLqNk34Ohg36R6Ff3GM', '小七子', 'http://wx.qlogo.cn/mmopen/ajNVdqHZLLA3lXIU8uLTicaVEFp8rwxAKwAWficyVLGkQVd8kgKNVQoCIDJ90JafPBXjHMOccic5xzQRpcEbSNICg/0', '学以明志', 'bg_default.jpg');
INSERT INTO `t_user` VALUES ('2', 'ksjakflsafjienxnxlafooo', '兮兮', '../img/rBACE1TA2qfQ2FScAAAQoyZ33Vo309_200x200_3.jpg', '爱不完', 'bg_default.jpg');
INSERT INTO `t_user` VALUES ('3', 'sdafkooo', '3号', '../img/20141210221211_3BjNN.thumb.224_0.jpeg', '我的签名...', 'bg_default.jpg');
INSERT INTO `t_user` VALUES ('4', 'dddddddddd', 'didi', '../img/default.jpg', '我的签名...', 'bg_default.jpg');
INSERT INTO `t_user` VALUES ('5', 'hihiih', 'ozoz', 'defaultha.png', '我的签名...', 'bg_default.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_zan
-- ----------------------------
INSERT INTO `t_zan` VALUES ('71', '1', '15', '1');
INSERT INTO `t_zan` VALUES ('83', '1', '22', '1');
INSERT INTO `t_zan` VALUES ('87', '1', '55', '1');
INSERT INTO `t_zan` VALUES ('88', '1', '16', '1');
INSERT INTO `t_zan` VALUES ('92', '1', '58', '1');
INSERT INTO `t_zan` VALUES ('93', '1', '21', '1');
INSERT INTO `t_zan` VALUES ('94', '1', '62', '1');
INSERT INTO `t_zan` VALUES ('95', '1', '61', '1');
INSERT INTO `t_zan` VALUES ('97', '1', '68', '1');
INSERT INTO `t_zan` VALUES ('98', '73', '1', '1');
INSERT INTO `t_zan` VALUES ('100', '2', '16', '1');
INSERT INTO `t_zan` VALUES ('102', '2', '78', '1');
INSERT INTO `t_zan` VALUES ('104', '2', '76', '1');
INSERT INTO `t_zan` VALUES ('105', '2', '60', '1');
INSERT INTO `t_zan` VALUES ('106', '2', '3', '1');
INSERT INTO `t_zan` VALUES ('107', '2', '68', '1');
INSERT INTO `t_zan` VALUES ('109', '2', '15', '1');
INSERT INTO `t_zan` VALUES ('111', '2', '73', '1');
INSERT INTO `t_zan` VALUES ('113', '2', '21', '1');
