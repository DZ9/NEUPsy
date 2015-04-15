-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.11 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 neupsy 的数据库结构
CREATE DATABASE IF NOT EXISTS `neupsy` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `neupsy`;


-- 导出  表 neupsy.t_appointment 结构
CREATE TABLE IF NOT EXISTS `t_appointment` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `stuNum` varchar(20) DEFAULT NULL,
  `eTeacher` varchar(20) DEFAULT NULL,
  `aTeacher` varchar(20) DEFAULT NULL,
  `receiver` varchar(20) DEFAULT NULL,
  `stuPhone` varchar(30) DEFAULT NULL,
  `eTime` datetime DEFAULT NULL,
  `aTime` datetime DEFAULT NULL,
  `way` int(5) DEFAULT NULL,
  `aspect` int(5) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `rDate` date DEFAULT NULL,
  `checked` bit(1) DEFAULT NULL,
  `stuName` varchar(100) DEFAULT NULL,
  `college` varchar(100) DEFAULT NULL,
  `content` mediumtext,
  `suggestion` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 neupsy.t_aspect 结构
CREATE TABLE IF NOT EXISTS `t_aspect` (
  `id` int(5) NOT NULL,
  `aspect` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 neupsy.t_authority 结构
CREATE TABLE IF NOT EXISTS `t_authority` (
  `id` int(5) DEFAULT NULL,
  `authority` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 neupsy.t_music 结构
CREATE TABLE IF NOT EXISTS `t_music` (
  `stuNum` int(20) DEFAULT NULL,
  `symptom` varchar(200) DEFAULT NULL,
  `rDate` date DEFAULT NULL,
  `eDate` varchar(100) DEFAULT NULL,
  `eTime` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `type` varchar(200) DEFAULT NULL,
  `advice` varchar(6000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 neupsy.t_student 结构
CREATE TABLE IF NOT EXISTS `t_student` (
  `stuNum` int(10) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `college` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `interest` varchar(500) DEFAULT NULL,
  `nation` varchar(20) DEFAULT NULL,
  `hometown` varchar(200) DEFAULT NULL,
  `grade` varchar(50) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`stuNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 neupsy.t_stuinfo 结构
CREATE TABLE IF NOT EXISTS `t_stuinfo` (
  `stuNum` int(20) NOT NULL DEFAULT '0',
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`stuNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 neupsy.t_teacher 结构
CREATE TABLE IF NOT EXISTS `t_teacher` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `authority` int(5) DEFAULT NULL,
  `description` varchar(8000) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 neupsy.t_way 结构
CREATE TABLE IF NOT EXISTS `t_way` (
  `id` int(5) NOT NULL,
  `way` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
