


-- FOR TESTING PURPOSES ------------------------------
CREATE DATABASE hotel;
USE hotel;

CREATE TABLE user (
  id_user integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email varchar(30) NOT NULL,
  password varchar(30) NOT NULL
);
--------------------------------------------------------