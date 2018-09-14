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
-- Table structure for table `ds_blog_tx`
--

DROP TABLE IF EXISTS `ds_blog_tx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_blog_tx` (
  `IDX` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` varchar(15) NOT NULL,
  `BLOG_TYPE` tinyint(4) NOT NULL,
  `SHOW_FLG` tinyint(4) NOT NULL DEFAULT '0',
  `SCHOOL_ID` varchar(15) DEFAULT NULL,
  `TITLE` varchar(1024) NOT NULL,
  `CONTENTS` text NOT NULL,
  `UPD_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPD_ID` varchar(15) DEFAULT NULL,
  `DEL_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DEL_FLG` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`IDX`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_blog_tx`
--

LOCK TABLES `ds_blog_tx` WRITE;
/*!40000 ALTER TABLE `ds_blog_tx` DISABLE KEYS */;
INSERT INTO `ds_blog_tx` VALUES (1,'STD0000021',1,1,NULL,'나름대로 기준을 가지고 학교 행사에 참여','중학교는 사람으로서의 기초를 만드는 시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할 생각할 수 있는 환경을 갖고 있는 학교를 찾았습니다. 설명회를 통해 학교가 가장 소중히하고 있는 교육과 방침에 대해 공감 할 수 있는 것에 확인할 수 있었습니다.','2018-07-16 15:40:27','admin','0000-00-00 00:00:00',0),(2,'STD0000022',1,1,NULL,'중, 고등학교는 인생에서 가장 중요한시기','진학 학교 정보 수집, 이벤트 참여, 최근 실적이 좋은 입시학원의 WEB에서 리뷰 검색, 학교 선생님과의 면담 등으로 아이들과 함께 참여하거나 웹사이트에서 정보를 활용하기도했습니다. 마지막은 직접 학교에 가서보고, 학교의 분위기와 여러가지를 검토한 후에 결정했습니다. 중, 고등학교는 인생에서 가장 중요한시기라고 생각합니다.','2018-07-16 15:40:27','admin','0000-00-00 00:00:00',0),(3,'STD0000023',1,1,NULL,'우선, 사립 또는 국립도 하나에서부터 시작','우선, 사립 또는 국립을 위해 주로 엄마끼리 정보 교환하고있는 상황에서 중학교 시험을 어떻게 할까 고민하다가 앞으로 학교의 정보를 수집하는 데에 있어서 사립 또는 국립학교까지 갈지를 결정하는 중요한 포인트가된다고 생각합니다. 역시 다른 사람의 흐름보다 자녀의 미래를 생각하고 결정해야 한다고 생각합니다.','2018-07-16 15:40:27','admin','0000-00-00 00:00:00',0),(4,'STD0000024',1,1,NULL,'축구의 재능을 살릴 수 있는 학교 선택','맞벌이로 부부끼리 의견을 맞추는 것이 매우 어렵다고 느끼고 있었기에 아이가 좋아하는 축구의 재능을 살릴 수 있는 학교의 선택은 어려웠습니다. 실제로 학교의 의견을 듣고 싶습니다. 지금은 수영과 영어를하고 있지만 그것도 좋은 것인지 솔직히 모르는 상황입니다.','2018-07-16 15:40:27','admin','0000-00-00 00:00:00',0),(5,'STD0000025',1,1,NULL,'왕따 방지를 위한 대책을 세우는 학교, 그 학교의 브랜드 가치','아직 아이가 어리지만 사립학교에 진학을 결정하고있습니다. 실제 정보는 대부분 집 주변의 엄마들과의 정보 교환을 통해 수집하고있는 상황입니다만, 중요한 것은 여자아이이기 때문에 학교의 브랜드와 건전성 등도 확인하고 있습니다. 특히 왕따 대책에 대해 학교 정책 등이 있는 곳인지 확인하고 있습니다.','2018-07-16 15:40:27','admin','0000-00-00 00:00:00',0),(6,'STD0000026',1,1,NULL,'자립 할 수있는 아이가 되었으면','실제 직장에서 신입 사원의 모습을 보면, 자립에 일 수 있는지 커뮤니케이션을 잘 할 수 있는지는 어린 시절의 영향이 크다고 생각합니다. 학교의 주요 결정은 아이에게 맡기고 있지만, 학교 선택에 도움이 될 책과 이벤트를 확인하고 함께 실제로 이야기 하고 있습니다.','2018-07-16 15:40:27','admin','0000-00-00 00:00:00',0),(7,'STD0000027',1,1,NULL,'그다지 높지 않은 성적으로도 선택할 수 있는 학교들','학교 정보 중 학교 환경, 집과의 거리, 동아리, 실제 학교의 생생한 정보를 원하고 있지만, 학교의 대응이 잘 되지 않네요. 교육 방침을 가지고 학교의 정보를 갖고 있으며, 실제 재학중인 학생과 학부모의 정직한 목소리가 교환되고 있는 커뮤니티에도 가입 싶습니다.','2018-07-16 15:40:27','admin','0000-00-00 00:00:00',0),(8,'STD0000028',1,1,NULL,'맞벌이 부모의 경력 활용','학부모로서 학교의 교육 방침이 얼마나 심각하게 아이들에 대해 생각하고 있는지, 자연이 환경이 풍부한지, 도서관을 갖추고 있는지, 현재 다니고있는 학생이 생기 발랄하게 생활하고 있는지 등의 원시 정보가 교환 할 수 있기를 바랍니다. 학교는 진지하게 학생들 마주하고 고민하고 있는지, 기초 공부에 충실한지 등 학교의 교육방침에 대해서도 정보를 얻고 싶습니다.','2018-07-16 15:40:27','admin','0000-00-00 00:00:00',0),(9,'STD0000021',3,1,NULL,'테스트블로그11','오예11','2018-07-16 15:40:27','STD0000021','0000-00-00 00:00:00',0);
/*!40000 ALTER TABLE `ds_blog_tx` ENABLE KEYS */;
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
