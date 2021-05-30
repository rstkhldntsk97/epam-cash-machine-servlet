SET GLOBAL TIME_ZONE = "+3:00";
DROP DATABASE IF EXISTS `servlet_db`;
CREATE DATABASE `servlet_db`;
USE `servlet_db`;

CREATE TABLE user (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                   `username` VARCHAR(12) NOT NULL UNIQUE,
                   `password` VARCHAR(255) NOT NULL,
                   `role` ENUM('CASHIER', 'SENIOR_CASHIER', 'COMMODITY_EXPERT', 'ADMIN'));

INSERT INTO user (username, password, role) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN');

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
                                  `price` DECIMAL(8,2),
                                  FOREIGN KEY (invoice_id) REFERENCES invoice(id) ON DELETE CASCADE,
                                  FOREIGN KEY (product_code) REFERENCES product(code),
                                  UNIQUE (invoice_id, product_code));

INSERT INTO user (username, password, role) VALUES
('senior1', '21232f297a57a5a743894a0e4a801fc3', 'SENIOR_CASHIER'),
('senior2', '21232f297a57a5a743894a0e4a801fc3', 'SENIOR_CASHIER'),
('senior3', '21232f297a57a5a743894a0e4a801fc3', 'SENIOR_CASHIER'),
('expert1', '21232f297a57a5a743894a0e4a801fc3','COMMODITY_EXPERT'),
('cashier1', '21232f297a57a5a743894a0e4a801fc3', 'CASHIER'),
('cashier2', '21232f297a57a5a743894a0e4a801fc3','CASHIER');

INSERT INTO product (name, price, quantity) VALUES
('MacBookAir', 1200, 1000),
('AirPods', 100, 900),
('iMac', 5000, 800),
('iPhone XS', 600, 700),
('Apple Watch 3', 300, 600),
('AirTag', 50, 500),
('AirPods Pro', 200, 600),
('AirPods Max', 500, 600),
('MacBook Pro 16', 1300, 600),
('MacBook Pro 13', 2200, 600),
('Mac Pro', 4500, 600),
('MacMini', 700, 600),
('Pro Display XDR', 4000, 600),
('OS Big Sur', 200, 600),
('iPad Pro', 900, 600),
('iPad Air', 600, 600),
('Apple TV 4K', 500, 600),
('Apple Pencil', 300, 600),
('iPhone 11', 700, 600),
('iPhone 11 Pro', 800, 600),
('iPhone 12', 900, 600),
('iPhone 12 Pro', 1100, 600),
('Apple Watch 6', 500, 600),
('Apple Watch 5', 400, 600);

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

-- update invoice set created_at = ('2021-05-25') where invoice.id = 10;


-- select product.name, product.code, product.price, quantity_in_invoice, created_at, user.username, user.id, user.password, invoice.status from invoice
-- join invoice_has_product on invoice.id = invoice_has_product.invoice_id
-- join product on invoice_has_product.product_code = product.code
-- join user on user.id = invoice.user_id where invoice.id = 6;

-- update invoice set status = 'CLOSED' where invoice.id = 1;
-- SELECT user.id, user.username, user.password,
--  product.code, product.name, product.quantity, product.price,
--  invoice.id, invoice.created_at, invoice.total_price, invoice.status
--  FROM INVOICE
--  join user on invoice.user_id = user.id
--  join invoice_has_product on invoice.id = invoice_has_product.invoice_id
--  join product on product.code = invoice_has_product.product_code where invoice.id = 1;

select * from invoice join user on user.id = invoice.user_id join invoice_has_product on invoice.id = invoice_has_product.invoice_id join product on product.code = invoice_has_product.product_code where user.id = 6 and invoice.id = 1;


