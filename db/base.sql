/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.24 : Database - base
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`base` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `base`;

/*Table structure for table `tb_resource` */

DROP TABLE IF EXISTS `tb_resource`;

CREATE TABLE `tb_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `parent_id` int(11) DEFAULT NULL COMMENT '资源父id',
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `source_key` varchar(100) NOT NULL COMMENT '资源唯一标识',
  `type` int(11) NOT NULL COMMENT '资源类型,0:目录;1:菜单;2:按钮',
  `source_url` varchar(500) DEFAULT NULL COMMENT '资源url',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(100) DEFAULT '' COMMENT '图标',
  `is_hide` int(11) DEFAULT '0' COMMENT '是否隐藏',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `tb_resource` */

insert  into `tb_resource`(`id`,`parent_id`,`name`,`source_key`,`type`,`source_url`,`level`,`sort`,`icon`,`is_hide`,`description`,`create_time`,`update_time`) values (1,NULL,'系统基础管理','system:index',0,'',1,1,'fa fa-list',0,'系统基础管理','2016-01-05 12:11:12','2016-12-31 14:54:36'),(2,1,'用户管理','system:user:index',0,'/admin/user/index',2,1,'',0,'用户管理','2016-01-08 12:37:10','2016-12-31 18:12:57'),(3,1,'角色管理','system:role:index',0,'/admin/role/index',2,2,'',0,'角色管理','2016-01-11 22:51:07','2016-12-31 18:13:24'),(4,1,'资源管理','system:resource:index',0,'/admin/resource/index',2,3,'',0,'资源管理','2016-01-11 22:51:55','2016-12-31 18:13:37'),(5,2,'添加','system:user:add',1,'/admin/user/add',3,1,'',0,'添加用户','2016-01-22 00:18:40','2016-12-31 18:06:00'),(6,2,'编辑','system:user:edit',1,'/admin/user/edit',3,2,'',0,'编辑用户','2016-01-22 00:18:40','2016-12-31 18:06:12'),(7,2,'删除','system:user:deleteBatch',1,'/admin/user/deleteBatch',NULL,3,NULL,0,'删除用户','2016-02-05 15:26:32','2016-12-31 18:06:28'),(8,2,'重置密码','system:user:resetPassword',1,'/admin/user/resetPassword',NULL,4,NULL,0,'重置密码','2016-02-27 23:55:13','2016-12-31 18:06:47'),(9,3,'添加','system:role:add',1,'/admin/role/add',NULL,1,NULL,0,'添加','2016-02-27 23:56:52','2016-12-31 18:07:11'),(10,3,'编辑','system:role:edit',1,'/admin/role/edit',NULL,2,NULL,0,'编辑','2016-02-27 23:57:35','2016-12-31 18:07:13'),(11,3,'删除','system:role:deleteBatch',1,'/admin/role/deleteBatch',NULL,3,NULL,0,'删除','2016-02-27 23:58:02','2016-12-31 18:07:15'),(12,3,'分配权限','system:role:permission',1,'/admin/role/permission',NULL,4,NULL,0,'分配权限','2016-02-27 23:59:20','2016-12-31 18:07:18'),(13,4,'添加','system:resource:add',1,'/admin/resource/add',NULL,1,NULL,0,'添加','2016-02-28 00:01:15','2016-12-31 18:07:21'),(14,4,'编辑','system:resource:edit',1,'/admin/resource/edit',NULL,2,NULL,0,'编辑','2016-02-28 00:02:01','2016-12-31 18:07:22'),(15,4,'删除','system:resource:deleteBatch',1,'/admin/resource/deleteBatch',NULL,3,NULL,0,'删除','2016-02-28 00:03:03','2016-12-31 18:07:24');

/*Table structure for table `tb_resources_role` */

DROP TABLE IF EXISTS `tb_resources_role`;

CREATE TABLE `tb_resources_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL COMMENT '资源id',
  `r_id` int(11) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_r_resource_role` (`s_id`),
  KEY `FK_r_role_resource` (`r_id`),
  CONSTRAINT `FK_r_resource_role` FOREIGN KEY (`s_id`) REFERENCES `tb_resource` (`id`),
  CONSTRAINT `FK_r_role_resource` FOREIGN KEY (`r_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='角色权限映射表';

/*Data for the table `tb_resources_role` */

insert  into `tb_resources_role`(`id`,`s_id`,`r_id`,`create_time`) values (1,1,1,'2016-12-28 21:17:27'),(2,2,1,'2016-12-28 21:17:27'),(3,3,1,'2016-12-28 21:17:27'),(4,4,1,'2016-12-28 21:17:27'),(5,5,1,'2016-12-28 21:17:27'),(6,6,1,'2016-12-28 21:17:27'),(7,7,1,'2016-12-28 21:17:27'),(8,8,1,'2016-12-28 21:17:27'),(9,9,1,'2016-12-28 21:17:27'),(10,10,1,'2016-12-28 21:17:27'),(11,11,1,'2016-12-28 21:17:27'),(12,12,1,'2016-12-28 21:17:27'),(13,13,1,'2016-12-28 21:17:27'),(14,14,1,'2016-12-28 21:17:27'),(15,15,1,'2016-12-28 21:17:27');

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `key` varchar(50) NOT NULL COMMENT '角色key',
  `status` int(11) DEFAULT '0' COMMENT '角色状态,0：正常；1：删除',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `tb_role` */

insert  into `tb_role`(`id`,`name`,`key`,`status`,`description`,`create_time`,`update_time`) values (1,'超级管理员','administrator',0,'超级管理员','2016-01-05 12:07:42','2016-02-27 22:30:15'),(2,'管理员','admin',0,'管理员','2016-01-05 12:07:42','2016-02-27 22:30:22'),(3,'普通用户','customer',0,'普通用户','2016-02-28 17:09:40','2016-03-08 22:55:36');

/*Table structure for table `tb_role_user` */

DROP TABLE IF EXISTS `tb_role_user`;

CREATE TABLE `tb_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` int(11) DEFAULT NULL COMMENT '角色id',
  `u_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_r_role_user` (`r_id`),
  KEY `FK_r_user_role` (`u_id`),
  CONSTRAINT `FK_r_role_user` FOREIGN KEY (`r_id`) REFERENCES `tb_role` (`id`),
  CONSTRAINT `FK_r_user_role` FOREIGN KEY (`u_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';

/*Data for the table `tb_role_user` */

insert  into `tb_role_user`(`id`,`r_id`,`u_id`,`create_time`) values (1,1,1,'2016-01-05 12:07:15');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(100) NOT NULL COMMENT '账户名',
  `nick_name` varchar(100) NOT NULL COMMENT '昵称',
  `password` varchar(100) NOT NULL COMMENT '用户密码',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '住址',
  `delete_status` int(11) DEFAULT '0' COMMENT '逻辑删除状态',
  `locked` int(11) DEFAULT '0' COMMENT '是否锁定',
  `description` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户账户表';

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`user_name`,`nick_name`,`password`,`sex`,`birthday`,`telephone`,`email`,`address`,`delete_status`,`locked`,`description`,`create_time`,`update_time`) values (1,'admin','whoismy8023@163.com','96e79218965eb72c92a549dd5a330112',1,'2016-07-21','15923935295','whoismy8023@163.com','成都',0,0,'哈哈这就是我 ','2016-07-21 17:29:19','2016-07-21 17:29:22');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
