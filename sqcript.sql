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

INSERT INTO `users` (`username`, `password`, `role_id`) VALUES ('user1', 'pass1', 1);
INSERT INTO `users` (`username`, `password`, `role_id`) VALUES ('user11', 'pass12', 3);
INSERT INTO `users` (`username`, `password`, `role_id`) VALUES ('user22', 'passqwe1', 2);
INSERT INTO `users` (`username`, `password`, `role_id`) VALUES ('user2', 'pass2', 2);
INSERT INTO `users` (`username`, `password`, `role_id`) VALUES ('user3', 'pass3', 3);

INSERT INTO `products` (`product_name`, `price`) VALUES ('car', 110.5);
INSERT INTO `products` (`product_name`, `price`) VALUES ('bike', 11.50);
INSERT INTO `products` (`product_name`, `price`) VALUES ('jeep', 200);

INSERT INTO `stock` (`product_id`, `quantity`) VALUES (1, 10);
INSERT INTO `stock` (`product_id`, `quantity`) VALUES (2, 50);
INSERT INTO `stock` (`product_id`, `quantity`) VALUES (3, 5);

SELECT `product_name`, `price`, `quantity` FROM stock JOIN products ON stock.product_id = products.id;
SELECT `id`, `username`, `password`, `role_name` FROM users JOIN roles ON users.id = roles.user_id WHERE users.id = 3;


SELECT `username`, `password`, `role_name` FROM users Join roles ON users.role_id = roles.id;