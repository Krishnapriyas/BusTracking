# Host: localhost  (Version 5.1.43-community)
# Date: 2017-11-12 12:32:49
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "bus_stop_info"
#

DROP TABLE IF EXISTS `bus_stop_info`;
CREATE TABLE `bus_stop_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stop_id` int(11) NOT NULL DEFAULT '0',
  `trip_id` int(11) NOT NULL DEFAULT '0',
  `stop_order` int(11) NOT NULL DEFAULT '0',
  `stop_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `trip_id` (`trip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Data for table "bus_stop_info"
#

INSERT INTO `bus_stop_info` VALUES (1,1,1,1,'02:00'),(2,7,1,5,'04:00'),(5,1,3,1,'01:00'),(6,7,3,4,'02:00'),(7,2,4,1,'13:00'),(8,9,4,4,'15:00');

#
# Structure for table "login_info"
#

DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL DEFAULT '',
  `status` varchar(255) NOT NULL DEFAULT 'pending',
  `type` varchar(255) NOT NULL DEFAULT '',
  `username` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Data for table "login_info"
#

INSERT INTO `login_info` VALUES (1,'admin','pending','admin','admin'),(2,'12345','approved','owner','sow'),(3,'12345','approved','owner','midhun'),(4,'1641','approved','driver','gir361'),(5,'5921','approved','driver','saf340'),(6,'12345','approved','user','ks@gmail.com');

#
# Structure for table "driver_info"
#

DROP TABLE IF EXISTS `driver_info`;
CREATE TABLE `driver_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) NOT NULL DEFAULT '0',
  `driver_name` varchar(255) NOT NULL DEFAULT '',
  `driver_phone` varchar(255) NOT NULL DEFAULT '',
  `driver_email` varchar(255) NOT NULL DEFAULT '',
  `driver_address` varchar(255) NOT NULL DEFAULT '',
  `bus_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `login_id` (`login_id`),
  CONSTRAINT `driver_info_ibfk_1` FOREIGN KEY (`login_id`) REFERENCES `login_info` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Data for table "driver_info"
#

INSERT INTO `driver_info` VALUES (1,4,'gireesh','4524353465','gireesh@hh.ghg','dsfsdfds',1),(2,5,'safar','9879877777','safar@gmail.com','sdkfsfjsdaf',2);

#
# Structure for table "bus_info"
#

DROP TABLE IF EXISTS `bus_info`;
CREATE TABLE `bus_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bus_description` varchar(255) DEFAULT NULL,
  `bus_name` varchar(255) DEFAULT NULL,
  `bus_number` varchar(255) DEFAULT NULL,
  `owner_id` int(11) NOT NULL DEFAULT '0',
  `lat` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  `bus_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `bus_info_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `login_info` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for table "bus_info"
#

INSERT INTO `bus_info` VALUES (1,'sajfhskfdsf','Deva','8788',2,'9.96716216','76.29482314','start'),(2,'asdfsad','sandra','3453',2,NULL,NULL,NULL),(3,'dfsd','ms menon','87868',2,NULL,NULL,NULL);

#
# Structure for table "bus_trip_info"
#

DROP TABLE IF EXISTS `bus_trip_info`;
CREATE TABLE `bus_trip_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bus_start` varchar(255) NOT NULL DEFAULT '',
  `bus_stop` varchar(255) NOT NULL DEFAULT '',
  `trip_time` varchar(255) NOT NULL DEFAULT '',
  `bus_id` int(11) NOT NULL DEFAULT '0',
  `stop_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bus_id` (`bus_id`),
  CONSTRAINT `bus_trip_info_ibfk_1` FOREIGN KEY (`bus_id`) REFERENCES `bus_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Data for table "bus_trip_info"
#

INSERT INTO `bus_trip_info` VALUES (1,'Manorama','thrissur','02:00',1,5),(3,'Manorama','thrissur','01:00',2,4),(4,'Kadavanthara','angamali','13:00',2,4);

#
# Structure for table "owner_info"
#

DROP TABLE IF EXISTS `owner_info`;
CREATE TABLE `owner_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `owner_address` varchar(255) DEFAULT NULL,
  `owner_email` varchar(255) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `owner_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `login_id` (`login_id`),
  CONSTRAINT `owner_info_ibfk_1` FOREIGN KEY (`login_id`) REFERENCES `login_info` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Data for table "owner_info"
#

INSERT INTO `owner_info` VALUES (1,2,'xvzxvxv','sowbagya@gmail.com','Sowbagya','2348726481'),(2,3,'dskhfkjdfh','midhun@gmail.com','Midhun','7268756437');

#
# Structure for table "stops_info"
#

DROP TABLE IF EXISTS `stops_info`;
CREATE TABLE `stops_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stop_name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

#
# Data for table "stops_info"
#

INSERT INTO `stops_info` VALUES (1,'Manorama'),(2,'Kadavanthara'),(3,'vytila'),(4,'fort kochi'),(5,'panampilly'),(6,'mg road'),(7,'thrissur'),(8,'aluva'),(9,'angamali'),(10,'chalakudi'),(11,'irinjalakuda');

#
# Structure for table "user_info"
#

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL DEFAULT '',
  `phone` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `login_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Data for table "user_info"
#

INSERT INTO `user_info` VALUES (1,'gireesh','8893580892','ks@gmail.com',6);
