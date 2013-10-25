# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.1.70)
# Database: phonebook
# Generation Time: 2013-10-25 17:53:33 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Entry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Entry`;

CREATE TABLE `Entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `telephoneNumber` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `streetAddress` varchar(255) DEFAULT NULL,
  `latitude` bigint(20) DEFAULT '0',
  `longitude` bigint(20) DEFAULT '0',
  `entered` timestamp NULL DEFAULT NULL,
  `changed` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(1) NOT NULL DEFAULT '3',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`status`,`categoryId`),
  KEY `entry_category` (`categoryId`),
  CONSTRAINT `entry_category` FOREIGN KEY (`categoryId`) REFERENCES `entrycategory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Entry` WRITE;
/*!40000 ALTER TABLE `Entry` DISABLE KEYS */;

INSERT INTO `Entry` (`id`, `name`, `categoryId`, `telephoneNumber`, `website`, `emailAddress`, `streetAddress`, `latitude`, `longitude`, `entered`, `changed`, `status`)
VALUES
	(1,'European Emergency Number',1,'112','http://google.com','',NULL,NULL,NULL,NULL,'2013-09-09 01:33:24',2),
	(2,'Police Immediate Responce',1,'100','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(3,'Abulance Service (EKAB)',1,'166','','',NULL,NULL,NULL,NULL,'2013-09-12 23:07:47',2),
	(4,'Hospitals & Pharmacies',1,'14944','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(5,'Fire Brigade',1,'199','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(6,'Police Call Center',1,'1033','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(7,'Tourist Police',1,'171','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(8,'Marine Police Immediate intervention',1,'108','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(9,'Counterterrorism Agency',1,'1014','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(10,'Roadside Assistance',1,'10400','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(11,'Abulance Service (EKAB)',2,'166','http://www.ekab.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(12,'Police Immediate Responce',2,'100','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(13,'Hospitals & Pharmacies',2,'14944','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(14,'Poisoning center',2,'00302107793777','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(15,'First aid station of IKA',2,'00302106467811','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(16,'Hellenic Red Cross',2,'00302103603449','http://www.redcross.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(17,'Medecins Sans Frontieres',2,'00302105200500 ','http://www.msf.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(18,'Drug Help Line (OKANA)',2,'1031','http://www.okana.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(19,'Police Immediate Responce',3,'100','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(20,'Athens Traffic Police',3,'00302105284000','http://www.hellenicpolice.gr/index.php?lang=EN',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(21,'Tourist Police',3,'171','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(22,'Counter terrorism',3,'1014','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(23,'Narcotics Squads',3,'109','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(24,'Marine Police',3,'108','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(25,'Citizen Helpline',4,'195','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(26,'The Greek Ombudsman',4,'801 1125000','http://www.synigoros.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(27,'Consumer\'s Helpline',4,'1520','http://www.efpolis.gr/',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(28,'International Call Assitance',4,'139','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(29,'Hellenic Postal Services',5,'800 1182000','http://www.elta.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(30,'Electricity Service',5,'10500','http://www.dei.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(31,'US Embassy',6,'00302107212951','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(32,'United Kingdom Embassy',6,'00302107272600','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(33,'All Schedules',7,'14944','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(34,'Athens Airport',7,'00302103530000','http://www.aia.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(35,'Athens METRO',7,'00302105194012','http://www.ametro.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(36,'Athens Urban Transportation',7,'185','http://www.oasa.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(37,'Hellenic Train Services',7,'1110','http://www.ose.gr',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(38,'General Information',8,'13888','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(39,'Telephone Directory',8,'11888','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(40,'Local Time',8,'14844','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(41,'Weather Bulletin',8,'14944','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(42,'Theaters',8,'1422','',NULL,NULL,NULL,NULL,'2013-06-14 00:00:00','2013-06-14 00:00:00',2),
	(43,'International Call Assitance',8,'139','','',NULL,NULL,NULL,NULL,'2013-09-09 13:24:23',0);

/*!40000 ALTER TABLE `Entry` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table EntryCategory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `EntryCategory`;

CREATE TABLE `EntryCategory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `entered` timestamp NULL DEFAULT NULL,
  `changed` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_org_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `EntryCategory` WRITE;
/*!40000 ALTER TABLE `EntryCategory` DISABLE KEYS */;

INSERT INTO `EntryCategory` (`id`, `name`, `description`, `entered`, `changed`)
VALUES
	(1,'Emergency','Emergency',NULL,'2013-09-12 22:19:05'),
	(2,'HealthCare','HealthCare','2013-06-15 01:27:45','2013-06-15 01:27:45'),
	(3,'Police','Police','2013-06-15 01:27:45','2013-06-15 01:27:45'),
	(4,'Helplines','Helplines','2013-06-15 01:27:45','2013-06-15 01:27:45'),
	(5,'Public Services','Public Services','2013-06-15 01:27:45','2013-06-15 01:27:45'),
	(6,'Embassies','Embassies','2013-06-15 01:27:45','2013-06-15 01:27:45'),
	(7,'Transportation','Transportation','2013-06-15 01:27:45','2013-06-15 01:27:45'),
	(8,'Information','General Informations','2013-06-15 01:27:45','2013-06-15 01:27:45');

/*!40000 ALTER TABLE `EntryCategory` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Version
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Version`;

CREATE TABLE `Version` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL DEFAULT '',
  `Version` tinyint(4) DEFAULT NULL,
  `entered` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `changed` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Version` WRITE;
/*!40000 ALTER TABLE `Version` DISABLE KEYS */;

INSERT INTO `Version` (`Id`, `Name`, `Version`, `entered`, `changed`)
VALUES
	(1,'app',1,'2013-04-06 12:14:55','2013-04-06 12:14:55'),
	(2,'data',4,'2013-04-06 12:14:51','2013-04-07 15:38:45');

/*!40000 ALTER TABLE `Version` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
