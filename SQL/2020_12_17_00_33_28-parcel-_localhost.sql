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
  `detail_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作发生时间',
  `state` int NOT NULL COMMENT '执行的操作',
  `operator_id` int DEFAULT '-1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcel_details`
--

LOCK TABLES `parcel_details` WRITE;
/*!40000 ALTER TABLE `parcel_details` DISABLE KEYS */;
INSERT INTO `parcel_details` VALUES (1,'2020-12-09 16:34:52',0,-1),(2,'2020-12-09 16:34:52',0,-1),(3,'2020-12-09 16:34:52',0,-1),(3,'2020-12-09 16:34:53',3,-1),(2,'2020-12-09 17:34:52',1,-1),(4,'2020-12-11 16:34:52',0,-1),(4,'2020-12-11 16:34:53',3,-1),(5,'2020-12-16 17:09:52',0,-1),(5,'2020-12-16 17:12:42',3,-1),(5,'2020-12-16 17:12:48',4,-1),(6,'2020-12-16 17:14:05',2,-1),(3,'2020-12-16 17:44:27',0,-1),(3,'2020-12-16 17:53:41',3,-1),(3,'2020-12-16 18:00:31',0,-1),(4,'2020-12-16 18:06:35',4,-1),(1,'2020-12-16 18:28:13',3,-1),(1,'2020-12-16 18:28:28',0,-1),(1,'2020-12-16 18:28:41',3,-1),(1,'2020-12-16 18:29:33',0,-1),(1,'2020-12-16 18:31:23',3,-1),(3,'2020-12-16 19:18:25',3,-1),(3,'2020-12-16 19:18:26',0,-1),(3,'2020-12-16 19:39:54',3,-1),(3,'2020-12-16 19:39:56',0,-1),(3,'2020-12-16 19:53:52',3,-1),(3,'2020-12-16 19:54:02',0,-1),(4,'2020-12-16 20:03:30',0,-1),(3,'2020-12-16 20:03:52',3,-1),(3,'2020-12-16 20:04:14',0,-1),(1,'2020-12-16 20:04:28',0,-1),(1,'2020-12-16 20:04:33',3,-1),(1,'2020-12-16 20:04:59',4,-1),(5,'2020-12-16 21:54:48',0,-1),(3,'2020-12-16 21:54:58',3,-1),(1,'2020-12-16 22:03:42',0,-1),(1,'2020-12-17 00:04:00',3,-1),(1,'2020-12-17 00:04:01',-2,-1),(1,'2020-12-17 00:07:13',3,-1),(1,'2020-12-17 00:08:00',-1,-1),(1,'2020-12-17 00:08:08',3,-1),(1,'2020-12-17 00:08:12',4,-1),(1,'2020-12-17 00:08:20',-2,-1),(1,'2020-12-17 00:08:30',3,-1),(1,'2020-12-17 00:08:34',4,-1),(1,'2020-12-17 00:08:36',-2,-1),(17,'2020-12-17 00:24:36',0,-1),(17,'2020-12-17 00:25:29',3,1),(17,'2020-12-17 00:25:37',4,3),(17,'2020-12-17 00:25:44',-2,3),(17,'2020-12-17 00:25:51',3,1),(17,'2020-12-17 00:25:57',-2,1),(17,'2020-12-17 00:26:08',3,1),(17,'2020-12-17 00:26:12',4,3),(17,'2020-12-17 00:26:17',-2,1);
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
  `tracking_number` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '包裹的单号',
  `receive_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `pick_up_time` datetime DEFAULT NULL COMMENT '取件时间',
  `consignee_id` int DEFAULT '0' COMMENT '帮助取的人的ID 默认为0',
  PRIMARY KEY (`parcel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcel_info`
--

LOCK TABLES `parcel_info` WRITE;
/*!40000 ALTER TABLE `parcel_info` DISABLE KEYS */;
INSERT INTO `parcel_info` VALUES (1,1,'SF1000001','2020-12-09 16:34:52',NULL,0),(2,1,'SF1000002','2020-12-09 16:34:52','2020-12-09 17:34:52',0),(3,1,'SF1000003','2020-12-09 16:34:52',NULL,2),(4,2,'SF1000004','2020-12-11 16:34:52',NULL,0),(5,3,'123345','2020-12-16 17:09:52',NULL,0),(6,1,'aaaa','2020-12-16 17:13:44',NULL,0),(17,1,'test','2020-12-17 00:24:36',NULL,0);
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
  `full_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Store User Info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (-1,'System'),(1,'ZHENG'),(2,'LIN'),(3,'ZIXI');
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

-- Dump completed on 2020-12-17  0:33:28
