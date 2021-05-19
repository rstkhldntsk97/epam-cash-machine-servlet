SET GLOBAL TIME_ZONE = "+2:00";
DROP DATABASE IF EXISTS `servlet_db`;
CREATE DATABASE `servlet_db`;
USE `servlet_db`;

CREATE TABLE `roles` (`id` BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
                      `role_name` VARCHAR(20) NOT NULL UNIQUE);

INSERT INTO `roles` (`role_name`) VALUES ('CASHIER');
INSERT INTO `roles` (`role_name`) VALUES ('SENIOR_CASHIER');
INSERT INTO `roles` (`role_name`) VALUES ('MANAGER');

CREATE TABLE `users` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      `username` VARCHAR(12) NOT NULL UNIQUE,
                      `password` VARCHAR(20) NOT NULL,
                      `role_id` BIGINT NOT NULL,
                      FOREIGN KEY (`role_id`) REFERENCES roles(id));

CREATE TABLE `products` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `code` DECIMAL(4,0) NOT NULL UNIQUE,
                         `product_name` VARCHAR(20) NOT NULL UNIQUE,
                         `price` DECIMAL(8,2)  NOT NULL);

CREATE TABLE `stock` (`product_id` BIGINT NOT NULL UNIQUE,
                      `quantity` BIGINT UNSIGNED NOT NULL DEFAULT(0),
                      FOREIGN KEY(`product_id`) REFERENCES products(id));

CREATE TABLE `receipts` (`id` BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
                         `total_price` DECIMAL(8,2) NOT NULL,
                         `user_id` BIGINT NOT NULL,
                         `is_closed` TINYINT NOT NULL DEFAULT(0),
                         FOREIGN KEY(`user_id`) REFERENCES users(id));

CREATE TABLE `products_in_receipt` (`receipt_id` BIGINT NOT NULL,
                                    `product_id` BIGINT NOT NULL,
                                    FOREIGN KEY (`receipt_id`) REFERENCES receipts(id),
                                    FOREIGN KEY (`product_id`) REFERENCES products(id));

CREATE TABLE `product_in_check` (`id` BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
                                 `price` DECIMAL(8,2) NOT NULL,
                                 `quantity` BIGINT UNSIGNED NOT NULL DEFAULT (1),
                                 `receipt_id` BIGINT NOT NULL,
                                 `product_id` BIGINT NOT NULL,
                                 FOREIGN KEY(receipt_id) REFERENCES receipts(id),
                                 FOREIGN KEY(product_id) REFERENCES products(id));