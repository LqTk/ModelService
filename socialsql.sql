/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : socialsql

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2021-01-08 17:21:58
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
  `msgContent` varchar(255) DEFAULT NULL,
  `voiceTime` varchar(11) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `chatTime` datetime DEFAULT NULL,
  PRIMARY KEY (`chatId`)
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
  PRIMARY KEY (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_partner
-- ----------------------------
DROP TABLE IF EXISTS `social_partner`;
CREATE TABLE `social_partner` (
  `id` varchar(255) NOT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `partnerId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_public
-- ----------------------------
DROP TABLE IF EXISTS `social_public`;
CREATE TABLE `social_public` (
  `userId` varchar(32) NOT NULL DEFAULT '1',
  `userName` varchar(255) DEFAULT NULL,
  `userHead` varchar(255) DEFAULT NULL,
  `userSex` int(1) DEFAULT '1',
  `shareId` varchar(32) NOT NULL,
  `shareName` varchar(255) DEFAULT NULL,
  `shareUrl` varchar(255) DEFAULT NULL,
  `shareText` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isPublic` int(1) DEFAULT '0',
  `goodsCount` int(11) DEFAULT '0',
  `reviewCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`shareId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for social_review
-- ----------------------------
DROP TABLE IF EXISTS `social_review`;
CREATE TABLE `social_review` (
  `reviewId` varchar(32) NOT NULL,
  `peopleId` varchar(32) NOT NULL,
  `publicId` varchar(32) NOT NULL,
  `reviewText` varchar(255) NOT NULL,
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
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sex` int(1) DEFAULT '1',
  `birthday` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT '0',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
