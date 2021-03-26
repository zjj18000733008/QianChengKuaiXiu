/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 8.0.14 : Database - qiancheng_repair
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`qiancheng_repair` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `qiancheng_repair`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receiver_name` varchar(125) DEFAULT NULL,
  `mobile` varchar(125) DEFAULT NULL,
  `area` varchar(125) DEFAULT NULL COMMENT '收货人所在地区',
  `street_address` varchar(125) DEFAULT NULL COMMENT '街道地址',
  `user_id` int(11) DEFAULT NULL COMMENT '地址所属的用户的id',
  `state` char(2) DEFAULT NULL COMMENT '1:当前地址为该用户的默认地址,0:该地址是用户的备用地址,-1:该地址已被用户删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_type_id` int(11) DEFAULT NULL,
  `title` varchar(125) DEFAULT NULL,
  `content` text,
  `state` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `article_type_id` (`article_type_id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`article_type_id`) REFERENCES `article_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `article_type` */

DROP TABLE IF EXISTS `article_type`;

CREATE TABLE `article_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_type` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authorities` varchar(112) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `concret_type` */

DROP TABLE IF EXISTS `concret_type`;

CREATE TABLE `concret_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) NOT NULL COMMENT '商品具体类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `homepage_picture` */

DROP TABLE IF EXISTS `homepage_picture`;

CREATE TABLE `homepage_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` text,
  `state` char(2) DEFAULT NULL COMMENT '1:发布,0:不发布',
  `type` char(2) DEFAULT NULL COMMENT '1:轮播图,2:广告',
  `product_id` int(11) DEFAULT NULL,
  `create_data` varchar(125) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Table structure for table `merchant` */

DROP TABLE IF EXISTS `merchant`;

CREATE TABLE `merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) DEFAULT NULL,
  `address` varchar(125) DEFAULT NULL,
  `phone` varchar(125) DEFAULT NULL,
  `longitude` varchar(125) DEFAULT NULL,
  `latitude` varchar(125) DEFAULT NULL,
  `img` varchar(125) DEFAULT NULL,
  `url` varchar(125) DEFAULT NULL COMMENT '商户网页地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` varchar(125) NOT NULL,
  `deliveryman_id` int(11) DEFAULT NULL COMMENT '取/送人员id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `address_id` int(11) DEFAULT NULL COMMENT '订单要送到的地址',
  `pattern` enum('店家送上门','客户送上门','店家上门取','客户上门取') DEFAULT NULL COMMENT '配送方式/服务方式',
  `payment_method` varchar(125) DEFAULT NULL COMMENT '付款方式',
  `leave_word` varchar(125) DEFAULT NULL COMMENT '留言',
  `servicing_time` varchar(125) DEFAULT NULL COMMENT '服务时间',
  `goods_amount` double DEFAULT NULL COMMENT '商品金额',
  `freight_charge` double DEFAULT NULL COMMENT '运费',
  `actual_amount` double DEFAULT NULL COMMENT '实际金额/代付金额',
  `order_time` varchar(125) DEFAULT NULL COMMENT '用户的下单时间',
  `pay_time` varchar(125) DEFAULT NULL COMMENT '用户的付款时间',
  `state` char(2) DEFAULT NULL COMMENT '3:被指派人已经取/送成功2:已支付且已被指派,1:已支付,0:未支付,-1:已取消',
  PRIMARY KEY (`id`),
  KEY `address_id` (`address_id`),
  KEY `user_id` (`user_id`),
  KEY `deliveryman_id` (`deliveryman_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`deliveryman_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(125) NOT NULL,
  `specification_id` int(11) DEFAULT NULL,
  `buynum` int(11) DEFAULT NULL COMMENT '购买数量',
  `unit_price` double DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`id`),
  KEY `specification_id` (`specification_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`specification_id`) REFERENCES `specification` (`id`),
  CONSTRAINT `orderitem_ibfk_3` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(125) DEFAULT NULL COMMENT '商品名称',
  `overview` varchar(125) DEFAULT NULL COMMENT '商品概述',
  `type_id` int(11) DEFAULT NULL COMMENT '商品类型id',
  `concret_type_id` int(11) DEFAULT NULL COMMENT '商品具体类型id',
  `second_type_id` int(11) DEFAULT NULL COMMENT '二级分类id',
  `surface_img` text COMMENT '封面图地址',
  `slide_img` text COMMENT '商品的轮播图片地址',
  `intro_img` text COMMENT '商品详细信息图片地址',
  `weight` enum('10','9','8','7','6','5','4','3','2','1') DEFAULT NULL COMMENT '商品权重',
  `add_time` varchar(125) DEFAULT NULL COMMENT '添加时间',
  `modify_time` varchar(125) DEFAULT NULL COMMENT '修改时间',
  `state` char(2) DEFAULT NULL COMMENT '商品状态(1:启用,0:未启用)',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `product_ibfk_2` (`concret_type_id`),
  KEY `product_ibfk_3` (`second_type_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `producttype` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`concret_type_id`) REFERENCES `concret_type` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`second_type_id`) REFERENCES `second_type` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

/*Table structure for table `producttype` */

DROP TABLE IF EXISTS `producttype`;

CREATE TABLE `producttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(125) DEFAULT NULL COMMENT '商品类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `repair_brand` */

DROP TABLE IF EXISTS `repair_brand`;

CREATE TABLE `repair_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) DEFAULT NULL COMMENT '品牌名称',
  `electric_appliance_id` int(11) DEFAULT NULL COMMENT '关联的电器id',
  `weight` enum('1','2','3','4','5','6','7','8','9','10') DEFAULT NULL COMMENT '权重,权重越重越靠前',
  PRIMARY KEY (`id`),
  KEY `electric_appliance_id` (`electric_appliance_id`),
  CONSTRAINT `repair_brand_ibfk_1` FOREIGN KEY (`electric_appliance_id`) REFERENCES `repair_electrical_appliance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `repair_electrical_appliance` */

DROP TABLE IF EXISTS `repair_electrical_appliance`;

CREATE TABLE `repair_electrical_appliance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) DEFAULT NULL COMMENT '电器种类名称(如:手机,平板...)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `repair_malfunction` */

DROP TABLE IF EXISTS `repair_malfunction`;

CREATE TABLE `repair_malfunction` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '故障类型id',
  `name` varchar(125) DEFAULT NULL COMMENT '故障类型',
  `model_id` int(11) DEFAULT NULL COMMENT '关联的机型id',
  PRIMARY KEY (`id`),
  KEY `model_id` (`model_id`),
  CONSTRAINT `repair_malfunction_ibfk_1` FOREIGN KEY (`model_id`) REFERENCES `repair_model` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Table structure for table `repair_malfunction_item` */

DROP TABLE IF EXISTS `repair_malfunction_item`;

CREATE TABLE `repair_malfunction_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '具体的故障类型的id',
  `item_name` varchar(125) DEFAULT NULL COMMENT '具体故障类型',
  `original_price` double DEFAULT NULL COMMENT '原价',
  `current_price` double DEFAULT NULL COMMENT '现价',
  `malfunction_id` int(11) DEFAULT NULL COMMENT '关联的故障类型id',
  PRIMARY KEY (`id`),
  KEY `malfunction_id` (`malfunction_id`),
  CONSTRAINT `repair_malfunction_item_ibfk_1` FOREIGN KEY (`malfunction_id`) REFERENCES `repair_malfunction` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

/*Table structure for table `repair_model` */

DROP TABLE IF EXISTS `repair_model`;

CREATE TABLE `repair_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '机型id',
  `name` varchar(125) DEFAULT NULL COMMENT '机型',
  `brand_id` int(11) DEFAULT NULL COMMENT '关联的品牌id',
  `weight` enum('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50') DEFAULT NULL COMMENT '权重,权重越重越靠前',
  PRIMARY KEY (`id`),
  KEY `brand_id` (`brand_id`),
  CONSTRAINT `repair_model_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `repair_brand` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Table structure for table `repair_waitforcall` */

DROP TABLE IF EXISTS `repair_waitforcall`;

CREATE TABLE `repair_waitforcall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) DEFAULT NULL COMMENT '选择的商户的id',
  `phone` varchar(125) DEFAULT NULL,
  `name` varchar(125) DEFAULT NULL,
  `malfunction_item_id` int(11) NOT NULL,
  `state` char(2) DEFAULT '0' COMMENT '1:已处理,0:未处理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(125) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `role_authorities` */

DROP TABLE IF EXISTS `role_authorities`;

CREATE TABLE `role_authorities` (
  `role_id` int(11) NOT NULL,
  `authorities_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`authorities_id`),
  KEY `role_authorities_ibfk_2` (`authorities_id`),
  CONSTRAINT `role_authorities_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_authorities_ibfk_2` FOREIGN KEY (`authorities_id`) REFERENCES `authorities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `second_type` */

DROP TABLE IF EXISTS `second_type`;

CREATE TABLE `second_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) DEFAULT NULL COMMENT '二级分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `shoppingcart` */

DROP TABLE IF EXISTS `shoppingcart`;

CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `specification_id` int(11) NOT NULL,
  `specificationImg` varchar(250) NOT NULL COMMENT '规格图片',
  `unitPrice` double NOT NULL COMMENT '此规格商品的单价',
  `totalPrice` double NOT NULL COMMENT '此规格商品的总价',
  `num` int(11) NOT NULL COMMENT '此规格商品的数量',
  `product_name` varchar(125) NOT NULL COMMENT '商品的名称',
  `add_time` varchar(125) NOT NULL COMMENT '此规格商品添加的时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `specification_id` (`specification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `specification` */

DROP TABLE IF EXISTS `specification`;

CREATE TABLE `specification` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品规格的id',
  `name` varchar(125) DEFAULT NULL COMMENT '规格名称',
  `product_id` int(11) DEFAULT NULL COMMENT '商品规格所属的商品的id',
  `original_price` double DEFAULT NULL COMMENT '这种规格商品的原价',
  `current_price` double DEFAULT NULL COMMENT '这种规格商品的现价',
  `inventory` int(11) DEFAULT NULL COMMENT '库存数',
  `img` text COMMENT '这种规格商品的图片地址',
  `state` char(2) DEFAULT NULL COMMENT '1:货源充足,0:已售罄;-1:已下架',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `specification_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(125) NOT NULL COMMENT '用户名',
  `password` varchar(125) NOT NULL COMMENT '密码',
  `real_name` varchar(125) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(125) DEFAULT NULL COMMENT '电话',
  `state` char(2) DEFAULT '1' COMMENT '状态',
  `registration_date` varchar(125) DEFAULT NULL COMMENT '注册时间',
  `profile_img` varchar(125) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Table structure for table `staff_role` */

DROP TABLE IF EXISTS `staff_role`;

CREATE TABLE `staff_role` (
  `role_id` int(11) NOT NULL,
  `staff_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`staff_id`),
  KEY `staff_role_ibfk_2` (`staff_id`),
  CONSTRAINT `staff_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `staff_role_ibfk_2` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(125) DEFAULT NULL COMMENT '用户的openid',
  `gender` char(2) DEFAULT NULL COMMENT '1:男,0:女',
  `user_avatar` varchar(250) DEFAULT NULL COMMENT '用户头像地址',
  `role_id` int(11) DEFAULT '1' COMMENT '用户角色',
  `city` varchar(125) DEFAULT NULL,
  `province` varchar(125) DEFAULT NULL,
  `country` varchar(125) DEFAULT NULL,
  `nickname` varchar(125) DEFAULT NULL,
  `phone_number` varchar(125) DEFAULT NULL,
  `session_key` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Table structure for table `user_authorities` */

DROP TABLE IF EXISTS `user_authorities`;

CREATE TABLE `user_authorities` (
  `user_id` int(11) NOT NULL,
  `authorities_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`authorities_id`),
  KEY `user_authorities_ibfk_2` (`authorities_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `user_role_ibfk_2` (`role_id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
