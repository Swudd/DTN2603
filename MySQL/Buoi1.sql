CREATE DATABASE Testing_System;

USE Testing_System;

CREATE TABLE department(
	department_id 		INT AUTO_INCREMENT PRIMARY KEY,
    department_name 	VARCHAR(100) NOT NULL
);

CREATE TABLE `position` (
	position_id 	INT AUTO_INCREMENT PRIMARY KEY,
    position_name 	ENUM('DEV', 'TEST', 'SCRUM_MASTER', 'PM') NOT NULL
);

CREATE TABLE `account` (
	account_id 		INT AUTO_INCREMENT PRIMARY KEY,
    email 			VARCHAR(100) UNIQUE NOT NULL,
    username 		VARCHAR(100) UNIQUE NOT NULL,
    full_name 		VARCHAR(100) NOT NULL,
    department_id 	INT,
    position_id 	INT,
    create_date 	DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_acount_department FOREIGN KEY (department_id) REFERENCES department(department_id),
    CONSTRAINT fk_account_position FOREIGN KEY (position_id) REFERENCES `position`(position_id)
);
    
CREATE TABLE `group` (
	group_id 		INT AUTO_INCREMENT PRIMARY KEY,
    group_name 		VARCHAR(100) UNIQUE NOT NULL,
    creator_id 		INT NOT NULL,
    create_date 	DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_group_account FOREIGN KEY (creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE group_account (
	group_id 	INT NOT NULL,
    account_id 	INT NOT NULL,
    join_date 	DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_group_account_group FOREIGN KEY (group_id) REFERENCES `group`(group_id),
    CONSTRAINT fk_group_account_account FOREIGN KEY (account_id) REFERENCES `account`(account_id)
);

CREATE TABLE type_question (
	type_id 	INT AUTO_INCREMENT PRIMARY KEY,
    type_name 	VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE category_question (
	category_id 	INT AUTO_INCREMENT PRIMARY KEY,
    category_name 	ENUM('JAVA', '.NET', 'SQL', 'POSTMAN', 'RUBY') NOT NULL
);

CREATE TABLE question (
	question_id 	INT AUTO_INCREMENT PRIMARY KEY,
    content 		VARCHAR(500) NOT NULL,
    category_id 	INT NOT NULL,
    type_id 		INT NOT NULL,
    creator_id 		INT NOT NULL,
    create_date 	DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_question_category FOREIGN KEY (category_id) REFERENCES category_question(category_id),
    CONSTRAINT fk_question_type FOREIGN KEY (type_id) REFERENCES type_question(type_id),
    CONSTRAINT fk_question_creator FOREIGN KEY (creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE answer (
	answer_id 	INT AUTO_INCREMENT PRIMARY KEY,
    content 	VARCHAR(500) NOT NULL,
    question_id INT NOT NULL,
    is_correct 	ENUM('WRONG', 'CORRECT') NOT NULL,
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);

CREATE TABLE exam (
	exam_id 	INT AUTO_INCREMENT PRIMARY KEY,
    `code` 		TINYINT NOT NULL,
    title 		VARCHAR(100) NOT NULL,
    category_id INT NOT NULL,
    duration 	TIME,
    creator_id 	INT NOT NULL,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_exam_category FOREIGN KEY (category_id) REFERENCES category_question(category_id),
    CONSTRAINT fk_exam_creator FOREIGN KEY (creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE exam_question (
	exam_id 	INT NOT NULL,
    question_id INT NOT NULL,
    CONSTRAINT fk_exam_question_exam FOREIGN KEY (exam_id) REFERENCES exam(exam_id),
    CONSTRAINT fk_exam_question_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);
