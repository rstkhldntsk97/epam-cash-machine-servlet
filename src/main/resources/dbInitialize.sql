DROP DATABASE IF EXISTS `servlet_db`;
CREATE DATABASE `servlet_db`;
USE `servlet_db`;

CREATE TABLE `roles` (
                         `id` BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
                         `role_name` VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO `roles` (`role_name`) VALUES ('CASHIER');
INSERT INTO `roles` (`role_name`) VALUES ('SENIOR_CASHIER');
INSERT INTO `roles` (`role_name`) VALUES ('MANAGER');

CREATE TABLE users (
                       `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       `username` VARCHAR(12) NOT NULL UNIQUE,
                       `password` VARCHAR(20) NOT NULL,
                       `role_id` BIGINT NOT NULL,
                       FOREIGN KEY (`role_id`) REFERENCES roles(id)
);

CREATE TABLE `products` (
                            `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `product_name` VARCHAR(20) NOT NULL UNIQUE,
                            `price` DECIMAL(8,2) NOT NULL
);

CREATE TABLE `stock` (
                         `product_id` BIGINT NOT NULL UNIQUE,
                         `quantity` BIGINT NOT NULL,
                         FOREIGN KEY(`product_id`) REFERENCES products(id)
);

CREATE TABLE `receipt` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
                           `total_price` DECIMAL(8,2) NOT NULL,
                           `user_id` BIGINT NOT NULL,
                           FOREIGN KEY(`user_id`) REFERENCES users(id)
);