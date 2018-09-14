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
-- Temporary view structure for view `ds_school_list_view`
--

DROP TABLE IF EXISTS `ds_school_list_view`;
/*!50001 DROP VIEW IF EXISTS `ds_school_list_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `ds_school_list_view` AS SELECT 
 1 AS `SCH_ID`,
 1 AS `SCHOOL_NAME`,
 1 AS `SCHOOL_TYPE`,
 1 AS `SCH_FOUNDER_ID`,
 1 AS `ZIPCODE`,
 1 AS `PREFECTURE_ID`,
 1 AS `ADDRESS`,
 1 AS `INS_DATE`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `ds_school_list_view`
--

/*!50001 DROP VIEW IF EXISTS `ds_school_list_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ds_school_list_view` AS select `ds_schmid_base`.`SCH_ID` AS `SCH_ID`,`ds_schmid_base`.`SCHOOL_NAME` AS `SCHOOL_NAME`,2 AS `SCHOOL_TYPE`,3 AS `SCH_FOUNDER_ID`,`ds_schmid_base`.`ZIPCODE` AS `ZIPCODE`,`ds_schmid_base`.`PREFECTURE_ID` AS `PREFECTURE_ID`,`ds_schmid_base`.`ADDRESS` AS `ADDRESS`,`ds_schmid_base`.`INS_DATE` AS `INS_DATE` from `ds_schmid_base` union select `ds_search_high_tx`.`SCH_ID` AS `SCH_ID`,`ds_search_high_tx`.`NAME` AS `SCHOOL_NAME`,3 AS `SCHOOL_TYPE`,`ds_search_high_tx`.`SCH_FOUNDER_ID` AS `SCH_FOUNDER_ID`,`ds_search_high_tx`.`ZIPCODE` AS `ZIPCODE`,`ds_search_high_tx`.`PREFECTURE_ID` AS `PREFECTURE_ID`,concat(`ds_search_high_tx`.`ADDRESS1`,`ds_search_high_tx`.`ADDRESS2`,`ds_search_high_tx`.`ADDRESS3`) AS `ADDRESS`,`ds_search_high_tx`.`INS_DATE` AS `INS_DATE` from `ds_search_high_tx` union select `ds_search_univ_tx`.`SCH_ID` AS `SCH_ID`,`ds_search_univ_tx`.`NAME` AS `SCHOOL_NAME`,4 AS `SCHOOL_TYPE`,`ds_search_univ_tx`.`SCH_FOUNDER_ID` AS `SCH_FOUNDER_ID`,`ds_search_univ_tx`.`ZIPCODE` AS `ZIPCODE`,`ds_search_univ_tx`.`PREFECTURE_ID` AS `PREFECTURE_ID`,concat(`ds_search_univ_tx`.`ADDRESS1`,`ds_search_univ_tx`.`ADDRESS2`,`ds_search_univ_tx`.`ADDRESS3`) AS `ADDRESS`,`ds_search_univ_tx`.`INS_DATE` AS `INS_DATE` from `ds_search_univ_tx` union select `ds_search_voca_tx`.`SCH_ID` AS `SCH_ID`,`ds_search_voca_tx`.`CUST_NAME` AS `SCHOOL_NAME`,5 AS `SCHOOL_TYPE`,`ds_search_voca_tx`.`SCH_FOUNDER_ID` AS `SCH_FOUNDER_ID`,`ds_search_voca_tx`.`ZIPCODE` AS `ZIPCODE`,`ds_search_voca_tx`.`PREFECTURE_ID` AS `PREFECTURE_ID`,concat(`ds_search_voca_tx`.`ADDRESS1`,`ds_search_voca_tx`.`ADDRESS2`) AS `ADDRESS`,`ds_search_voca_tx`.`INS_DATE` AS `INS_DATE` from `ds_search_voca_tx` union select `ds_school_branch_tx`.`SCHOOL_ID` AS `SCH_ID`,`ds_school_branch_tx`.`SB_NAME` AS `SCHOOL_NAME`,`ds_school_branch_tx`.`SB_TYPE` AS `SCHOOL_TYPE`,`ds_school_branch_tx`.`SB_ID` AS `SB_ID`,`ds_school_branch_tx`.`SB_ZIPCODE` AS `SB_ZIPCODE`,`ds_school_branch_tx`.`SB_PREFECTURE_ID` AS `SB_PREFECTURE_ID`,concat(`ds_school_branch_tx`.`SB_ADDRESS1`,`ds_school_branch_tx`.`SB_ADDRESS2`) AS `ADDRESS`,`ds_school_branch_tx`.`INS_DATE` AS `INS_DATE` from `ds_school_branch_tx` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-23 23:34:38
