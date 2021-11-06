/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - miaosha
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`miaosha` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `miaosha`;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `price` decimal(64,0) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL,
  `sales` int(11) NOT NULL DEFAULT '0',
  `img_url` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`id`,`title`,`price`,`description`,`sales`,`img_url`) values (1,'3060显卡','5000','英伟达3060显卡',0,'https://img12.360buyimg.com/n1/jfs/t1/185493/33/9386/122950/60c99f24E062204b0/79a3961603731ba7.jpg'),(2,'苹果13','6799','iphone手机13 ',2,'https://img14.360buyimg.com/n0/jfs/t1/109384/3/17626/212571/614be585Eb8f44b8c/be8ecd190a639396.jpg'),(3,'小米11','4399','小米11手机12+256',0,'https://img14.360buyimg.com/n0/jfs/t1/143274/14/21375/93920/617e8ca6E22ca47f2/f9441af6fa02eae0.jpg');

/*Table structure for table `item_stock` */

DROP TABLE IF EXISTS `item_stock`;

CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `item_stock` */

insert  into `item_stock`(`id`,`stock`,`item_id`) values (1,100,1),(2,498,2),(3,50,3);

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `item_price` double NOT NULL DEFAULT '0',
  `amount` int(11) NOT NULL DEFAULT '0',
  `order_price` double NOT NULL DEFAULT '0',
  `promo_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_info` */

insert  into `order_info`(`id`,`user_id`,`item_id`,`item_price`,`amount`,`order_price`,`promo_id`) values ('2021110600000000',1,2,5999,1,5999,1),('2021110600000100',1,2,5999,1,5999,1);

/*Table structure for table `promo` */

DROP TABLE IF EXISTS `promo`;

CREATE TABLE `promo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) NOT NULL DEFAULT '0',
  `start_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `promo_item_price` double NOT NULL DEFAULT '0',
  `end_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `promo` */

insert  into `promo`(`id`,`promo_name`,`start_date`,`item_id`,`promo_item_price`,`end_date`) values (1,'苹果降价','2021-11-06 21:01:11',2,5999,'2022-05-14 21:01:13'),(2,'显卡打折','2022-04-22 21:01:42',1,4888,'2024-02-15 21:02:05');

/*Table structure for table `sequence_info` */

DROP TABLE IF EXISTS `sequence_info`;

CREATE TABLE `sequence_info` (
  `name` varchar(255) NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT '0',
  `step` int(11) NOT NULL DEFAULT '0',
  `max_value` int(11) NOT NULL DEFAULT '0',
  `init_value` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sequence_info` */

insert  into `sequence_info`(`name`,`current_value`,`step`,`max_value`,`init_value`) values ('order_info',2,1,0,0);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `gender` int(11) NOT NULL DEFAULT '0' COMMENT '//1代表男性,2代表女性',
  `age` int(11) NOT NULL,
  `telephone` varchar(64) NOT NULL,
  `register_mode` varchar(64) NOT NULL COMMENT '//byphone,bywechat,byalipay',
  `third_party_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telephone_unique_index` (`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`name`,`gender`,`age`,`telephone`,`register_mode`,`third_party_id`) values (1,'add',1,18,'123456','byphone',NULL);

/*Table structure for table `user_password` */

DROP TABLE IF EXISTS `user_password`;

CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user_password` */

insert  into `user_password`(`id`,`encrpt_password`,`user_id`) values (1,'4QrcOUm6Wau+VuBX8g+IPg==',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
