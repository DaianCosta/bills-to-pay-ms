SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into category (name) values ('Bebida');
insert into category (name) values ('Alimentação');
insert into category (name) values ('Transporte');
insert into category (name) values ('Vestuário');
insert into category (name) values ('Lazer');
insert into category (name) values ('Salário');
insert into category (name) values ('Automomo');
insert into category (name) values ('Estudo');
insert into category (name) values ('Tecnologia');
insert into category (name) values ('Moradia');
insert into category (name) values ('Outros');