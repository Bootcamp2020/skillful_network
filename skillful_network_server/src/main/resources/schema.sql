DROP TABLE IF EXISTS `job_applications`;
CREATE TABLE `job_applications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `submit_date` datetime DEFAULT NULL,
  `job_offer_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);
  
DROP TABLE IF EXISTS `job_offer`;
CREATE TABLE `job_offer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `date_beg` datetime DEFAULT NULL,
  `date_end` datetime DEFAULT NULL,
  `date_upload` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keywords` tinyblob,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);  

DROP TABLE IF EXISTS `qualifications`;
CREATE TABLE `qualifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
);   

DROP TABLE IF EXISTS `skills`;
CREATE TABLE `skills` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);  
  
DROP TABLE IF EXISTS `subscriptions`;
CREATE TABLE `subscriptions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
);  
  
DROP TABLE IF EXISTS `training`;
CREATE TABLE `training` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_beg` datetime DEFAULT NULL,
  `date_end` datetime DEFAULT NULL,
  `date_upload` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration_hours` bigint DEFAULT NULL,
  `financer` varchar(255) DEFAULT NULL,
  `keywords` tinyblob,
  `name` varchar(255) DEFAULT NULL,
  `organisation` varchar(255) DEFAULT NULL,
  `prerequisites` tinyblob,
  PRIMARY KEY (`id`)
);  
  
DROP TABLE IF EXISTS `training_applications`;
CREATE TABLE `training_applications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `submit_date` datetime DEFAULT NULL,
  `training_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
); 

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birth_date` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `validated` bit(1) NOT NULL,
  `photo` bit(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);   

  
  
  
  
  

