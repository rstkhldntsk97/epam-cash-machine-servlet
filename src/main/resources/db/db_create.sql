SET GLOBAL TIME_ZONE = "+3:00";
DROP DATABASE IF EXISTS `servlet_db`;
CREATE DATABASE `servlet_db`;
USE `servlet_db`;

CREATE TABLE language (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       `ISO` VARCHAR(2));

INSERT INTO language (ISO) VALUES ('EN'), ('UA');

CREATE TABLE user (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                   `username` VARCHAR(12) NOT NULL UNIQUE,
                   `password` VARCHAR(255) NOT NULL,
                   `role` ENUM('CASHIER', 'SENIOR_CASHIER', 'COMMODITY_EXPERT', 'ADMIN'));

INSERT INTO user (username, password, role) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN');

CREATE TABLE product (`code` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      `price` DECIMAL(8,2)  NOT NULL,
                      `quantity` INT UNSIGNED DEFAULT(0));

CREATE TABLE productEN (`product_code` INT NOT NULL UNIQUE,
                        `name` VARCHAR(255) UNIQUE);

CREATE TABLE productUA (`product_code` INT NOT NULL UNIQUE,
                        `name` VARCHAR(255) UNIQUE);

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
('senior1', '21232f297a57a5a743894a0e4a801fc3', 'SENIOR_CASHIER'),
('expert1', '21232f297a57a5a743894a0e4a801fc3','COMMODITY_EXPERT'),
('cashier1', '21232f297a57a5a743894a0e4a801fc3','CASHIER'),
('Alex', '21232f297a57a5a743894a0e4a801fc3', 'CASHIER'),
('Bob', '21232f297a57a5a743894a0e4a801fc3', 'CASHIER'),
('Michael', '21232f297a57a5a743894a0e4a801fc3', 'CASHIER'),
('Sara', '21232f297a57a5a743894a0e4a801fc3', 'CASHIER'),
('Katy', '21232f297a57a5a743894a0e4a801fc3', 'SENIOR_CASHIER'),
('Jimmy', '21232f297a57a5a743894a0e4a801fc3', 'SENIOR_CASHIER'),
('Andrew', '21232f297a57a5a743894a0e4a801fc3', 'SENIOR_CASHIER'),
('Kobe', '21232f297a57a5a743894a0e4a801fc3', 'SENIOR_CASHIER'),
('Leslie', '21232f297a57a5a743894a0e4a801fc3', 'COMMODITY_EXPERT'),
('Ann', '21232f297a57a5a743894a0e4a801fc3','COMMODITY_EXPERT'),
('Philip J.Fry', '21232f297a57a5a743894a0e4a801fc3', 'COMMODITY_EXPERT'),
('Eddie', '21232f297a57a5a743894a0e4a801fc3','COMMODITY_EXPERT');

INSERT INTO product (price, quantity) VALUES
(1200, 1000),
(100, 900),
(5000, 800),
(600, 700),
(300, 600),
(50, 500),
(200, 600),
(500, 600),
(1300, 600),
(2200, 600),
(4500, 600),
(700, 600),
(4000, 600),
(200, 600),
(900, 600),
(600, 600),
(500, 600),
(300, 600),
(700, 600),
(800, 600),
(900, 600),
(1100, 600),
(500, 600),
(2000, 600),
(100, 600),
(75000, 600),
(125000, 600),
(120000, 600),
(100000, 600),
(80000, 600),
(12050, 600),
(3000, 600),
(100500, 600),
(100500, 600),
(100, 50),
(100000, 1);

INSERT INTO productEN (product_code, name) VALUES
(1, 'MacBookAir'),
(2, 'AirPods'),
(3, 'iMac'),
(4, 'iPhone XS'),
(5, 'Apple Watch 3'),
(6, 'AirTag'),
(7, 'AirPods Pro'),
(8, 'AirPods Max'),
(9, 'MacBook Pro 16'),
(10, 'MacBook Pro 13'),
(11, 'Mac Pro'),
(12, 'MacMini'),
(13, 'Pro Display XDR'),
(14, 'вапр'),
(15, 'OS Big Sur'),
(16, 'iPad Pro'),
(17, 'iPad Air'),
(18, 'Apple TV 4K'),
(19, 'Apple Pencil'),
(20, 'iPhone 11'),
(21, 'iPhone 11 pro'),
(22, 'iPhone 12'),
(23, 'iPhone 12 Pro'),
(24, 'Apple Watch 6'),
(25, 'NOKIA'),
(26, 'Passat'),
(27, 'Audi'),
(28, 'BMW'),
(29, 'Ford'),
(30, 'Mazda'),
(31, 'iTable'),
(32, 'iWindows'),
(33, 'JAVA'),
(34, 'THE BEST'),
(35, 'A C# .NET'),
(36, 'Linus Torvald\'s brain');

INSERT INTO productUA (product_code, name) VALUES
(1, 'МакБукЕйр'),
(2, 'ЕйрПодс'),
(3, 'іМак'),
(4, 'айфон хс'),
(5, 'епл воч 3'),
(6, 'йцук'),
(7, 'цуке'),
(8, 'укен'),
(9, 'кнг'),
(10, 'егш'),
(11, 'нгшщ'),
(12, 'фіва'),
(13, 'авіп'),
(14, 'вапр'),
(15, 'апро'),
(16, 'прол'),
(17, 'ячсм'),
(18, 'смит'),
(19, 'митіфвь'),
(20, 'ифівтьб'),
(21, 'йціфвук'),
(22, 'цуапке'),
(23, 'укрен'),
(24, 'фівами'),
(25, 'фір'),
(26, 'п ку'),
(27, 'аівп '),
(28, 'фів '),
(29, 'фівцйу'),
(30, 'апкц'),
(31, 'апр'),
(32, 'кцунуке'),
(33, 'йцуцупк'),
(34, 'йцуфіс'),
(35, 'мчстгл'),
(36, 'гшщдгш');

INSERT INTO invoice (user_id) VALUES(6),(6),(6),(7),(7),(7),(6),(6),(6),(7);

INSERT INTO invoice_has_product (invoice_id, product_code, quantity_in_invoice, price) VALUES
(1, 1, 2, 4400), (1, 2, 3, 300), (1, 3, 1, 5000),
(2, 4, 4, 2400), (2,5, 1, 300),
(3, 6, 10, 500),
(4, 24, 3, 1200), (4, 1, 3, 6750), (4, 20, 2, 1600),(4, 10, 5, 12000), (4, 6, 8, 400),
(5, 11, 4, 18000), (5, 3, 8, 40000), (5, 16, 20, 12000),(5, 15, 20, 18000),
(6, 12, 3, 1200), (6, 13, 3, 12000), (6, 14, 2, 400),(6, 15, 5, 4500), (6, 16, 8, 4800),(6, 17, 3, 1500),
(7, 18, 10, 3000), (7, 19, 20, 14000),
(8, 21, 30, 27000), (8, 22, 30, 33000), (8, 23, 20, 10000),(8, 24, 50, 20000),
(9, 9, 30, 39000), (9, 8, 3, 1500), (9, 7, 2, 1400),
(10, 19, 3, 2100);

update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 1), status = 'CLOSED' where invoice.id = 1;
update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 2), status = 'CLOSED' where invoice.id = 2;
update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 3), status = 'CLOSED' where invoice.id = 3;
update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 4), status = 'CLOSED' where invoice.id = 4;
update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 5), status = 'CLOSED' where invoice.id = 5;
update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 6), status = 'CLOSED' where invoice.id = 6;
update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 7), status = 'CLOSED' where invoice.id = 7;
update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 8), status = 'CLOSED' where invoice.id = 8;
update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 9), status = 'CLOSED' where invoice.id = 9;
update invoice set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 10), status = 'CLOSED' where invoice.id = 10;
