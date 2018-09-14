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
-- Table structure for table `ds_student_sub2_tx`
--

DROP TABLE IF EXISTS `ds_student_sub2_tx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_student_sub2_tx` (
  `IDX` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` varchar(15) NOT NULL,
  `SCHOOL1` varchar(100) DEFAULT NULL,
  `SCHOOL2` varchar(100) DEFAULT NULL,
  `SCHOOL3` varchar(100) DEFAULT NULL,
  `LOCATION1` varchar(100) DEFAULT NULL,
  `LOCATION2` varchar(100) DEFAULT NULL,
  `LOCATION3` varchar(100) DEFAULT NULL,
  `SUBJECT1` varchar(100) DEFAULT NULL,
  `SUBJECT2` varchar(100) DEFAULT NULL,
  `SUBJECT3` varchar(100) DEFAULT NULL,
  `JOB1` varchar(100) DEFAULT NULL,
  `JOB2` varchar(100) DEFAULT NULL,
  `JOB3` varchar(100) DEFAULT NULL,
  `FIELD1` varchar(100) DEFAULT NULL,
  `FIELD2` varchar(100) DEFAULT NULL,
  `FIELD3` varchar(100) DEFAULT NULL,
  `REMARKS` varchar(2048) DEFAULT NULL,
  `SCHOOL_ORD` tinyint(4) DEFAULT NULL,
  `SUBJECT_ORD` tinyint(4) DEFAULT NULL,
  `LOCATION_ORD` tinyint(4) DEFAULT NULL,
  `JOB_ORD` tinyint(4) DEFAULT NULL,
  `FIELD_ORD` tinyint(4) DEFAULT NULL,
  `INS_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`STUDENT_ID`),
  UNIQUE KEY `IDX` (`IDX`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_student_sub2_tx`
--

LOCK TABLES `ds_student_sub2_tx` WRITE;
/*!40000 ALTER TABLE `ds_student_sub2_tx` DISABLE KEYS */;
INSERT INTO `ds_student_sub2_tx` VALUES (1,'STD0000021','A학교','B학교','','서울','경기도','','','','','','','','','','',NULL,1,3,2,4,5,'2018-07-16 16:00:07','2018-07-16 16:00:07'),(2,'STD0000034','서울중학교','한국중학교','test 중','tse','test','tset','tes','test','tse','ts','tse','tse','','','',NULL,1,3,2,4,5,'2018-07-18 04:25:37','2018-07-18 04:25:37'),(3,'STD0000037','희망학교1','희망학교2','희망학교3','','','','','','','','','','','','',NULL,1,3,2,4,5,'2018-07-22 16:16:35','0000-00-00 00:00:00'),(4,'STD0000038','서울중','한국중','제일중','','','','','','','','','','','','',NULL,1,3,2,4,5,'2018-07-22 17:14:52','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `ds_student_sub2_tx` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-23 23:34:32
