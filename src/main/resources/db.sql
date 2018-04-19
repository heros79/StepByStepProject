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

INSERT INTO prodcategory (categoryname) VALUE ("household");