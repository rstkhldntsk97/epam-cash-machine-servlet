SET GLOBAL TIME_ZONE = "+3:00";
DROP DATABASE IF EXISTS `servlet_db`;
CREATE DATABASE `servlet_db`;
USE `servlet_db`;

CREATE TABLE language (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       `lang` VARCHAR(2));

INSERT INTO language (lang) VALUES ('EN'), ('UA');

CREATE TABLE user (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                   `username` VARCHAR(12) NOT NULL UNIQUE,
                   `password` VARCHAR(255) NOT NULL,
                   `role` ENUM('CASHIER', 'SENIOR_CASHIER', 'COMMODITY_EXPERT', 'ADMIN'));

INSERT INTO user (username, password, role) VALUES ('admin', '5f4dcc3b5aa765d61d8327deb882cf99', 'ADMIN');

CREATE TABLE product (`code` INT NOT NULL UNIQUE PRIMARY KEY,
                      `price` DECIMAL(8,2)  NOT NULL,
                      `quantity` INT UNSIGNED DEFAULT(0));


CREATE TABLE product_translate (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                `product_code` INT NOT NULL,
                                `lang_id` INT NOT NULL,
                                `name` VARCHAR(255) NOT NULL,
                                FOREIGN KEY (lang_id) REFERENCES language(id),
                                FOREIGN KEY (product_code) REFERENCES product(code));


insert into product (code, price, quantity) values(1, 100, 100),(2, 200, 200),(3, 300, 300),(4, 400, 400),(5, 500, 500),(6, 600, 600),(7, 700, 700);

insert into product_translate(product_code, lang_id, name) values(1, 1, 'potato'),(1, 2, 'kartoxa'),(2,1, 'car'), (2, 2, 'maxina'), (3,1, 'laptop'),(3,2,'noutbuk'), (4,1, 'phone'),(4,2,'telefon'), (5,1, 'TV'),(5,2,'televizor'), (6,1, 'tomato'),(6,2,'pomidor'), (7,1, 'book'),(7,2,'kniga');


CREATE TABLE invoice (`id` INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
                      `total_price` DECIMAL(8,2),
                      `user_id` INT NOT NULL,
                      `created_at` TIMESTAMP DEFAULT NOW(),
                      `status` ENUM('NEW', 'CLOSED', 'DECLINED') NOT NULL DEFAULT ('NEW'),
                      FOREIGN KEY(user_id) REFERENCES user(id));

CREATE TABLE invoice_has_product (`invoice_id` INT NOT NULL,
                                  `product_code` INT NOT NULL,
                                  `quantity_in_invoice` INT DEFAULT(1),
                                  `price` DECIMAL(8,2),
                                  FOREIGN KEY (invoice_id) REFERENCES invoice(id) ON DELETE CASCADE,
                                  FOREIGN KEY (product_code) REFERENCES product(code),
                                  UNIQUE (invoice_id, product_code));

INSERT INTO user (username, password, role) VALUES
('senior1', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
('expert1', '5f4dcc3b5aa765d61d8327deb882cf99','COMMODITY_EXPERT'),
('cashier1', '5f4dcc3b5aa765d61d8327deb882cf99','CASHIER'),
('Alex', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
('Bob', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
('Michael', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
('Sara', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
('Katy', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
('Jimmy', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
('Andrew', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
('Kobe', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
('Leslie', '5f4dcc3b5aa765d61d8327deb882cf99', 'COMMODITY_EXPERT'),
('Ann', '5f4dcc3b5aa765d61d8327deb882cf99','COMMODITY_EXPERT'),
('Philip J.Fry', '5f4dcc3b5aa765d61d8327deb882cf99', 'COMMODITY_EXPERT'),
('Eddie', '5f4dcc3b5aa765d61d8327deb882cf99','COMMODITY_EXPERT');
