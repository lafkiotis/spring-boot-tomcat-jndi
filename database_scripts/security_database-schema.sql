SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS security_database;
CREATE SCHEMA security_database;
USE security_database;

CREATE TABLE role
(
    role_id INT(11) PRIMARY KEY NOT NULL,
    role VARCHAR(200) NOT NULL
);
CREATE TABLE user
(
    user_id BIGINT(10) PRIMARY KEY NOT NULL,
    username VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    active INT(11) NOT NULL
);
CREATE TABLE user_role
(
    user_id INT(11) NOT NULL,
    role_id INT(11) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, role_id)
);