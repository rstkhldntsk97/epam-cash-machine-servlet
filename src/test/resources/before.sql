SET GLOBAL TIME_ZONE = "+3:00";
DROP DATABASE IF EXISTS `servlet_db_test`;
CREATE DATABASE `servlet_db_test`;
USE `servlet_db_test`;

CREATE TABLE language
(
    `id`   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `lang` VARCHAR(2)
);

INSERT INTO language (lang)
VALUES ('EN'),
       ('UA');

CREATE TABLE user
(
    `id`       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(12)  NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `role`     ENUM ('CASHIER', 'SENIOR_CASHIER', 'COMMODITY_EXPERT', 'ADMIN')
);

INSERT INTO user (username, password, role)
VALUES ('admin', '5f4dcc3b5aa765d61d8327deb882cf99', 'ADMIN');

CREATE TABLE product
(
    `code`     INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `price`    DECIMAL(8, 2) NOT NULL,
    `quantity` INT UNSIGNED DEFAULT (0)
);


CREATE TABLE product_translate
(
    `id`           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_code` INT          NOT NULL,
    `lang_id`      INT          NOT NULL,
    `name`         VARCHAR(255) NOT NULL,
    FOREIGN KEY (lang_id) REFERENCES language (id),
    FOREIGN KEY (product_code) REFERENCES product (code)
);


insert into product(code, price, quantity)
values (1, 100, 100),
       (2, 200, 200),
       (3, 300, 300),
       (4, 400, 400),
       (5, 500, 500),
       (6, 600, 600),
       (7, 700, 700),
       (8, 800, 800),
       (9, 900, 900),
       (10, 1000, 1000),
       (11, 1100, 1100),
       (12, 1200, 1200),
       (13, 1300, 600),
       (14, 1400, 1400),
       (15, 1500, 1500),
       (16, 1600, 1600),
       (17, 1700, 1700),
       (18, 1800, 1800),
       (19, 1900, 1900),
       (20, 2000, 2000);

insert into product_translate(product_code, lang_id, name)
values (1, 1, 'bed'),
       (1, 2, 'ліжко'),
       (2, 1, 'window'),
       (2, 2, 'вікно'),
       (3, 1, 'laptop'),
       (3, 2, 'ноутбук'),
       (4, 1, 'phone'),
       (4, 2, 'телефон'),
       (5, 1, 'tv'),
       (5, 2, 'телевізор'),
       (6, 1, 'pc'),
       (6, 2, 'компютер'),
       (7, 1, 'headphones'),
       (7, 2, 'наушники'),
       (8, 1, 'table'),
       (8, 2, 'стіл'),
       (9, 1, 'house'),
       (9, 2, 'будинок'),
       (10, 1, 'flat'),
       (10, 2, 'квартира'),
       (11, 1, 'mouse'),
       (11, 2, 'мишка'),
       (12, 1, 'keyboard'),
       (12, 2, 'клавіатура'),
       (13, 1, 'display'),
       (13, 2, 'монітор'),
       (14, 1, 'speaker'),
       (14, 2, 'динамік'),
       (15, 1, 'door'),
       (15, 2, 'двері'),
       (16, 1, 'cat'),
       (16, 2, 'кіт'),
       (17, 1, 'dog'),
       (17, 2, 'собака'),
       (18, 1, 'brain'),
       (18, 2, 'мозок'),
       (19, 1, 'hand'),
       (19, 2, 'рука'),
       (20, 1, 'leg'),
       (20, 2, 'нога');


CREATE TABLE invoice
(
    `id`          INT                                NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    `total_price` DECIMAL(8, 2),
    `user_id`     INT                                NOT NULL,
    `created_at`  TIMESTAMP                                   DEFAULT NOW(),
    `status`      ENUM ('NEW', 'CLOSED', 'DECLINED') NOT NULL DEFAULT ('NEW'),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE invoice_has_product
(
    `invoice_id`          INT NOT NULL,
    `product_code`        INT NOT NULL,
    `quantity_in_invoice` INT DEFAULT (1),
    `price`               DECIMAL(8, 2),
    FOREIGN KEY (invoice_id) REFERENCES invoice (id) ON DELETE CASCADE,
    FOREIGN KEY (product_code) REFERENCES product (code),
    UNIQUE (invoice_id, product_code)
);

INSERT INTO user (username, password, role)
VALUES ('senior1', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
       ('expert1', '5f4dcc3b5aa765d61d8327deb882cf99', 'COMMODITY_EXPERT'),
       ('cashier1', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
       ('Alex', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
       ('Bob', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
       ('Michael', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
       ('Sara', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
       ('Katy', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
       ('Jimmy', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
       ('Andrew', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
       ('Kobe', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
       ('Leslie', '5f4dcc3b5aa765d61d8327deb882cf99', 'COMMODITY_EXPERT'),
       ('Ann', '5f4dcc3b5aa765d61d8327deb882cf99', 'COMMODITY_EXPERT'),
       ('Philip J.Fry', '5f4dcc3b5aa765d61d8327deb882cf99', 'COMMODITY_EXPERT'),
       ('Eddie', '5f4dcc3b5aa765d61d8327deb882cf99', 'COMMODITY_EXPERT');

INSERT INTO invoice (user_id)
VALUES (6),
       (6),
       (6),
       (7),
       (7),
       (7),
       (6),
       (6),
       (6),
       (7);

INSERT INTO invoice_has_product (invoice_id, product_code, quantity_in_invoice, price)
VALUES (1, 1, 2, 4400),
       (1, 2, 3, 300),
       (1, 3, 1, 5000),
       (2, 4, 4, 2400),
       (2, 5, 1, 300),
       (3, 6, 10, 500),
       (4, 20, 3, 1200),
       (4, 1, 3, 6750),
       (4, 19, 2, 1600),
       (4, 10, 5, 12000),
       (4, 6, 8, 400),
       (5, 11, 4, 18000),
       (5, 3, 8, 40000),
       (5, 16, 20, 12000),
       (5, 15, 20, 18000),
       (6, 12, 3, 1200),
       (6, 13, 3, 12000),
       (6, 14, 2, 400),
       (6, 15, 5, 4500),
       (6, 16, 8, 4800),
       (6, 17, 3, 1500),
       (7, 18, 10, 3000),
       (7, 19, 20, 14000),
       (8, 11, 30, 27000),
       (8, 2, 30, 33000),
       (8, 3, 20, 10000),
       (8, 14, 50, 20000),
       (9, 9, 30, 39000),
       (9, 8, 3, 1500),
       (9, 7, 2, 1400),
       (10, 19, 3, 2100);

update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 1),
    status      = 'CLOSED'
where invoice.id = 1;
update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 2),
    status      = 'CLOSED'
where invoice.id = 2;
update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 3),
    status      = 'CLOSED'
where invoice.id = 3;
update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 4),
    status      = 'CLOSED'
where invoice.id = 4;
update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 5),
    status      = 'CLOSED'
where invoice.id = 5;
update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 6),
    status      = 'CLOSED'
where invoice.id = 6;
update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 7),
    status      = 'CLOSED'
where invoice.id = 7;
update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 8),
    status      = 'CLOSED'
where invoice.id = 8;
update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 9),
    status      = 'CLOSED'
where invoice.id = 9;
update invoice
set total_price = (select sum(price) from invoice_HAS_product where invoice_id = 10),
    status      = 'CLOSED'
where invoice.id = 10;