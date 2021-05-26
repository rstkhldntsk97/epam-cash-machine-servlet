SET GLOBAL TIME_ZONE = "+2:00";
DROP DATABASE IF EXISTS `servlet_db`;
CREATE DATABASE `servlet_db`;
USE `servlet_db`;

CREATE TABLE user (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                   `username` VARCHAR(12) NOT NULL UNIQUE,
                   `password` VARCHAR(20) NOT NULL,
                   `role` ENUM('CASHIER', 'SENIOR_CASHIER', 'COMMODITY_EXPERT', 'ADMIN'));

INSERT INTO user (username, password, role) VALUES ('admin', 'admin', 'ADMIN');

CREATE TABLE product (`code` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      `name` VARCHAR(20) NOT NULL UNIQUE,
                      `price` DECIMAL(8,2)  NOT NULL,
                      `quantity` INT UNSIGNED DEFAULT(0));

CREATE TABLE invoice (`id` INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
                      `total_price` DECIMAL(8,2),
                      `user_id` INT NOT NULL,
                      `created_at` TIMESTAMP DEFAULT NOW(),
                      `status` ENUM('NEW', 'CLOSED', 'DECLINED') DEFAULT ('NEW'),
                      FOREIGN KEY(user_id) REFERENCES user(id));

CREATE TABLE invoice_has_product (`invoice_id` INT NOT NULL,
                                  `product_code` INT NOT NULL,
                                  `quantity_in_invoice` INT DEFAULT(1),
                                  `price` DECIMAL(8,2)  NOT NULL,
                                  FOREIGN KEY (invoice_id) REFERENCES invoice(id),
                                  FOREIGN KEY (product_code) REFERENCES product(code));

INSERT INTO user (username, password, role) VALUES
('senior1', 'admin', 'SENIOR_CASHIER'),
('senior2', 'admin', 'SENIOR_CASHIER'),
('senior3', 'admin', 'SENIOR_CASHIER'),
('expert1', 'admin','COMMODITY_EXPERT'),
('cashier1', 'admin', 'CASHIER'),
('cashier2', 'admin','CASHIER');

INSERT INTO product (name, price, quantity) VALUES
('macbook', 2250, 1000),
('airpods', 250, 900),
('imac', 5000, 800),
('iphone', 1000, 700),
('iwatch', 550, 600),
('airtag', 50, 500);

INSERT INTO invoice (user_id) VALUES(6), (6),(6),(7),(7);

INSERT INTO invoice_has_product (invoice_id, product_code, quantity_in_invoice, price) VALUES
(1, 1, 2, 4500), (1, 2, 3, 750), (1, 3, 1, 5000),
(2, 4, 4, 4000), (2,5, 1, 1000),
(3, 6, 10, 500),
(4, 2, 3, 750), (4, 1, 3, 6750), (4, 4, 2, 2000),(4, 5, 5, 2850), (4, 6, 8, 400),
(5, 1, 4, 9000), (5, 3, 8, 40000), (5, 6, 20, 1000),(5, 5, 20, 11000);