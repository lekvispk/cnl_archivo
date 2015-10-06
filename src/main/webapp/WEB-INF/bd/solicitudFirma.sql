/*
SQLyog - Free MySQL GUI v5.19
Host - 5.0.77 : Database - solicitudfirma
*********************************************************************
Server version : 5.0.77
*/

SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `solicitudfirma`;

USE `solicitudfirma`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `solicitud` */

DROP TABLE IF EXISTS `solicitud`;

CREATE TABLE `solicitud` (
  `xml` longblob,
  `rtf` longblob,
  `fecha` datetime default NULL,
  `md5idp` char(50) default NULL,
  `idp` decimal(10,0) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
