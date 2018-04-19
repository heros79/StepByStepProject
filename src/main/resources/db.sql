/* Create Users table */
CREATE TABLE users (
  id INT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(100) NOT NULL UNIQUE,
  passhash VARCHAR(100) NOT NULL,
  firstname VARCHAR(100) NOT NULL,
  lastname VARCHAR(100) NOT NULL,
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

INSERT INTO prodcategory (categoryname) VALUE ('household');

/* Cretate ProductType table */
CREATE TABLE prodtype (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_id INT NOT NULL,
  typename VARCHAR(100) UNIQUE,

  FOREIGN KEY (category_id) REFERENCES prodcategory (id)
)
  ENGINE = InnoDB;

INSERT INTO prodtype VALUE ((SELECT id FROM prodcategory WHERE categoryname = 'household'), 'soap');

/* Create ProductBrand table */
CREATE TABLE prodbrand (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  brandname VARCHAR(100) NOT NULL UNIQUE
)
  ENGINE = InnoDB;

INSERT INTO prodbrand (brandname) VALUE ('IKEA');

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