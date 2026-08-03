use testing_system;

INSERT INTO department (department_name) VALUES
('Software Development'),
('Quality Assurance'),
('Human Resources'),
('Business Analysis'),
('IT Support');

INSERT INTO `position` (position_name) VALUES
('DEV'),
('TEST'),
('SCRUM_MASTER'),
('PM'),
('DEV');

INSERT INTO `account` (email, username, full_name, department_id, position_id) VALUES
('an.nguyen@company.com', 'annguyen', 'Nguyen Van An', 1, 1),
('binh.tran@company.com', 'binhtran', 'Tran Thi Binh', 2, 2),
('cuong.le@company.com', 'cuongle', 'Le Van Cuong', 1, 3),
('dung.pham@company.com', 'dungpham', 'Pham Thi Dung', 4, 4),
('em.hoang@company.com', 'emhoang', 'Hoang Van Em', 2, 2);

INSERT INTO `group` (group_name, creator_id) VALUES
('Java Backend Team', 1),
('QA Automation Team', 2),
('Scrum Masters Guild', 3),
('Project Managers', 4),
('SQL Practice Group', 1);

INSERT INTO group_account (group_id, account_id) VALUES
(1, 1),
(1, 3),
(2, 2),
(2, 5),
(3, 3),
(4, 4),
(5, 1);

INSERT INTO type_question (type_name) VALUES
('Multiple Choice'),
('Single Choice'),
('True/False'),
('Fill in the Blank'),
('Essay');

INSERT INTO category_question (category_name) VALUES
('JAVA'),
('.NET'),
('SQL'),
('POSTMAN'),
('RUBY');

INSERT INTO question (content, category_id, type_id, creator_id) VALUES
('What is the difference between JDK and JRE?', 1, 2, 1),
('Which keyword is used to inherit a class in C#?', 2, 2, 3),
('Write a SQL query to select all employees with salary > 5000.', 3, 5, 1),
('Postman supports which type of API testing?', 4, 1, 2),
('In Ruby, what does "attr_accessor" do?', 5, 2, 4);

INSERT INTO answer (content, question_id, is_correct) VALUES
('JDK includes JRE plus development tools', 1, 'CORRECT'),
('JRE includes JDK plus development tools', 1, 'WRONG'),
('extends', 2, 'CORRECT'),
('implements', 2, 'WRONG'),
('SELECT * FROM employees WHERE salary > 5000;', 3, 'CORRECT');

INSERT INTO exam (`code`, title, category_id, duration, creator_id) VALUES
(1, 'Java Fundamentals Test', 1, '01:00:00', 1),
(2, 'C# Basic Exam', 2, '00:45:00', 3),
(3, 'SQL Skill Assessment', 3, '01:30:00', 1),
(4, 'API Testing with Postman', 4, '00:30:00', 2),
(5, 'Ruby on Rails Quiz', 5, '01:00:00', 4);

INSERT INTO exam_question (exam_id, question_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(1, 3),
(3, 1);