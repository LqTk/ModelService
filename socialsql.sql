/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : socialsql

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2021-02-23 17:26:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for social_chat
-- ----------------------------
DROP TABLE IF EXISTS `social_chat`;
CREATE TABLE `social_chat` (
  `chatId` varchar(255) NOT NULL,
  `talkId` varchar(255) DEFAULT NULL,
  `toId` varchar(255) DEFAULT NULL,
  `msgType` varchar(255) DEFAULT NULL,
  `msgContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `voiceTime` varchar(11) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `chatTime` datetime DEFAULT NULL,
  PRIMARY KEY (`chatId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_chat_review
-- ----------------------------
DROP TABLE IF EXISTS `social_chat_review`;
CREATE TABLE `social_chat_review` (
  `reviewChatId` varchar(32) NOT NULL,
  `reviewId` varchar(32) NOT NULL,
  `talkId` varchar(32) NOT NULL,
  `toId` varchar(32) NOT NULL,
  `chatText` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `chatTime` datetime NOT NULL,
  PRIMARY KEY (`reviewChatId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_goods
-- ----------------------------
DROP TABLE IF EXISTS `social_goods`;
CREATE TABLE `social_goods` (
  `goodsId` varchar(32) NOT NULL,
  `peopleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `publicId` varchar(32) NOT NULL,
  `peopleName` varchar(255) DEFAULT NULL,
  `peopleHead` varchar(255) DEFAULT NULL,
  `goodsTime` datetime DEFAULT NULL,
  PRIMARY KEY (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_msg
-- ----------------------------
DROP TABLE IF EXISTS `social_msg`;
CREATE TABLE `social_msg` (
  `msgId` varchar(32) NOT NULL,
  `msgType` varchar(255) NOT NULL,
  `publishId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `peopleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reviewId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `chatReviewId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `readed` int(1) DEFAULT '0',
  `goodsId` varchar(32) DEFAULT NULL,
  `msgTime` datetime DEFAULT NULL,
  PRIMARY KEY (`msgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_partner
-- ----------------------------
DROP TABLE IF EXISTS `social_partner`;
CREATE TABLE `social_partner` (
  `id` varchar(255) NOT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `partnerId` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_public
-- ----------------------------
DROP TABLE IF EXISTS `social_public`;
CREATE TABLE `social_public` (
  `userId` varchar(32) NOT NULL DEFAULT '1',
  `shareId` varchar(32) NOT NULL,
  `shareName` varchar(255) DEFAULT NULL,
  `shareUrl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `shareText` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `createTime` datetime DEFAULT NULL,
  `isPublic` int(1) DEFAULT '0',
  `type` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `latitude` double(11,6) DEFAULT NULL,
  `longitude` double(11,6) DEFAULT NULL,
  `reportCount` int(2) DEFAULT '0',
  PRIMARY KEY (`shareId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_report_publish
-- ----------------------------
DROP TABLE IF EXISTS `social_report_publish`;
CREATE TABLE `social_report_publish` (
  `reportId` varchar(32) NOT NULL,
  `publishId` varchar(32) NOT NULL,
  `reportUserId` varchar(32) NOT NULL,
  `text` text,
  `img` text,
  PRIMARY KEY (`reportId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_review
-- ----------------------------
DROP TABLE IF EXISTS `social_review`;
CREATE TABLE `social_review` (
  `reviewId` varchar(32) NOT NULL,
  `peopleId` varchar(32) NOT NULL,
  `publicId` varchar(32) NOT NULL,
  `reviewText` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reviewTime` datetime DEFAULT NULL,
  PRIMARY KEY (`reviewId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_user
-- ----------------------------
DROP TABLE IF EXISTS `social_user`;
CREATE TABLE `social_user` (
  `id` varchar(32) NOT NULL,
  `registrationId` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `sex` int(1) DEFAULT '1',
  `birthday` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `latitude` double(11,6) DEFAULT NULL,
  `longitude` double(11,6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
