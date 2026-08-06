USE testing_system;
-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ

Select *
from account acc
left join department de on acc.department_id = de.department_id;

-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010

select account_id, email, username, full_name, department_name, position_name, create_date
from account acc
left join department de on acc.department_id = de.department_id
left join position po on acc.position_id = po.position_id
where create_date > "2010-12-20" ;

-- Question 3: Viết lệnh để lấy ra tất cả các developer

select account_id, email, username, full_name, position_name, create_date
from account acc
left join position po on acc.position_id = po.position_id
where position_name = "DEV";

-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên

select de.department_id, department_name ,count(username) as count
from department de
left join account acc on de.department_id = acc.department_id
group by de.department_id, department_name
having count(username) > 3;

-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất

-- lay count nhieu nhat
select count(1) as count
from exam_question
group by question_id
order by count desc
limit 1;

select qe.question_id, qe.content, count(1) as count
from exam_question eq
join question qe on eq.question_id = qe.question_id
group by qe.question_id, qe.content
having count = (select count(1) as count
		from exam_question
		group by question_id
		order by count desc
		limit 1);

-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question
        
select c.category_id, c.category_name, count(q.question_id) as count
from category_question c
left join question q on c.category_id = q.category_id
group by c.category_id, c.category_name;

-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam

select q.question_id, q.content, count(exam_id) as count
from question q
left join exam_question e on q.question_id = e.question_id
group by q.question_id, q.content;

-- Question 8: Lấy ra Question có nhiều câu trả lời nhất

select count(answer_id) as count
from question q
left join answer a on q.question_id = a.question_id
group by q.question_id
order by count desc
limit 1;

select q.question_id, q.content, count(answer_id) as count
from question q
left join answer a on q.question_id = a.question_id
group by q.question_id, q.content
having count = (select count(answer_id) as count
				from question q2
				left join answer a2 on q2.question_id = a2.question_id
				group by q2.question_id
				order by count desc
				limit 1);
                
-- Question 9: Thống kê số lượng account trong mỗi group

select g.group_id, g.group_name, count(ga.account_id) as count
from `group` g
left join group_account ga on g.group_id = ga.group_id
group by g.group_id, g.group_name;

-- Question 10: Tìm chức vụ có ít người nhất

SELECT p.position_id, p.position_name, COUNT(a.account_id) AS count
FROM `position` p
LEFT JOIN `account` a ON p.position_id = a.position_id
GROUP BY p.position_id, p.position_name
HAVING count = (SELECT COUNT(a2.account_id) AS count
				FROM `position` p2
				LEFT JOIN `account` a2 ON p2.position_id = a2.position_id
				GROUP BY p2.position_id
				ORDER BY count ASC
				LIMIT 1);
                
-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM

SELECT d.department_id,d.department_name,
    COUNT(CASE WHEN p.position_name = 'DEV' THEN 1 END) AS DEV,
    COUNT(CASE WHEN p.position_name = 'TEST' THEN 1 END) AS TEST,
    COUNT(CASE WHEN p.position_name = 'SCRUM_MASTER' THEN 1 END) AS SCRUM_MASTER,
    COUNT(CASE WHEN p.position_name = 'PM' THEN 1 END) AS PM
FROM department d
LEFT JOIN `account` a ON d.department_id = a.department_id
LEFT JOIN `position` p ON a.position_id = p.position_id
GROUP BY d.department_id, d.department_name;

-- Question 12: Lấy thông tin chi tiết của câu hỏi (thông tin cơ bản, loại câu hỏi, người tạo, câu trả lời...)

SELECT q.question_id, q.content, q.create_date, t.type_id, t.type_name, c.category_id, c.category_name, 
acc.account_id AS creator_id,  acc.full_name AS creator_name, acc.email AS creator_email,
a.answer_id, a.content AS answer_content, a.is_correct
FROM question q
JOIN type_question t ON q.type_id = t.type_id
JOIN category_question c ON q.category_id = c.category_id
JOIN `account` acc ON q.creator_id = acc.account_id
LEFT JOIN answer a ON q.question_id = a.question_id
ORDER BY q.question_id;

-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm

SELECT t.type_id, t.type_name, COUNT(q.question_id) AS count
FROM type_question t
LEFT JOIN question q ON t.type_id = q.type_id
GROUP BY t.type_id, t.type_name
ORDER BY count DESC;

-- Question 14, 15: Lấy ra group không có account nào

SELECT g.group_id, g.group_name, g.creator_id, g.create_date
FROM `group` g
LEFT JOIN group_account ga ON g.group_id = ga.group_id
WHERE ga.account_id IS NULL;

-- Question 16: Lấy ra question không có answer nào

SELECT q.question_id, q.content, q.category_id, q.type_id, q.creator_id, q.create_date
FROM question q
LEFT JOIN answer a ON q.question_id = a.question_id
WHERE a.answer_id IS NULL;

-- Question 17

-- a) Lấy các account thuộc nhóm thứ 1
SELECT a.account_id, a.email, a.username, a.full_name
FROM `account` a
JOIN group_account ga ON a.account_id = ga.account_id
WHERE ga.group_id = 1;

-- b) Lấy các account thuộc nhóm thứ 2
SELECT a.account_id, a.email, a.username, a.full_name
FROM `account` a
JOIN group_account ga ON a.account_id = ga.account_id
WHERE ga.group_id = 2;

-- c) Ghép 2 kết quả từ câu a) và câu b) sao cho không có record nào trùng nhau
SELECT a.account_id, a.email, a.username, a.full_name
FROM `account` a
JOIN group_account ga ON a.account_id = ga.account_id
WHERE ga.group_id = 1

UNION

SELECT a.account_id, a.email, a.username, a.full_name
FROM `account` a
JOIN group_account ga ON a.account_id = ga.account_id
WHERE ga.group_id = 2;

-- Question 18

-- a) Lấy các group có lớn hơn 5 thành viên
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS count
FROM `group` g
JOIN group_account ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(ga.account_id) > 5;

-- b) Lấy các group có nhỏ hơn 7 thành viên
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS count
FROM `group` g
JOIN group_account ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(ga.account_id) < 7;

-- c) Ghép 2 kết quả từ câu a) và câu b)
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS count
FROM `group` g
JOIN group_account ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(ga.account_id) > 5

UNION

SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS count
FROM `group` g
JOIN group_account ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(ga.account_id) < 7;