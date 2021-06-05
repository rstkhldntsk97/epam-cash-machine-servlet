SET GLOBAL TIME_ZONE = "+3:00";
DROP DATABASE IF EXISTS `servlet_db_test`;
CREATE DATABASE `servlet_db_test`;
USE `servlet_db_test`;

CREATE TABLE language
(
    `id`   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `lang` VARCHAR(2)
);


CREATE TABLE user
(
    `id`       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(12)  NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `role`     ENUM ('CASHIER', 'SENIOR_CASHIER', 'COMMODITY_EXPERT', 'ADMIN')
);


CREATE TABLE product
(
    `code`     INT           NOT NULL UNIQUE PRIMARY KEY,
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


INSERT INTO language (lang)
VALUES ('EN'),
       ('UA');

INSERT INTO user (username, password, role)
VALUES ('admin', '5f4dcc3b5aa765d61d8327deb882cf99', 'ADMIN'),
       ('senior1', '5f4dcc3b5aa765d61d8327deb882cf99', 'SENIOR_CASHIER'),
       ('expert1', '5f4dcc3b5aa765d61d8327deb882cf99', 'COMMODITY_EXPERT'),
       ('cashier1', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER'),
       ('cashier', '5f4dcc3b5aa765d61d8327deb882cf99', 'CASHIER');


INSERT INTO product (code, price, quantity)
VALUES (1, 100, 1000),
       (2, 200, 2000),
       (3, 300, 3000);

INSERT INTO product_translate(product_code, lang_id, name)
VALUES (1, 1, 'bed'),
       (1, 2, 'ліжко'),
       (2, 1, 'window'),
       (2, 2, 'вікно'),
       (3, 1, 'laptop'),
       (3, 2, 'ноутбук');

INSERT INTO invoice (user_id)
VALUES (4),
       (5),
       (4);

INSERT INTO invoice_has_product (invoice_id, product_code, quantity_in_invoice, price)
VALUES (1, 1, 10, 100), (1, 2, 10, 200),
       (2, 2, 10, 200),
       (3, 3, 10, 300);