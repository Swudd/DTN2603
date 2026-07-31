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
    CONSTRAINT fk_acount_department FOREIGN KEY (department_id) REFERENCES department(department_id),
    CONSTRAINT fk_account_position FOREIGN KEY (position_id) REFERENCES `position`(position_id)
);
    
CREATE TABLE `group` (
	group_id 		INT AUTO_INCREMENT PRIMARY KEY,
    group_name 		VARCHAR(100),
    creator_id 		INT,
    create_date 	DATETIME,
    CONSTRAINT fk_group_account FOREIGN KEY (creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE group_account (
	group_id 	INT,
    account_id 	INT,
    join_date 	DATETIME,
    CONSTRAINT fk_group_account_group FOREIGN KEY (group_id) REFERENCES `group`(group_id),
    CONSTRAINT fk_group_account_account FOREIGN KEY (account_id) REFERENCES `account`(account_id)
);

CREATE TABLE type_question (
	type_id 	INT AUTO_INCREMENT PRIMARY KEY,
    type_name 	VARCHAR(100)
);

CREATE TABLE category_question (
	category_id 	INT AUTO_INCREMENT PRIMARY KEY,
    category_name 	ENUM('JAVA', '.NET', 'SQL', 'POSTMAN', 'RUBY')
);

CREATE TABLE question (
	question_id 	INT AUTO_INCREMENT PRIMARY KEY,
    content 		VARCHAR(500),
    category_id 	INT,
    type_id 		INT,
    creator_id 		INT,
    create_date 	DATETIME,
    CONSTRAINT fk_question_category FOREIGN KEY (category_id) REFERENCES category_question(category_id),
    CONSTRAINT fk_question_type FOREIGN KEY (type_id) REFERENCES type_question(type_id),
    CONSTRAINT fk_question_creator FOREIGN KEY (creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE answer (
	answer_id 	INT AUTO_INCREMENT PRIMARY KEY,
    content 	VARCHAR(500),
    question_id INT,
    is_correct 	ENUM('WRONG', 'CORRECT'),
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);

CREATE TABLE exam (
	exam_id 	INT AUTO_INCREMENT PRIMARY KEY,
    `code` 		TINYINT,
    title 		VARCHAR(100),
    category_id INT,
    duration 	TIME,
    creator_id 	INT,
    create_date DATETIME,
    CONSTRAINT fk_exam_category FOREIGN KEY (category_id) REFERENCES category_question(category_id),
    CONSTRAINT fk_exam_creator FOREIGN KEY (creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE exam_question (
	exam_id 	INT,
    question_id INT,
    CONSTRAINT fk_exam_question_exam FOREIGN KEY (exam_id) REFERENCES exam(exam_id),
    CONSTRAINT fk_exam_question_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);
