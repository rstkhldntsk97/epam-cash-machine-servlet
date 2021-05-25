SET GLOBAL TIME_ZONE = "+2:00";
DROP DATABASE IF EXISTS `servlet_db`;
CREATE DATABASE `servlet_db`;
USE `servlet_db`;

CREATE TABLE users (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    `username` VARCHAR(12) NOT NULL UNIQUE,
                    `password` VARCHAR(20) NOT NULL,
                    `role` ENUM('CASHIER', 'SENIOR_CASHIER', 'COMMODITY_EXPERT', 'ADMIN'));

INSERT INTO users (username, password, role) VALUES ('admin', 'admin', 'ADMIN');

CREATE TABLE products (`code` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       `name` VARCHAR(20) NOT NULL UNIQUE,
                       `price` DECIMAL(8,2)  NOT NULL);

CREATE TABLE invoices (`id` BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
                       `total_price` DECIMAL(8,2) NOT NULL,
                       `user_id` BIGINT NOT NULL,
                       `created_at` TIMESTAMP DEFAULT NOW(),
                       FOREIGN KEY(user_id) REFERENCES users(id));

CREATE TABLE invoice_products (`invoice_id` BIGINT NOT NULL,
                               `product_code` BIGINT NOT NULL,
                               `quantity_in_invoice` INT DEFAULT(1),
                               `price` DECIMAL(8,2)  NOT NULL,
                               FOREIGN KEY (invoice_id) REFERENCES invoices(id),
                               FOREIGN KEY (product_code) REFERENCES products(code));
