SET GLOBAL TIME_ZONE = "+3:00";
DROP DATABASE IF EXISTS `servlet_db`;
CREATE DATABASE `servlet_db`;
USE `servlet_db`;

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
    `code`     INT           NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
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


INSERT INTO product (code, price, quantity)
VALUES (1, 100, 100),
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
       (13, 1300, 1300),
       (14, 1400, 1400),
       (15, 1500, 1500),
       (16, 1600, 1600),
       (17, 1700, 1700),
       (18, 1800, 1800),
       (19, 1900, 1900),
       (20, 2000, 2000),
       (21, 2100, 2100),
       (22, 2200, 2200);

INSERT INTO product_translate(product_code, lang_id, name)
VALUES (1, 1, 'product1'),
       (2, 1, 'product2'),
       (3, 1, 'product3'),
       (4, 1, 'product4'),
       (5, 1, 'product5'),
       (6, 1, 'product6'),
       (7, 1, 'product7'),
       (8, 1, 'product8'),
       (9, 1, 'product9'),
       (10, 1, 'product10'),
       (12, 1, 'product12'),
       (13, 1, 'product13'),
       (14, 1, 'product14'),
       (15, 1, 'product15'),
       (16, 1, 'product16'),
       (17, 1, 'product17'),
       (18, 1, 'product18'),
       (19, 1, 'product19'),
       (20, 1, 'product20'),
       (21, 1, 'product21'),
       (22, 1, 'product22'),
       (1, 2, 'продукт1'),
       (2, 2, 'продукт2'),
       (3, 2, 'продукт3'),
       (4, 2, 'продукт4'),
       (5, 2, 'продукт5'),
       (6, 2, 'продукт6'),
       (7, 2, 'продукт7'),
       (8, 2, 'продукт8'),
       (9, 2, 'продукт9'),
       (10, 2, 'продукт10'),
       (11, 2, 'продукт11'),
       (12, 2, 'продукт12'),
       (13, 2, 'продукт13'),
       (14, 2, 'продукт14'),
       (15, 2, 'продукт15'),
       (16, 2, 'продукт16'),
       (17, 2, 'продукт17'),
       (18, 2, 'продукт18'),
       (19, 2, 'продукт19'),
       (20, 2, 'продукт20'),
       (21, 2, 'продукт21'),
       (22, 2, 'продукт22');


CREATE TABLE invoice
(
    `id`          INT                                NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    `total_price` DECIMAL(8, 2)                               DEFAULT (0),
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


INSERT INTO invoice(user_id)
VALUES (8),
       (5),
       (6),
       (7),
       (8),
       (7);

INSERT INTO invoice_has_product(invoice_id, product_code, quantity_in_invoice, price)
VALUES (1, 1, 1, 100),
       (1, 2, 2, 400),
       (2, 3, 1, 300),
       (3, 4, 3, 1200),
       (4, 5, 5, 2500),
       (4, 6, 1, 600),
       (4, 7, 1, 700),
       (4, 8, 2, 1600),
       (5, 9, 11, 9900),
       (5, 10, 1, 1000),
       (5, 11, 10, 11000),
       (6, 12, 5, 60000),
       (6, 13, 2, 2600),
       (6, 14, 1, 1400);

UPDATE invoice
SET total_price = (SELECT SUM(price) FROM invoice_has_product WHERE invoice_id = 1),
    status      = 'CLOSED'
WHERE invoice.id = 1;
UPDATE invoice
SET total_price = (SELECT SUM(price) FROM invoice_has_product WHERE invoice_id = 2),
    status      = 'CLOSED'
WHERE invoice.id = 2;
UPDATE invoice
SET total_price = (SELECT SUM(price) FROM invoice_has_product WHERE invoice_id = 3),
    status      = 'CLOSED'
WHERE invoice.id = 3;
UPDATE invoice
SET total_price = (SELECT SUM(price) FROM invoice_has_product WHERE invoice_id = 4),
    status      = 'CLOSED'
WHERE invoice.id = 4;
UPDATE invoice
SET total_price = (SELECT SUM(price) FROM invoice_has_product WHERE invoice_id = 5),
    status      = 'CLOSED'
WHERE invoice.id = 5;
UPDATE invoice
SET total_price = (SELECT SUM(price) FROM invoice_has_product WHERE invoice_id = 6),
    status      = 'CLOSED'
WHERE invoice.id = 6;