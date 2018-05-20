SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE security_database;

INSERT INTO security_database.role (role_id, role) VALUES (1, 'ADMIN');
INSERT INTO security_database.user (user_id, username, password, first_name, last_name, active) VALUES (1, 'CDVDIS', '$2a$10$W7J0/5znKRW9MCIg5pf.Ku3zPN8v.TjEkTex3HBwNngrrqGu/8Oka', 'DIMITRIOS', 'STEFOS', 1);
INSERT INTO security_database.user_role (user_id, role_id) VALUES (1, 1);