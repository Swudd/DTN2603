CREATE DATABASE Testing_System;

USE Testing_System;

CREATE TABLE department(
	department_id 		INT AUTO_INCREMENT PRIMARY KEY,
    department_name 	VARCHAR(100)
);

CREATE TABLE `position` (
	position_id 	INT AUTO_INCREMENT PRIMARY KEY,
    position_name 	ENUM('DEV', 'TEST', 'SCRUM_MASTER', 'PM')
);

CREATE TABLE `account` (
	account_id 		INT AUTO_INCREMENT PRIMARY KEY,
    email 			VARCHAR(100) UNIQUE NOT NULL,
    username 		VARCHAR(100) UNIQUE NOT NULL,
    full_name 		VARCHAR(100),
    department_id 	INT,
    position_id 	INT,
    create_date 	DATETIME,
    CONSTRAINT fk_acount_department FOREIGN KEY(department_id) REFERENCES department(department_id),
    CONSTRAINT fk_account_position FOREIGN KEY(position_id) REFERENCES `position`(position_id)
);
    
