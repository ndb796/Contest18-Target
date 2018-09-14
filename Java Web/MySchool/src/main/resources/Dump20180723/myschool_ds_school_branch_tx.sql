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
-- Table structure for table `ds_school_branch_tx`
--

DROP TABLE IF EXISTS `ds_school_branch_tx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_school_branch_tx` (
  `IDX` int(11) NOT NULL AUTO_INCREMENT,
  `SB_ID` varchar(10) NOT NULL,
  `SCHOOL_ID` varchar(15) NOT NULL,
  `SB_TYPE` tinyint(4) DEFAULT NULL,
  `SB_CATEGORY` tinyint(4) DEFAULT NULL COMMENT '1-ELEMENTARY\n2-MIDDLE\n3-HIGH\n4-COLLEGE\n5-UNIVERSITY\n6-GRADUATE\n7-ABROAD\n',
  `SB_ORD` tinyint(4) DEFAULT NULL,
  `SB_NAME` varchar(200) DEFAULT NULL,
  `SB_TITLE1` varchar(200) DEFAULT NULL,
  `SB_TITLE2` varchar(200) DEFAULT NULL,
  `SB_CONTENTS` varchar(2048) DEFAULT NULL,
  `SB_FEATURES` varchar(2048) DEFAULT NULL,
  `SB_POLICY` varchar(2048) DEFAULT NULL,
  `SB_TEL` varchar(50) DEFAULT NULL,
  `SB_FAX` varchar(50) DEFAULT NULL,
  `SB_DEPT` varchar(100) DEFAULT NULL,
  `SB_ZIPCODE` varchar(20) DEFAULT NULL,
  `SB_PREFECTURE_ID` varchar(2) DEFAULT NULL,
  `SB_ADDRESS1` varchar(200) DEFAULT NULL,
  `SB_ADDRESS2` varchar(200) DEFAULT NULL,
  `SB_ADDRESS3` varchar(200) DEFAULT NULL,
  `SB_ADDRESS4` varchar(200) DEFAULT NULL,
  `SB_ACCESS` varchar(1024) DEFAULT NULL,
  `PHOTO1_NAME` varchar(200) DEFAULT NULL,
  `PHOTO1_SIZE` bigint(20) DEFAULT NULL,
  `ORG1_NAME` varchar(200) DEFAULT NULL,
  `PHOTO2_NAME` varchar(200) DEFAULT NULL,
  `PHOTO2_SIZE` bigint(20) DEFAULT NULL,
  `ORG2_NAME` varchar(200) DEFAULT NULL,
  `INS_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPD_ID` varchar(10) DEFAULT NULL,
  `DEL_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DEL_FLG` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`SB_ID`),
  UNIQUE KEY `IDX` (`IDX`),
  KEY `SCHOOL_ID` (`SCHOOL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_school_branch_tx`
--

LOCK TABLES `ds_school_branch_tx` WRITE;
/*!40000 ALTER TABLE `ds_school_branch_tx` DISABLE KEYS */;
INSERT INTO `ds_school_branch_tx` VALUES (1,'ABRN000002','ASCH0000001',3,NULL,NULL,'서울고등학교',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'003-0809','10','서울시','강남구','개포동 321',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-07-18 07:49:17','0000-00-00 00:00:00',NULL,'0000-00-00 00:00:00',NULL),(2,'ABRN000003','ASCH0000002',3,NULL,NULL,'한국고등학교',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'064-8624','10','서울시','강북구','길동 123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-07-18 07:49:17','0000-00-00 00:00:00',NULL,'0000-00-00 00:00:00',NULL),(3,'ABRN000004','ASCH0000003',3,NULL,NULL,'경기고등학교',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'869-3603','10','서울시','강서구','화곡동 23-1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-07-18 07:49:17','0000-00-00 00:00:00',NULL,'0000-00-00 00:00:00',NULL),(4,'ABRN000005','ASCH0000004',3,NULL,NULL,'남산고등학교',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'569-1098','10','서울시','강동구','천호동 111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-07-18 07:49:17','0000-00-00 00:00:00',NULL,'0000-00-00 00:00:00',NULL),(5,'ABRN000006','ASCH0000005',3,NULL,NULL,'대한고등학교',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'631-8522','10','서울시','성동구','왕십리 1-2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-07-18 07:49:17','0000-00-00 00:00:00',NULL,'0000-00-00 00:00:00',NULL),(6,'BRN0000007','SCH0000005',2,NULL,0,'test 중','',NULL,'','ㄹㅇㄴㄹ','ㄹㅇㄴ','','','담당','1111','10','서울','','','','','SCH0000005_BRN0000007_01_1531771638250.jpg',34879,'i1.jpg','SCH0000005_BRN0000007_02_1531771638256.jpg',59290,'i4.jpg','2018-07-18 07:49:17','2018-07-16 20:12:06',NULL,'0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `ds_school_branch_tx` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-23 23:34:34
