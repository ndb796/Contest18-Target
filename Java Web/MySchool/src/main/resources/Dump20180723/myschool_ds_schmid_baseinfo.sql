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
-- Table structure for table `ds_schmid_baseinfo`
--

DROP TABLE IF EXISTS `ds_schmid_baseinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_schmid_baseinfo` (
  `IDX` int(11) NOT NULL AUTO_INCREMENT,
  `SCH_ID` varchar(15) DEFAULT NULL,
  `ID` int(11) DEFAULT NULL,
  `SCHOOL_NAME` varchar(200) DEFAULT NULL,
  `SCHOOL_COPY` varchar(400) DEFAULT NULL,
  `PRINCIPAL` varchar(100) DEFAULT NULL,
  `HISTORY` text,
  `SCH_COUNT` text,
  `STATION` text,
  `CURRICULUM` text,
  `START_TIME` varchar(50) DEFAULT NULL,
  `END_TIME` varchar(50) DEFAULT NULL,
  `SCH_EXPLAIN1` text,
  `HENSACHI` text,
  `GAKUHI` text,
  `INS_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`IDX`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_schmid_baseinfo`
--

LOCK TABLES `ds_schmid_baseinfo` WRITE;
/*!40000 ALTER TABLE `ds_schmid_baseinfo` DISABLE KEYS */;
INSERT INTO `ds_schmid_baseinfo` VALUES (1,'MSCH0000001',40010,'제일중학교','대한민국 제일 중학교로...','신나게 놀고, 공부하고','1924년','1000','왕십리','영어특기생','09:00','17:30','좋은학교','20%','10%','2018-07-17 15:39:28'),(2,'MSCH0000002',40020,'서울중학교','모두가 행복한 서울 미래 교육','배움과 배려의 열정으로 모두가 행복한 교육','1951년 개교 이래...','500','서울역','교육장학생','09:00','17:30','좋은학교','20%','10%','2018-07-17 15:50:46'),(3,'MSCH0000003',40030,'서울시립중학교','미래사회를 주도할 바른 인성을 지닌 창의적인 인재육성','배움의 즐거움이 있는 행복한 학교','2000년 개교','1000','삼성역','운동학생','09:00','17:30','좋은학교','20%','10%','2018-07-17 15:50:46'),(4,'MSCH0000004',40040,'시청중학교','지역사회아ㅗ 함꼐하는 인터넷중독 예방 프로그램 운영 학교','자기주도적으로 공부하며 꿈이 있는 학생','2005년 중고등학교 통합','1000','도봉역','특별활동 활발','09:00','17:30','좋은학교','20%','10%','2018-07-17 15:50:46'),(5,'MSCH0000005',40050,'한국중학교','꿈을 키워가는 행복키움터 운영','학교를 신뢰하며 교사를 존경하는 학부모, 보람과 긍지를 가지고 교육하는 존경받는 선생니므 최선을 다하고 꾸준히 노력하여 실력을 키우는 학생','1924년','1000','신도림','일본재매학교','09:00','17:30','좋은학교','20%','10%','2018-07-17 15:50:46');
/*!40000 ALTER TABLE `ds_schmid_baseinfo` ENABLE KEYS */;
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
