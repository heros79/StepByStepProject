/* Create Users table */
CREATE TABLE users (
  id INT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(100) NOT NULL UNIQUE,
  passhash VARCHAR(100) NOT NULL,
  firstname VARCHAR(100) NOT NULL,
  lastname VARCHAR(100) NOT NULL,
  money DOUBLE,
  email VARCHAR(100) NOT NULL UNIQUE,
  role INT NOT NULL
)
  ENGINE = InnoDB;

/* Create ProductCategory table */
CREATE TABLE prodcategory (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  categoryname VARCHAR(100) NOT NULL UNIQUE
)
  ENGINE = InnoDB;

/* Cretate ProductType table */
CREATE TABLE prodtype (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_id INT NOT NULL,
  typename VARCHAR(100) UNIQUE,

  FOREIGN KEY (category_id) REFERENCES prodcategory (id)
)
  ENGINE = InnoDB;

/* Create ProductBrand table */
CREATE TABLE prodbrand (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  brandname VARCHAR(100) NOT NULL UNIQUE
)
  ENGINE = InnoDB;

/* Create Products table */
CREATE TABLE products (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  category_id INT NOT NULL ,
  type_id INT NOT NULL ,
  brand_id INT NOT NULL ,
  vendorcode VARCHAR(50) NOT NULL UNIQUE ,
  productname VARCHAR(100) NOT NULL ,
  price DOUBLE NOT NULL ,
  description TEXT,
  filepath VARCHAR(255) NOT NULL ,

  FOREIGN KEY (category_id) REFERENCES prodcategory (id),
  FOREIGN KEY (type_id) REFERENCES prodtype (id),
  FOREIGN KEY (brand_id) REFERENCES prodbrand (id)
)
  ENGINE = InnoDB;

/* Create Product buy and sell action with date and count */
CREATE TABLE productbuysell (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  product_id INT NOT NULL,
  count INT NOT NULL,
  buydate DATE,
  selldate DATE,
  FOREIGN KEY (product_id) REFERENCES products (id)
)
  ENGINE = InnoDB;

/* Create View Products by count sum */
CREATE VIEW totalproducts AS
  SELECT
    productname AS productname,
    vendorcode AS vendorcode,
    product_id AS product_id,
    SUM(`count`) AS `count`
  FROM products
    JOIN productbuysell
  WHERE (productbuysell.product_id = products.id)
  GROUP BY product_id;