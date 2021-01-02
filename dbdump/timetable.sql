-- MariaDB dump 10.18  Distrib 10.5.8-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: timetable
-- ------------------------------------------------------
-- Server version	10.5.8-MariaDB-1:10.5.8+maria~focal

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES ('25277859c0b44b9888eb8c29cfdd7639','Arabic Language Lecture','L-Arab'),('3b9e03a42119430e924f3177ed512247','Mathematics Tutorial','T-Math'),('9391970ea0d4460da800eb93d94a479e','English Language Lecture','L-Eng'),('c9e76f2bf96f4f51b04bd4de9855471c','History Lecture','L-Hist'),('cde9b5be9b21424db1d5be952369f25f','Mathematics Lecture','L-Math');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_allowed_day`
--

DROP TABLE IF EXISTS `activity_allowed_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_allowed_day` (
  `id` varchar(50) NOT NULL,
  `dayNum` int(11) NOT NULL,
  `activity_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdshphvubrvja9c56iwakuso8w` (`activity_id`),
  CONSTRAINT `FKdshphvubrvja9c56iwakuso8w` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_allowed_day`
--

LOCK TABLES `activity_allowed_day` WRITE;
/*!40000 ALTER TABLE `activity_allowed_day` DISABLE KEYS */;
INSERT INTO `activity_allowed_day` VALUES ('12577841013444a89a7bcb65edafd247',3,'3b9e03a42119430e924f3177ed512247'),('2a017c23051c47a3bb699a7e2307c980',4,'3b9e03a42119430e924f3177ed512247'),('4636e9b4bab648b38e2ad63a2aa68f3a',2,'25277859c0b44b9888eb8c29cfdd7639'),('473d0daef16b4510980ca136b8d3037b',2,'c9e76f2bf96f4f51b04bd4de9855471c'),('52cc62b37b60465d808383268f9357bc',1,'c9e76f2bf96f4f51b04bd4de9855471c'),('75a9bab77ce84482b10352ae0469de56',0,'9391970ea0d4460da800eb93d94a479e'),('d88dc8b097dd42f3acda71c051591b6e',1,'cde9b5be9b21424db1d5be952369f25f'),('ed917a9c54d64f02b2133b040bac7f3d',2,'9391970ea0d4460da800eb93d94a479e'),('f8b4e8689ed5408c90f0649953c75b7b',0,'cde9b5be9b21424db1d5be952369f25f'),('fdbc0988027f49ca86f89e8ec9c67b33',1,'9391970ea0d4460da800eb93d94a479e');
/*!40000 ALTER TABLE `activity_allowed_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_allowed_period`
--

DROP TABLE IF EXISTS `activity_allowed_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_allowed_period` (
  `id` varchar(50) NOT NULL,
  `periodNum` int(11) NOT NULL,
  `activity_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo0f2vqwylupl1ijqcjhvr1e8k` (`activity_id`),
  CONSTRAINT `FKo0f2vqwylupl1ijqcjhvr1e8k` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_allowed_period`
--

LOCK TABLES `activity_allowed_period` WRITE;
/*!40000 ALTER TABLE `activity_allowed_period` DISABLE KEYS */;
INSERT INTO `activity_allowed_period` VALUES ('0bfdf2ec3e564a2f91f5740545f4ddd7',3,'3b9e03a42119430e924f3177ed512247'),('248fef101aa3434abb7fa8ed9ebc28a0',3,'c9e76f2bf96f4f51b04bd4de9855471c'),('29c93ef9561840c4b1fcb95d4ac95db6',0,'9391970ea0d4460da800eb93d94a479e'),('3dbb7b3e19e844258667448fa24b0d15',1,'cde9b5be9b21424db1d5be952369f25f'),('54410d8c90944347a0ec899c5366527f',3,'25277859c0b44b9888eb8c29cfdd7639'),('6088ec1c8f3f4eff97c963239941332a',2,'25277859c0b44b9888eb8c29cfdd7639'),('70fc2374de1f4665b27da23c711d409c',1,'c9e76f2bf96f4f51b04bd4de9855471c'),('7145c02c5b1148ca8a1a64843053a648',1,'9391970ea0d4460da800eb93d94a479e'),('72a5c16444034f1281a118158c792b6e',0,'cde9b5be9b21424db1d5be952369f25f'),('771ebb5c782f46b7a2366aeee5d79053',2,'c9e76f2bf96f4f51b04bd4de9855471c'),('7cc739d7afaa45d5b4e9e2ddab9b81f0',4,'3b9e03a42119430e924f3177ed512247'),('84d74c25c2f44bccb7c58376eab30bf5',2,'3b9e03a42119430e924f3177ed512247'),('ba9a4d5073a14012b2e81e9b0466d32a',3,'9391970ea0d4460da800eb93d94a479e'),('cc0a67400465475099e875112c0980e9',4,'25277859c0b44b9888eb8c29cfdd7639'),('fb8add4a03d74803976ecedf5011a9b3',2,'9391970ea0d4460da800eb93d94a479e');
/*!40000 ALTER TABLE `activity_allowed_period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_assigned_teacher`
--

DROP TABLE IF EXISTS `activity_assigned_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_assigned_teacher` (
  `id` varchar(50) NOT NULL,
  `activity_id` varchar(50) DEFAULT NULL,
  `teacher_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqshk5b0jslsrn7it35ty929ek` (`activity_id`),
  KEY `FKf4rio2o98pja6hfb0hm68j6m7` (`teacher_id`),
  CONSTRAINT `FKf4rio2o98pja6hfb0hm68j6m7` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FKqshk5b0jslsrn7it35ty929ek` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_assigned_teacher`
--

LOCK TABLES `activity_assigned_teacher` WRITE;
/*!40000 ALTER TABLE `activity_assigned_teacher` DISABLE KEYS */;
INSERT INTO `activity_assigned_teacher` VALUES ('7e3c5bf6e62e40d29a62705de10e8347','3b9e03a42119430e924f3177ed512247','a246d8d8130b448991435609efd263e6'),('804c7ae8809344e19fc5331390a26803','9391970ea0d4460da800eb93d94a479e','2af4d3fb55fc4d849c367fb4b2461bd9'),('90f0ef9d349b4a15a0b1b1c92e9522bc','25277859c0b44b9888eb8c29cfdd7639','742dbc3d68f14245be334b12ce5b4d83'),('93603fde4fa347dba94969d323dd6410','9391970ea0d4460da800eb93d94a479e','a246d8d8130b448991435609efd263e6'),('a8ecdeee8f424fb7a84a1b5ee3d1bc5c','3b9e03a42119430e924f3177ed512247','51362c0f7f894efb8054c18b125bce62'),('dc6a3d66c9d341b4b3826f57343b8523','c9e76f2bf96f4f51b04bd4de9855471c','a246d8d8130b448991435609efd263e6'),('e445b6b5eb7d4d2e87bfced4a78cbcb9','cde9b5be9b21424db1d5be952369f25f','eeb71ffaa41545789c737b7b6742f933');
/*!40000 ALTER TABLE `activity_assigned_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` varchar(200) NOT NULL,
  `dateTime` datetime DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_roles`
--

DROP TABLE IF EXISTS `menu_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_roles` (
  `menu_id` varchar(100) NOT NULL,
  `role_id` varchar(200) NOT NULL,
  KEY `FKk7bdy1lc9xbitmcejy3q4qm41` (`role_id`),
  KEY `FKsyib35y32a5303ry88npak6us` (`menu_id`),
  CONSTRAINT `FKk7bdy1lc9xbitmcejy3q4qm41` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKsyib35y32a5303ry88npak6us` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_roles`
--

LOCK TABLES `menu_roles` WRITE;
/*!40000 ALTER TABLE `menu_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menus` (
  `id` varchar(100) NOT NULL,
  `grouped` int(11) NOT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `moduleClassName` varchar(100) DEFAULT NULL,
  `orderNo` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `parent_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcctt24h6nix02cxipt9rbqtnc` (`parent_id`),
  CONSTRAINT `FKcctt24h6nix02cxipt9rbqtnc` FOREIGN KEY (`parent_id`) REFERENCES `menus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modules`
--

DROP TABLE IF EXISTS `modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modules` (
  `id` varchar(200) NOT NULL,
  `className` varchar(100) DEFAULT NULL,
  `flag` varchar(20) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modules`
--

LOCK TABLES `modules` WRITE;
/*!40000 ALTER TABLE `modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_modules`
--

DROP TABLE IF EXISTS `role_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_modules` (
  `id` varchar(200) NOT NULL,
  `module_id` varchar(200) DEFAULT NULL,
  `role_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlfibvmp5sf3u6v4k44e9k526n` (`module_id`),
  KEY `FKbsac7r3yfq51cfww7u7r5jbyv` (`role_id`),
  CONSTRAINT `FKbsac7r3yfq51cfww7u7r5jbyv` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKlfibvmp5sf3u6v4k44e9k526n` FOREIGN KEY (`module_id`) REFERENCES `modules` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_modules`
--

LOCK TABLES `role_modules` WRITE;
/*!40000 ALTER TABLE `role_modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` varchar(200) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('admin','admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('2af4d3fb55fc4d849c367fb4b2461bd9','Ivan Vincent','IV'),('51362c0f7f894efb8054c18b125bce62','Saidy bin Yusof','SY'),('742dbc3d68f14245be334b12ce5b4d83','Ismail bin Mobius','IM'),('a246d8d8130b448991435609efd263e6','Latifah Ainun','LA'),('d558707cd2e84e0db974efd0820ddfe6','Salmah bte Salleh','SS'),('eeb71ffaa41545789c737b7b6742f933','Jaafar Darwis','JD');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` varchar(200) NOT NULL,
  `avatarImageFileName` varchar(100) DEFAULT NULL,
  `bankAcctNo` varchar(100) DEFAULT NULL,
  `bankName` varchar(100) DEFAULT NULL,
  `companyName` varchar(100) DEFAULT NULL,
  `companyRegistrationNo` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `documentFileName1` varchar(200) DEFAULT NULL,
  `documentFileName2` varchar(200) DEFAULT NULL,
  `documentFileName3` varchar(200) DEFAULT NULL,
  `documentFileName4` varchar(200) DEFAULT NULL,
  `documentFileName5` varchar(200) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `initial` varchar(10) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `pageStyle` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `signatureImageFileName` varchar(100) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `userPassword` varchar(100) DEFAULT NULL,
  `role_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','QL0AFWMIX8NRZTKeof9cXsvbvu8=','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` varchar(200) NOT NULL,
  `role_id` varchar(200) NOT NULL,
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-02 16:21:48
