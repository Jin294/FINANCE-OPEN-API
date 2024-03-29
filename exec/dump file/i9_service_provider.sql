-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: j9b309.p.ssafy.io    Database: i9
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `service_provider`
--

DROP TABLE IF EXISTS `service_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider` (
  `service_provider_id` bigint NOT NULL AUTO_INCREMENT,
  `api_token` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `area` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `created_time` datetime(6) DEFAULT NULL,
  `deleted_time` datetime(6) DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0',
  `password` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `class` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`service_provider_id`),
  KEY `FK6tawicr70xuto0nlnnq6sdu3s` (`client_id`),
  CONSTRAINT `FK6tawicr70xuto0nlnnq6sdu3s` FOREIGN KEY (`client_id`) REFERENCES `oauth_client_details` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider`
--

LOCK TABLES `service_provider` WRITE;
/*!40000 ALTER TABLE `service_provider` DISABLE KEYS */;
INSERT INTO `service_provider` VALUES (2,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1Nzk3NTIzMjA3LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFAbmF2ZXIuY29tIiwiZXhwIjoxNzI3MzMzNTIzLCJzdWIiOiJhcGktdG9rZW4ifQ.hk4eQ3usbVPiubthtbIEKQ1M7ZPNI3NFhmSq6fSR7-4','대전',NULL,'2023-09-24 19:27:25.452825',NULL,'a@naver.com',_binary '\0','$2a$10$EzKTBLQciXQUeVFMot1ou.hvpQDwZ/I6YfcpAWgp4dyGqAGC1afLy',NULL,NULL),(3,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjA3NjEzNjg5LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFhQG5hdmVyLmNvbSIsImV4cCI6MTcyNzE0MzYxMywic3ViIjoiYXBpLXRva2VuIn0.KIxn8nY8xPgHZvM6NUs_oGStzgcx3wROgD2_iSKZvSI','대전',NULL,'2023-09-25 11:06:53.689875',NULL,'aa@naver.com',_binary '\0','$2a$10$UPkeyL/oTgXJBKWiNyZJf.uQZkDdkxdF53vdgnr/agDM6lGzAQ68m',NULL,NULL),(4,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjA3ODAwNTQwLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFhcUBuYXZlci5jb20iLCJleHAiOjE3MjcxNDM4MDAsInN1YiI6ImFwaS10b2tlbiJ9.kOXjNXkGdCIeIIRvl05KxCq2GGSkM86QblLwC0oB2yk','대전',NULL,'2023-09-25 11:10:00.540137',NULL,'aaq@naver.com',_binary '\0','$2a$10$jokACsg3tCJqbvdoIhDA9.BKaUQyKbKAmUFBQQg6Q7wlregR4.BeG',NULL,NULL),(5,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjA4MTUxOTU1LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjFAbmF2ZXIuY29tIiwiZXhwIjoxNzI3MTQ0MTUxLCJzdWIiOiJhcGktdG9rZW4ifQ.bUH-jkHF5y3xO-IDQesl3uqeoRFscEdCfz0_wHlZc3M','대전',NULL,'2023-09-25 11:15:51.955051',NULL,'1@naver.com',_binary '\0','$2a$10$mVbSI2DG5faB9s9KIPs1/ed3dwndfPc8dfpTZGeYvm3eyRO2/445q',NULL,NULL),(6,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjA4MTg3MDA0LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImhpaGlAbmF2ZXIuY29tIiwiZXhwIjoxNzI3MTQ0MTg3LCJzdWIiOiJhcGktdG9rZW4ifQ.4CVhDg4uLQ7S5rhy6y8uZXa55UuA_4lsrWvAyebscbM','대전',NULL,'2023-09-25 11:16:27.004283',NULL,'hihi@naver.com',_binary '\0','$2a$10$V6hYWWaXWcNJw9PSOJqmEei5mdk0r6ZkKK1XcRNKpq285jNlo4VGe',NULL,NULL),(7,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjE3OTYxNTUzLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFhNDRxQG5hdmVyLmNvbSIsImV4cCI6MTcyNzE1Mzk2MSwic3ViIjoiYXBpLXRva2VuIn0.Ci0E0INSzRCNHTlmwglmPoT5Hy_hF7Ch__pwS8FiJJk','대전',NULL,'2023-09-25 13:59:21.553785',NULL,'aa44q@naver.com',_binary '\0','$2a$10$.FtiyoU1WDE8nslSKPhUgOxsWBDjkIV1f7o0acHE0X6IHj6RqCsZa',NULL,NULL),(8,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjE4MDQyOTU2LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFhNDQ1NXFAbmF2ZXIuY29tIiwiZXhwIjoxNzI3MTU0MDQyLCJzdWIiOiJhcGktdG9rZW4ifQ.ttsQReW1Ydo9verT6bp_nKKaw2--X2lDv61IJ0nMEKE','대전',NULL,'2023-09-25 14:00:42.956209',NULL,'aa4455q@naver.com',_binary '\0','$2a$10$F71hd4D64OjpwG5YbQs7UeYNbOTmSG1recyomQPzF1QKJMWuelTAK',NULL,NULL),(9,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjE4MTU1NTMyLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imd1ZHduczEyM0BuYXZlci5jb20iLCJleHAiOjE3MjcxNTQxNTUsInN1YiI6ImFwaS10b2tlbiJ9.N6mQWqskx7tpzrb07QL1SqZZ5tO0erzI9jDpitTUBRA','대전',NULL,'2023-09-25 14:02:35.532505',NULL,'gudwns123@naver.com',_binary '\0','$2a$10$FY0AAtPSyhENezhpCaVJL.W8UMKE05Qy7Vy/OUyNo3t8OHCYaftY2',NULL,NULL),(10,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjE4NTYyODAwLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InpAbmF2ZXIuY29tIiwiZXhwIjoxNzI3MTU0NTYyLCJzdWIiOiJhcGktdG9rZW4ifQ.BsPjP4DLbQvxiRWjZeSQ6VERQlBE4E7jrZEObmy8rYc','대전',NULL,'2023-09-25 14:09:22.800292',NULL,'z@naver.com',_binary '\0','$2a$10$At0k3pQ5nAIPNFTgsTj43en1iuSMMe.A2rvSr.7GweoawUm.C/Zim',NULL,NULL),(11,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjE4ODgxNDE4LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imd1ZHduczEyMjI0M0BuYXZlci5jb20iLCJleHAiOjE3MjcxNTQ4ODEsInN1YiI6ImFwaS10b2tlbiJ9.x9XutOi1Ec_TRRtMoZEBEk5wDU8dMDUzp2mYyvkIGS8','대전',NULL,'2023-09-25 14:14:41.418706',NULL,'gudwns122243@naver.com',_binary '\0','$2a$10$npsjN/3l3t3Ktx1QtJyN/.BQewwl1lZyEZkpLMpUWxJ7rQX5fq1OS',NULL,NULL),(12,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjE5ODI1MTU2LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imd1ZDIyMjQzQG5hdmVyLmNvbSIsImV4cCI6MTcyNzE1NTgyNSwic3ViIjoiYXBpLXRva2VuIn0.hOcFK_K1_4fSD9W-Th7Z7kgKdHXq5tmu2VIILbhqerY','대전',NULL,'2023-09-25 14:30:25.156816',NULL,'gud22243@naver.com',_binary '\0','$2a$10$031jGyA9HCOtpWdRNJZoWeuREG.5JgzFreC1om9u.gYaodwtkO3EC',NULL,NULL),(13,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjE5ODQ1NzAxLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjQ0NDR6QG5hdmVyLmNvbSIsImV4cCI6MTcyNzE1NTg0NSwic3ViIjoiYXBpLXRva2VuIn0.4MGqZ9GJnR3gm_t9UgYRdrm3N6qgy9NEULLDbmhSpGs','대전',NULL,'2023-09-25 14:30:45.701536',NULL,'4444z@naver.com',_binary '\0','$2a$10$.hnUuuWCBZhhaBjwmVQYCOLMEnwImPPNd7U7QnShJXeCkBRwKzgDa',NULL,NULL),(14,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjE5ODQ2NDU3LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImcyNDNAbmF2ZXIuY29tIiwiZXhwIjoxNzI3MTU1ODQ2LCJzdWIiOiJhcGktdG9rZW4ifQ.IP97Y6n3mIq35UouwtYH9s9ixF9e9xQ5Y6mweC15AvY','대전',NULL,'2023-09-25 14:30:46.457992',NULL,'g243@naver.com',_binary '\0','$2a$10$zt7qTk23k5kZlqjAWSVNoevDWsrG97107/1sVGiUAlpLzEdq.U/pG',NULL,NULL),(15,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjE5ODU2MzYyLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjQ0NDR6NDQ0QG5hdmVyLmNvbSIsImV4cCI6MTcyNzE1NTg1Niwic3ViIjoiYXBpLXRva2VuIn0.2strkt9brN7xbGo24YUZPv1vgSCU6t-zPs2xJxiiXLQ','대전',NULL,'2023-09-25 14:30:56.362846',NULL,'4444z444@naver.com',_binary '\0','$2a$10$xcOZmOYRDOrsmY1UuTAVFe3Vse0PXLNM79/ExGzIufBWyAHzzlj5m',NULL,NULL),(16,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NjIwMjA4MzM3LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InNzYWZ5IiwiZXhwIjoxNzI3MTU2MjA4LCJzdWIiOiJhcGktdG9rZW4ifQ.SKfT-70zTP-ZM9N1XPKL72Yaio8uppK9ixCAO9R_7jg','대전',NULL,'2023-09-25 14:36:48.337175',NULL,'ssafy',_binary '\0','$2a$10$f0OU19B3s0T5RcmmrpnyXeCL1FTVZ8GE5X5caln7KT2N2M7dSICZS',NULL,NULL),(17,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk2MzgwNzAzOTIyLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InNzYWZ5QHNzYWZ5LmNvbSIsImV4cCI6MTcyNzkxNjcwMywic3ViIjoiYXBpLXRva2VuIn0.k_en74rJcoG2unZOHChIWUp_y5T1NUA6Pup_CYnwfcw','대전',NULL,'2023-09-26 09:42:52.233399',NULL,'ssafy@ssafy.com',_binary '\0','$2a$10$IW66LcRRx9NcVz0L5dXGL.wuFt/cPm5MBxvPbtzKjWfYKMmR3XB7K',NULL,NULL),(18,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NzU2NTY4MDk2LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InMxIiwiZXhwIjoxNzI3MjkyNTY4LCJzdWIiOiJhcGktdG9rZW4ifQ.EGzn48zuieGIfcM76PK-uuM26UVAsajYTok0SbquRCA','1',NULL,'2023-09-27 04:29:28.096435',NULL,'s1',_binary '\0','$2a$10$v3oe/RxweL4oiuFIkESncefHCoAio7Eegd0ToBJhyjsZsp.R1zxwG',NULL,NULL),(19,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1NzU3MDU4NTMwLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjEyM0AxMjMuY29tIiwiZXhwIjoxNzI3MjkzMDU4LCJzdWIiOiJhcGktdG9rZW4ifQ.QeCA-JA5VNQHhncuQywmG_4C2zWEQaRLeH2YAcY4nDA','123',NULL,'2023-09-27 04:37:38.530795',NULL,'123@123.com',_binary '\0','$2a$10$37Fu65ZAn.rW6CCwfJnJs.2AsM3B17jo/GoGHxBoB317zRT.uloY.',NULL,NULL),(20,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1Nzk2NjUyMDQ5LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjEiLCJleHAiOjE3MjczMzI2NTIsInN1YiI6ImFwaS10b2tlbiJ9.02TQXd4h4mymtdYGHV_QPPiYliPlK34AAbYkWoZwGEA','대전',NULL,'2023-09-27 15:37:32.049738',NULL,'1',_binary '\0','$2a$10$Fr1RqDYBtX4qDF.r57Hdp.vjci20UNF1IJHz.ZU7CNfGtD2p3OShC',NULL,NULL),(21,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1Nzk3NjYxMTAyLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjIiLCJleHAiOjE3MjczMzM2NjEsInN1YiI6ImFwaS10b2tlbiJ9.VXONTlOB8-IziHRCJv6K8rmYBgHmXMYE30bIpScHjzY','대전',NULL,'2023-09-27 15:54:21.102091',NULL,'2',_binary '\0','$2a$10$J.VsW4AnheMUwvMCBMhq2ODjZ/pfTZYybEq1/AcQjEpTwgLQLCwHO',NULL,NULL),(22,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk2MTU2OTE0MTcyLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IndqZDUxMjZAa29yZWF0ZWNoLmFjLmtyIiwiZXhwIjoxNzI3NjkyOTE0LCJzdWIiOiJhcGktdG9rZW4ifQ.enYyxwkthSgpgr1eFTXlPlkUklgQ0TLcjSnxqv3tG-g','대전',NULL,'2023-10-01 19:41:54.172237',NULL,'wjd5126@koreatech.ac.kr',_binary '\0','$2a$10$9sic1E9S0G3ZqVs6GnQ0HuzS69hk4okpY.cvumxbvUp03niVYp4Ka',NULL,NULL),(23,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk2MjQxMTM2NzQyLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjMiLCJleHAiOjE3Mjc3NzcxMzYsInN1YiI6ImFwaS10b2tlbiJ9.l4ejHGDNSVJQZ-jOdwQ88MuynWiNNs8EO9p0ITNZUtY','1',NULL,'2023-10-02 19:05:36.742290',NULL,'3',_binary '\0','$2a$10$CmRAYgVREitQS5.QA.xj5eEpQylwcU8wITHns4emkXzg/v/Qnx1tS',NULL,NULL),(24,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk2MzgxNTg1NDYwLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjQiLCJleHAiOjE3Mjc5MTc1ODUsInN1YiI6ImFwaS10b2tlbiJ9.NXVsb0zwDmC2Ccbsw4EnHLDbBt2OpH880AbbF-Jf1Z4','대전',NULL,'2023-10-04 10:06:25.460955',NULL,'4',_binary '\0','$2a$10$D79A77CdeYqWxyvxxsPPpukKV1.2nCzoSbxCWM5VCU13KVUF7vSu6',NULL,NULL),(25,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk2NDcxNzY5NDE0LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InNzYWZ5YWRtaW5AbmF2ZXIuY29tIiwiZXhwIjoxNzI4MDA3NzY5LCJzdWIiOiJhcGktdG9rZW4ifQ.9SGH68-Vv5bNNJEuH6bm5B7aot4nKuR8bm1CSmlWIuo','대전',NULL,'2023-10-05 11:09:29.414376',NULL,'ssafyadmin@naver.com',_binary '\0','$2a$10$bTzHZhURduCEZlijPxt74eEt0VQw65U7dBF5KeSdrW5D416DQcYdO',NULL,NULL),(26,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk2NDg2NDE4ODA4LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjUiLCJleHAiOjE3MjgwMjI0MTgsInN1YiI6ImFwaS10b2tlbiJ9.LQskaHkcnCDqAIZvZF_CF9oZKR9Dd7xfzfTLecn0oWs','대전',NULL,'2023-10-05 14:38:28.010805',NULL,'5',_binary '\0','$2a$10$x78oPpECz6rZJIkq/wBQbuFhQImO/DErGUO90Z1dAK7AjLe87yF5G',NULL,NULL),(27,'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk2NDg3MzI0MDQ2LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjYiLCJleHAiOjE3MjgwMjMzMjQsInN1YiI6ImFwaS10b2tlbiJ9.3FFBj5ghshlfRaOymI1H-nr7ZOY91R3i29TgmI7oBDU','6',NULL,'2023-10-05 15:28:44.046242',NULL,'6',_binary '\0','$2a$10$Ztz19mVz6oe7qHzJ.04NQ.N7SN4efpSvBc7rjKFszAWlvPhyhFQUe',NULL,NULL);
/*!40000 ALTER TABLE `service_provider` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-06  2:12:08
