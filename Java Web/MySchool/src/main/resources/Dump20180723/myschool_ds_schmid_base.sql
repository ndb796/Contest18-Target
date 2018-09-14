-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: myschool
-- ------------------------------------------------------
-- Server version	5.1.46-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ds_schmid_base`
--

DROP TABLE IF EXISTS `ds_schmid_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_schmid_base` (
  `IDX` int(11) NOT NULL AUTO_INCREMENT,
  `SCH_ID` varchar(15) DEFAULT NULL,
  `ID` int(11) DEFAULT NULL,
  `SCHOOL_NAME` varchar(200) DEFAULT NULL,
  `SCH_CATEGORY` varchar(50) DEFAULT NULL,
  `SCH_GUBUN` varchar(10) DEFAULT NULL,
  `HIRAGANA` varchar(400) DEFAULT NULL,
  `PREFECTURE_ID` int(2) DEFAULT NULL,
  `ZIPCODE` varchar(10) DEFAULT NULL,
  `ADDRESS` varchar(500) DEFAULT NULL,
  `GOOGLE` varchar(500) DEFAULT NULL,
  `TEL` varchar(100) DEFAULT NULL,
  `URL` varchar(100) DEFAULT NULL,
  `INS_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`IDX`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_schmid_base`
--

LOCK TABLES `ds_schmid_base` WRITE;
/*!40000 ALTER TABLE `ds_schmid_base` DISABLE KEYS */;
INSERT INTO `ds_schmid_base` VALUES (1,'MSCH0000001',40010,'제일중학교','국립','공학','제일중학교',10,'11111','서울시 강남구 도곡동 1','https://www.google.co.kr/maps/place/%E5%BE%80%E5%8D%81%E9%87%8C%E9%A7%85/@37.562522,127.0315208,17z/data=!4m8!1m2!2m1!1z7JmV7Iut66as7Jet!3m4!1s0x357ca359391d9747:0x3f7f791155a18366!8m2!3d37.5611284!4d127.035505','03-5978-5865','http://www.google.co.kr','2018-07-17 15:43:09'),(2,'MSCH0000005',40050,'한국중학교','국립','공학','한국중학교',10,'178-0063','서울 광진구 2-22-1','<a href=\"http://maps.google.co.jp/maps?f=q&hl=ja&q=%e6%9d%b1%e4%ba%ac%e9%83%bd%e7%b7%b4%e9%a6%ac%e5%8c%ba%e6%9d%b1%e5%a4%a7%e6%b3%895-22-1\" target=\"_blank\">google 지도 표시</a>','03-5905-1326','http://www.google.co.kr','2018-06-23 08:46:34'),(3,'MSCH0000002',40020,'서울중학교','국립','공학','서울중학교',10,'112-0012','서울 동작구 1-9-1','<a href=\"http://maps.google.co.jp/maps?f=q&hl=ja&q=%e6%9d%b1%e4%ba%ac%e9%83%bd%e6%96%87%e4%ba%ac%e5%8c%ba%e5%a4%a7%e5%a1%9a1-9-1\" target=\"_blank\">google 지도 표시</a>','03-3945-3231','http://www.google.co.kr','2018-06-23 08:46:58'),(4,'MSCH0000003',40030,'서울시립중학교','시립','남자','서울시립중학교',10,'154-0001','서울 용산구 1-7-1','<a href=\"http://maps.google.co.jp/maps?f=q&hl=ja&q=%e6%9d%b1%e4%ba%ac%e9%83%bd%e4%b8%96%e7%94%b0%e8%b0%b7%e5%8c%ba%e6%b1%a0%e5%b0%bb4-7-1\" target=\"_blank\">google 지도 표시</a>','03-3411-8521','http://www.google.co.kr','2018-06-23 08:46:58'),(5,'MSCH0000004',40040,'시청중학교','국립','공학','시청중학교',10,'184-8501','서울 종로구 2-1-1','<a href=\"http://maps.google.co.jp/maps?f=q&hl=ja&q=%e6%9d%b1%e4%ba%ac%e9%83%bd%e5%b0%8f%e9%87%91%e4%ba%95%e5%b8%82%e8%b2%ab%e4%ba%95%e5%8c%97%e7%94%ba4-1-1\" target=\"_blank\">google 지도 표시</a>','042-329-7833','http://www.google.co.kr','2018-06-23 08:46:58');
/*!40000 ALTER TABLE `ds_schmid_base` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-23 23:34:37
