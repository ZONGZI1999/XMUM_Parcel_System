-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: parcel
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `parcel_details`
--

DROP TABLE IF EXISTS `parcel_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parcel_details` (
  `parcel_id` int NOT NULL COMMENT '与Parcel_Info相同',
  `detail_time` datetime NOT NULL COMMENT '操作发生时间',
  `state` int NOT NULL COMMENT '执行的操作'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcel_details`
--

LOCK TABLES `parcel_details` WRITE;
/*!40000 ALTER TABLE `parcel_details` DISABLE KEYS */;
INSERT INTO `parcel_details` VALUES (1,'2020-12-09 16:34:52',0),(1,'2020-12-09 17:34:52',1),(1,'2020-12-09 17:40:52',3);
/*!40000 ALTER TABLE `parcel_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcel_info`
--

DROP TABLE IF EXISTS `parcel_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parcel_info` (
  `parcel_id` int NOT NULL AUTO_INCREMENT COMMENT '包裹的id',
  `user_id` int DEFAULT NULL COMMENT '主用户的ID',
  `tacking_number` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '包裹的单号',
  `current_state` int NOT NULL COMMENT '当前状态',
  `receive_time` datetime DEFAULT NULL COMMENT '入库时间',
  `pick_up_time` datetime DEFAULT NULL COMMENT '取件时间',
  `is_consignee` int DEFAULT '0' COMMENT '是否代收 默认为0 ',
  `consignee_id` int DEFAULT '0' COMMENT '帮助取的人的ID 默认为0',
  PRIMARY KEY (`parcel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcel_info`
--

LOCK TABLES `parcel_info` WRITE;
/*!40000 ALTER TABLE `parcel_info` DISABLE KEYS */;
INSERT INTO `parcel_info` VALUES (1,1,'SF1000001',0,'2020-12-09 16:34:52',NULL,0,0),(2,1,'SF1000002',1,'2020-12-09 16:34:52','2020-12-09 16:35:00',0,0),(3,1,'SF10000035456546',2,'2020-12-09 16:34:54',NULL,0,0),(4,1,'SF1000002',1,'2020-12-09 16:34:52','2020-12-09 16:35:00',0,0),(5,1,'SF1000002',1,'2020-12-09 16:34:52','2020-12-09 16:35:00',0,0),(6,1,'SF1000002',1,'2020-12-09 16:34:52','2020-12-09 16:35:00',0,0),(7,1,'SF1000002',1,'2020-12-09 16:34:52','2020-12-09 16:35:00',0,0),(8,1,'SF1000002',1,'2020-12-09 16:34:52','2020-12-09 16:35:00',0,0),(9,1,'SF1000002',1,'2020-12-09 16:34:52','2020-12-09 16:35:00',0,0),(10,1,'SF1000002',1,'2020-12-09 16:34:52','2020-12-09 16:35:00',0,0),(11,1,'SF1000002',1,'2020-12-09 16:34:52','2020-12-09 16:35:00',0,0),(12,1,'SF1000004',0,'2020-12-09 16:34:52',NULL,1,2),(13,2,'SF1000005',0,'2020-12-09 16:34:52',NULL,1,1),(14,3,'SF1000006',0,'2020-12-09 16:34:52',NULL,2,1),(15,1,'SF1000006',0,'2020-12-09 16:34:52',NULL,2,2);
/*!40000 ALTER TABLE `parcel_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Store User Info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ZHENG'),(2,'LIN'),(3,'ZIXI');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-16  1:09:11
