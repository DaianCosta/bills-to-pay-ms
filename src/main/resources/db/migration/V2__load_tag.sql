SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into tag (name) values ('meu');
insert into tag (name) values ('sinaira');
insert into tag (name) values ('viacorpus');
insert into tag (name) values ('tio maninho');
insert into tag (name) values ('ketlyn');
insert into tag (name) values ('free-santander');
insert into tag (name) values ('samsung-itaucard');
insert into tag (name) values ('nubank');
