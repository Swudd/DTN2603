--  Question 1: Thêm ít nhất 10 record vào mỗi table
USE Testing_System;
INSERT INTO department (department_name) VALUES
('Marketing'),
('Finance'),
('Sales'),
('DevOps'),
('Data Engineering'),
('Customer Support'),
('Legal'),
('R&D'),
('UI/UX Design'),
('Security');
INSERT INTO `position` (position_name) VALUES
('TEST'),
('PM'),
('DEV'),
('SCRUM_MASTER'),
('TEST'),
('DEV'),
('PM'),
('DEV'),
('TEST'),
('SCRUM_MASTER');
INSERT INTO `account` (email, username, full_name, department_id, position_id) VALUES
('phuc.vo@company.com', 'phucvo', 'Vo Van Phuc', 5, 5),
('giang.do@company.com', 'giangdo', 'Do Thi Giang', 6, 6),
('hai.bui@company.com', 'haibui', 'Bui Van Hai', 7, 7),
('iris.dang@company.com', 'irisdang', 'Dang Thi Iris', 8, 8),
('khoa.ly', 'khoaly', 'Ly Trong Khoa', 9, 9),
('lan.mai@company.com', 'lanmai', 'Mai Thi Lan', 10, 10),
('minh.duong@company.com', 'minhduong', 'Duong Van Minh', 1, 1),
('nga.truong@company.com', 'ngatruong', 'Truong Thi Nga', 2, 2),
('oanh.vu@company.com', 'oanhvu', 'Vu Thi Oanh', 3, 3),
('phong.ho@company.com', 'phongho', 'Ho Van Phong', 4, 4);
INSERT INTO `group` (group_name, creator_id) VALUES
('.NET Core Team', 5),
('Postman API Testers', 6),
('Ruby Developers', 7),
('DevOps Engineers', 8),
('Data Team', 9),
('UI/UX Designers', 10),
('Security Team', 6),
('Frontend Guild', 7),
('Backend Guild', 8),
('Mobile Team', 9);
INSERT INTO group_account (group_id, account_id) VALUES
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 6),
(12, 7),
(13, 8),
(14, 9),
(15, 10);
INSERT INTO type_question (type_name) VALUES
('Matching'),
('Ordering'),
('Coding Exercise'),
('Case Study'),
('Short Answer'),
('Drag and Drop'),
('Hotspot'),
('Scenario Based'),
('Debugging'),
('Practical Test');

INSERT INTO question (content, category_id, type_id, creator_id) VALUES
('What is polymorphism in Java?', 1, 1, 5),
('Explain dependency injection in .NET.', 2, 2, 6),
('Write a query to find duplicate emails in a table.', 3, 5, 7),
('How do you send a POST request with a JSON body in Postman?', 4, 4, 8),
('What is a Ruby module and how is it different from a class?', 5, 2, 9),
('What is the difference between abstract class and interface in Java?', 1, 2, 10),
('Explain the MVC pattern in .NET.', 2, 1, 5),
('Write a SQL query using JOIN to combine two tables.', 3, 5, 6),
('How do you use environment variables in Postman?', 4, 1, 7),
('What is the purpose of "yield" in Ruby?', 5, 2, 8);


INSERT INTO answer (content, question_id, is_correct) VALUES
('Ability of an object to take many forms', 6, 'CORRECT'),
('Ability to inherit only one class', 6, 'WRONG'),
('A design pattern to inject dependencies at runtime', 7, 'CORRECT'),
('A way to hide dependencies', 7, 'WRONG'),
('SELECT email, COUNT(*) FROM users GROUP BY email HAVING COUNT(*) > 1;', 8, 'CORRECT'),
('Set method to POST and add JSON in Body tab', 9, 'CORRECT'),
('A module cannot be instantiated, a class can', 10, 'CORRECT'),
('Abstract class allows method implementation, interface does not (before Java 8)', 11, 'CORRECT'),
('MVC separates Model, View and Controller', 12, 'CORRECT'),
('Yield transfers control to the block passed to a method', 15, 'CORRECT');

INSERT INTO exam (`code`, title, category_id, duration, creator_id) VALUES
(6, 'Advanced Java Exam', 1, '01:15:00', 5),
(7, 'C# Advanced Test', 2, '01:00:00', 6),
(8, 'SQL Advanced Query Test', 3, '01:30:00', 7),
(9, 'Postman Automation Test', 4, '00:40:00', 8),
(10, 'Ruby Advanced Exam', 5, '01:00:00', 9),
(11, 'Java OOP Concepts', 1, '00:50:00', 10),
(12, '.NET MVC Exam', 2, '01:00:00', 5),
(13, 'SQL Joins & Subqueries', 3, '01:20:00', 6),
(14, 'Postman Environment Setup', 4, '00:30:00', 7),
(15, 'Ruby Modules & Classes', 5, '00:45:00', 8);
INSERT INTO exam_question (exam_id, question_id) VALUES
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15);

-- Question 2: lấy ra tất cả các phòng ban

select *
from department;

-- Question 3: lấy ra id của phòng ban "Sale"

select department_id
from department
where department_name like "Sales";

-- Question 4: lấy ra thông tin account có full name dài nhất

SELECT account_id, email, username, full_name, LENGTH(full_name) AS name_length
FROM `account`
ORDER BY LENGTH(full_name) DESC
LIMIT 1;

-- Question 5: Lấy ra thông tin account có full name dài nhất và thuộc phòng ban có id= 3

SELECT account_id, email, username, full_name, department_id, LENGTH(full_name) AS name_length
FROM `account`
where department_id = 3
ORDER BY LENGTH(full_name) DESC
LIMIT 1;

-- Question 6: Lấy ra tên group đã tham gia trước ngày 20/12/2019

select group_name, create_date 
from `group`
where create_date < "2019-12-22";

-- Question 7: Lấy ra ID của question có >= 4 câu trả lời

select question_id, count(1) as count
from answer
group by question_id
having count(1) >= 4;

-- Question 8: Lấy ra các mã đề thi có thời gian thi >= 60 phút và được tạo trước ngày 20/12/2019

select `code`, duration, create_date
from exam
where duration > "00:60:00" and create_date < "2019-12-20";

-- Question 9: Lấy ra 5 group được tạo gần đây nhất

select *
from `group`
order by create_date desc
limit 5;

-- Question 10: Đếm số nhân viên thuộc department có id = 2

select count(1) as count
from `account`
where department_id =2;

-- Question 11: Lấy ra nhân viên có tên bắt đầu bằng chữ "D" và kết thúc bằng chữ "o"

select *
from `account`
where full_name like "D%o";

