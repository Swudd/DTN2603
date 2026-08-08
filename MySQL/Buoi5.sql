Use testing_system;

-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale

drop view if exists sale_account;
CREATE VIEW sale_account AS
    SELECT a.*, d.department_name
    FROM `account` a
    JOIN department d ON a.department_id = d.department_id
    WHERE d.department_name = 'Sales';

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất

drop view if exists view_account_most_group;

CREATE VIEW view_account_most_group as
WITH account_group_count AS (
	SELECT account_id, count(group_id) as count
    FROM group_account
    GROUP BY account_id),
max_count AS (
	SELECT *
    FROM account_group_count
    ORDER BY count DESC
    LIMIT 1 )
SELECT a.account_id, a.email, a.username, a.full_name, a.department_id, a.position_id, agc.count
FROM `account` a
JOIN account_group_count agc ON a.account_id = agc.account_id
JOIN max_count mc ON agc.count = mc.count;

-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ được coi là quá dài) và xóa nó đi

DROP VIEW IF EXISTS view_long_questions;
CREATE VIEW view_long_questions AS
SELECT q.question_id, q.content, q.category_id, q.type_id, q.creator_id, q.create_date, LENGTH(q.content) AS word_count
FROM question q
WHERE LENGTH(q.content) > 300;

DELETE ans FROM answer ans
WHERE ans.question_id IN (SELECT question_id FROM view_long_questions);

DELETE eq FROM exam_question eq
WHERE eq.question_id IN (SELECT question_id FROM view_long_questions);

DELETE FROM question
WHERE LENGTH(content) > 300;

-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất

DROP VIEW IF EXISTS view_department_most_employees;
CREATE VIEW view_department_most_employees AS
WITH dept_employee_count AS (
    SELECT department_id, COUNT(account_id) AS count
    FROM `account`
    GROUP BY department_id
),
max_count AS (
    SELECT MAX(count) AS max_employee_count
    FROM dept_employee_count
)
SELECT d.department_id, d.department_name, dc.count
FROM department d
JOIN dept_employee_count dc ON d.department_id = dc.department_id
JOIN max_count mc ON dc.count = mc.max_employee_count;

-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo.

DROP VIEW IF EXISTS view_questions_by_nguyen;
CREATE VIEW view_questions_by_nguyen AS
SELECT q.question_id, q.content, q.category_id, q.type_id, q.creator_id, a.full_name AS creator_name, a.email AS creator_email, q.create_date
FROM question q
JOIN `account` a ON q.creator_id = a.account_id
WHERE a.full_name LIKE 'Nguyễn%';
