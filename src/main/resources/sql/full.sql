START TRANSACTION;

DROP TABLE IF EXISTS customers_prod;
DROP TABLE IF EXISTS customer_repo;
DROP TABLE IF EXISTS product_repo;

CREATE TABLE product_repo (`id` INT NOT NULL AUTO_INCREMENT, `pname` VARCHAR(45) NOT NULL, `cost` INT NOT NULL, PRIMARY KEY (`id`));
INSERT INTO product_repo (`id`, `pname`, `cost`) VALUES
('1', 'Apple', '100'),
('2', 'Grape', '150'),
('3', 'Orange', '200'),
('4', 'Banana', '50'),
('5', 'Lemon', '40'),
('6', 'Pineapple', '120'),
('7', 'Carrot', '15'),
('8', 'Potato', '20'),
('9', 'Pear', '60'),
('10', 'Kiwi', '75');

CREATE TABLE customer_repo (`id` INT NOT NULL AUTO_INCREMENT, `cname` VARCHAR(45) NOT NULL, PRIMARY KEY (`id`));
INSERT INTO customer_repo (`id`, `cname`) VALUES
('1','Andew'),
('2','Bob'),
('3','Mike'),
('4','Sam'),
('5','Frodo');

CREATE TABLE customers_prod (`customers_id` INT NOT NULL,`products_id` INT NOT NULL,
INDEX `cid_idx` (`customers_id` ASC) VISIBLE,INDEX `pid_idx` (`products_id` ASC) VISIBLE,
FOREIGN KEY (`customers_id`)
REFERENCES `hiber`.`customer_repo` (`id`),
FOREIGN KEY (`products_id`)
REFERENCES `hiber`.`product_repo` (`id`));

INSERT INTO customers_prod (`customers_id`, `products_id`) VALUES
('1','1'),('1','3'),('1','10'),('1','7'),('1','5'),
('2','4'),('2','8'),('2','5'),
('3','1'),
('4','7'),('4','3'),('4','5'),('4','2'),
('5','6'),('5','9');

COMMIT;