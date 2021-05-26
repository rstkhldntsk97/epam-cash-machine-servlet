SET GLOBAL TIME_ZONE = "+2:00";
DROP DATABASE IF EXISTS `servlet_db`;
CREATE DATABASE `servlet_db`;
USE `servlet_db`;


CREATE TABLE role (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                   `role_name` VARCHAR(20) NOT NULL UNIQUE);

INSERT INTO `role` (`role_name`) VALUES ('CASHIER');
INSERT INTO `role` (`role_name`) VALUES ('SENIOR_CASHIER');
INSERT INTO `role` (`role_name`) VALUES ('COMMODITY_EXPERT');
INSERT INTO `role` (`role_name`) VALUES ('ADMIN');


CREATE TABLE user (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                   `username` VARCHAR(12) NOT NULL UNIQUE,
                   `password` VARCHAR(20) NOT NULL,
                   `role_id` INT NOT NULL,
                   FOREIGN KEY(role_id) REFERENCES role(id));

CREATE TABLE product (`code` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      `name` VARCHAR(20) NOT NULL UNIQUE,
                      `price` DECIMAL(8,2)  NOT NULL,
                      `quantity` INT UNSIGNED DEFAULT(0));

CREATE TABLE status (`id` INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
                     `status` VARCHAR(20) NOT NULL);

CREATE TABLE invoice (`id` INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
                      `total_price` DECIMAL(8,2),
                      `user_id` INT NOT NULL,
                      `status_id` INT DEFAULT(1),
                      `created_at` TIMESTAMP DEFAULT NOW(),
                      FOREIGN KEY(user_id) REFERENCES user(id),
                      FOREIGN KEY(status_id) REFERENCES status(id));

CREATE TABLE invoice_has_product (`invoice_id` INT NOT NULL,
                                  `product_code` INT NOT NULL,
                                  `quantity_in_invoice` INT DEFAULT(1),
                                  `price` DECIMAL(8,2)  NOT NULL,
                                  FOREIGN KEY (invoice_id) REFERENCES invoice(id),
                                  FOREIGN KEY (product_code) REFERENCES product(code),
                                  UNIQUE(invoice_id, product_code));

INSERT INTO user (username, password, role_id) VALUES ('admin', 'admin', 4),
                                                      ('senior1', 'admin', 2),
                                                      ('senior2', 'admin', 2),
                                                      ('senior3', 'admin', 2),
                                                      ('expert1', 'admin',3),
                                                      ('cashier1', 'admin', 1),
                                                      ('cashier2', 'admin',1);

INSERT INTO product (name, price, quantity) VALUES ('macbook', 2250, 1000),
                                                   ('airpods', 250, 900),
                                                   ('imac', 5000, 800),
                                                   ('iphone', 1000, 700),
                                                   ('iwatch', 550, 600),
                                                   ('airtag', 50, 500);

INSERT INTO status (status) VALUES ('NEW'), ('CLOSED'), ('DECLINED');

insert into invoice(user_id) VALUES(1), (1), (2);