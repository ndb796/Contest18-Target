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
-- Table structure for table `ds_search_univ_tx`
--

DROP TABLE IF EXISTS `ds_search_univ_tx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_search_univ_tx` (
  `SCH_ID` varchar(15) NOT NULL,
  `ID` int(11) NOT NULL,
  `SCHOOL_ID` varchar(15) DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL,
  `MEMO` varchar(200) DEFAULT NULL,
  `YOMIGANA` varchar(200) DEFAULT NULL,
  `SHT_NAME` varchar(200) DEFAULT NULL,
  `SHT_NAME_YG` varchar(200) DEFAULT NULL,
  `SCH_CATE_ID` varchar(200) DEFAULT NULL,
  `SCH_FOUNDER_ID` varchar(200) DEFAULT NULL,
  `URL_PC` varchar(200) DEFAULT NULL,
  `URL_MOBILE` varchar(200) DEFAULT NULL,
  `URL_CAREER_PC` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `ZIPCODE` varchar(50) DEFAULT NULL,
  `PREFECTURE_ID` varchar(200) DEFAULT NULL,
  `ADDRESS` varchar(200) DEFAULT NULL,
  `ADDRESS1` varchar(200) DEFAULT NULL,
  `ADDRESS2` varchar(200) DEFAULT NULL,
  `ADDRESS3` varchar(200) DEFAULT NULL,
  `ADDRESS4` varchar(200) DEFAULT NULL,
  `TEL` varchar(50) DEFAULT NULL,
  `TEL_MEMO` varchar(300) DEFAULT NULL,
  `FREEDIAL` varchar(100) DEFAULT NULL,
  `FREEDIAL_MEMO` varchar(300) DEFAULT NULL,
  `FAX` varchar(50) DEFAULT NULL,
  `FOUNDYEAR` varchar(20) DEFAULT NULL,
  `MEMO_PAST` varchar(500) DEFAULT NULL,
  `PAMPHLET` varchar(200) DEFAULT NULL,
  `PAMPHLETSTART` varchar(100) DEFAULT NULL,
  `SCH_STATUS_CONTRACT` int(11) DEFAULT NULL,
  `SCH_STATUS` int(11) DEFAULT NULL,
  `INS_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DEL_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DEL_FLG` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`SCH_ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_search_univ_tx`
--

LOCK TABLES `ds_search_univ_tx` WRITE;
/*!40000 ALTER TABLE `ds_search_univ_tx` DISABLE KEYS */;
INSERT INTO `ds_search_univ_tx` VALUES ('USCH0000001',1000,NULL,'서울대학교','','서울대학교','서울','서울','10','3','http://www.google.com','http://www.google.com','','test@myschoo.com','079-8501','10','서울시 관악구 123-1','','서울시','관악구 123-1',NULL,'0166-48-3121','대','0120-48-3124','입사행정과','','','','0','6월',1,1,'2018-07-18 07:48:18','0000-00-00 00:00:00','0000-00-00 00:00:00',NULL),('USCH0000002',1001,NULL,'고려대학교','','고려대학교','고려','고려','10','1','http://www.google.com','','','','078-8510','10','서울시 성동구 222-3','','서울시','성동구 222-3',NULL,'0166-68-2214','정보입시과','','','','','','1','8월',1,1,'2018-07-18 07:48:18','0000-00-00 00:00:00','0000-00-00 00:00:00',NULL),('USCH0000003',1002,NULL,'연세대학교','','연세대학교','연세','연세','10','1','http://www.google.com','','http://www.naver.com','test@ssaa.com','047-8501','10','서울시 마포구 555-4','','서울시','마포구 555-4',NULL,'0134-27-5254','입시행정과','','','','','','1','7월',1,1,'2018-07-18 07:48:18','0000-00-00 00:00:00','0000-00-00 00:00:00',NULL),('USCH0000004',1003,NULL,'성균관대학교','','성균관대학교','성균관','성균관','10','1','http://www.google.com','','http://www.yahoo.com','test@yana.com','080-8555','10','서울시 동대문구 88-12','','서울시','동대문구 88-12',NULL,'0155-49-5321','입시입학과','','','','','','1','7월',1,1,'2018-07-18 07:48:18','0000-00-00 00:00:00','0000-00-00 00:00:00',NULL),('USCH0000005',1004,NULL,'한국대학교','','서강대학교','서강','서강','10','1','http://www.google.com','','http://www.google.com','googlr@test.com','090-8507','10','서울시 강남구 5521','','서울시','강남구 5521',NULL,'0157-26-9167','입학행정담당과','','','','','','1','5월',1,1,'2018-07-18 07:48:18','0000-00-00 00:00:00','0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `ds_search_univ_tx` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-23 23:34:31
