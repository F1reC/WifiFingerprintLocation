/*
 Navicat Premium Data Transfer

 Source Server         : MySQL1
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3307
 Source Schema         : rssi1

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 15/07/2022 16:58:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information`  (
  `X` float(255, 0) NULL DEFAULT NULL,
  `Y` float(255, 0) NULL DEFAULT NULL,
  `AP1` float(255, 0) NULL DEFAULT NULL,
  `AP2` float(255, 0) NULL DEFAULT NULL,
  `AP3` float(255, 0) NULL DEFAULT NULL,
  `AP4` float(255, 0) NULL DEFAULT NULL,
  `AP5` float(255, 0) NULL DEFAULT NULL,
  `Remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
