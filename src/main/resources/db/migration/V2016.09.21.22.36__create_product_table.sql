CREATE TABLE `PRODUCT` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `time_created` datetime DEFAULT NULL,
  `user_id` bigint(20),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ID` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
