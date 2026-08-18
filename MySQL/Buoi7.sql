USE testing_system;
-- Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo
-- trước 1 năm trước

DROP trigger if exists q1;
DELIMITER $$
CREATE trigger q1
before INSERT on `group`
FOR each row
	BEGIN
		if new.create_date < date_sub(curdate(), interval 1 year) then
			signal sqlstate '12345'
            set message_text = 'Không được phép tạo group với ngày tạo trước 1 năm trở về trước';
		END IF;
    END $$
DELIMITER ;

INSERT INTO `group` (group_name, creator_id, create_date) 
VALUES ('Test Group Invalid', 1, '2020-01-01');

-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào
-- department "Sale" nữa, khi thêm thì hiện ra thông báo "Department
-- "Sale" cannot add more user"

DROP TRIGGER IF EXISTS q2_insert;
DELIMITER $$
CREATE trigger q2_insert
before INSERT on `account`
FOR each row
	BEGIN
		DECLARE v_department_name VARCHAR(100);
        
        select department_name INTO v_department_name
        from department
        where department_id = new.department_id;
        
		if v_department_name = 'Sales' THEN
        SIGNAL SQLSTATE '12345'
        set message_text = 'Department "Sale" cannot add more user';
        end if;
    end $$
DELIMITER ;

INSERT INTO `account` (email, username, full_name, department_id, position_id)
VALUES ('new.user@company.com', 'newuser', 'New User', 18, 1);

DROP TRIGGER IF EXISTS q2_update;
DELIMITER $$
CREATE trigger q2_update
before update on `account`
FOR each row
	BEGIN
		DECLARE v_department_name VARCHAR(100);
        
        select department_name INTO v_department_name
        from department
        where department_id = new.department_id;
        
		if v_department_name = 'Sales' THEN
        SIGNAL SQLSTATE '12345'
        set message_text = 'Department "Sale" cannot add more user';
        end if;
    end $$
DELIMITER ;

-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user

DROP TRIGGER IF EXISTS q3_insert;
DELIMITER $$
CREATE trigger q3_insert
BEFORE INSERT on `group_account`
FOR EACH ROW
	BEGIN
		DECLARE v_group_count int DEFAULT 0;
        
        select  count(*) into v_group_count
		from group_account
		where group_id = new.group_id;
        
        if v_group_count >=5 then
        SIGNAL SQLSTATE '12345'
        set message_text = 'Group cannot add more user';
        end if;
        
	end $$
DELIMITER ;

DROP TRIGGER IF EXISTS q3_update;
DELIMITER $$
CREATE trigger q3_update
BEFORE update on `group_account`
FOR EACH ROW
	BEGIN
		DECLARE v_group_count int DEFAULT 0;
        
        select  count(*) into v_group_count
		from group_account
		where group_id = new.group_id;
        
        if v_group_count >=5 then
        SIGNAL SQLSTATE '12345'
        set message_text = 'Group cannot add more user';
        end if;
        
	end $$
DELIMITER ;

-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question

DROP TRIGGER IF EXISTS q4_insert;
DELIMITER $$
CREATE trigger q4_insert
BEFORE INSERT on `exam_question`
FOR EACH ROW
BEGIN
    DECLARE v_question_count INT DEFAULT 0;

    SELECT COUNT(*) INTO v_question_count
    FROM exam_question
    WHERE exam_id = NEW.exam_id;

    IF v_question_count >= 10 THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Exam cannot have more than 10 questions';
    END IF;
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS q4_update;
DELIMITER $$
CREATE TRIGGER q4_update
BEFORE update ON `exam_question`
FOR EACH ROW
BEGIN
    DECLARE v_question_count INT DEFAULT 0;

    SELECT COUNT(*) INTO v_question_count
    FROM exam_question
    WHERE exam_id = NEW.exam_id;

    IF v_question_count >= 10 THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Exam cannot have more than 10 questions';
    END IF;
END$$
DELIMITER ;

-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là
-- admin@gmail.com (đây là tài khoản admin, không cho phép user xóa),
-- còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông
-- tin liên quan tới user đó

DROP TRIGGER IF EXISTS q5_delete_account;
DELIMITER $$
CREATE TRIGGER q5_delete_account
BEFORE DELETE ON `account`
FOR EACH ROW
BEGIN
    -- Chặn xóa tài khoản admin
    IF OLD.email = 'admin@gmail.com' THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Cannot delete admin account';
    END IF;

    -- Xóa các answer thuộc question do account này tạo
    DELETE FROM answer
    WHERE question_id IN (
        SELECT question_id FROM question WHERE creator_id = OLD.account_id
    );

    -- Xóa các exam_question liên quan tới question do account này tạo
    DELETE FROM exam_question
    WHERE question_id IN (
        SELECT question_id FROM question WHERE creator_id = OLD.account_id
    );

    -- Xóa các exam_question thuộc exam do account này tạo
    DELETE FROM exam_question
    WHERE exam_id IN (
        SELECT exam_id FROM exam WHERE creator_id = OLD.account_id
    );

    -- Xóa các question do account này tạo 
    DELETE FROM question
    WHERE creator_id = OLD.account_id;

    -- Xóa các exam do account này tạo 
    DELETE FROM exam
    WHERE creator_id = OLD.account_id;

    -- Xóa các group_account mà account này tham gia 
    DELETE FROM group_account
    WHERE account_id = OLD.account_id;

    -- Xóa các group_account thuộc group do account này tạo
    DELETE FROM group_account
    WHERE group_id IN (
        SELECT group_id FROM `group` WHERE creator_id = OLD.account_id
    );

    -- Xóa các group do account này tạo
    DELETE FROM `group`
    WHERE creator_id = OLD.account_id;
END$$
DELIMITER ;

-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của table
-- Account, hãy tạo trigger cho phép người dùng khi tạo account không điền
-- vào departmentID thì sẽ được phân vào phòng ban "waiting Department"

DROP TRIGGER IF EXISTS q6;
DELIMITER $$
CREATE TRIGGER q6
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    DECLARE v_waiting_dept_id INT;

    IF new.department_id IS NULL THEN

        SELECT department_id INTO v_waiting_dept_id
        FROM department
        WHERE department_name = 'Waiting Department'
        LIMIT 1;

        IF v_waiting_dept_id IS NULL THEN
            INSERT INTO department (department_name) VALUES ('Waiting Department');
            SET v_waiting_dept_id = LAST_INSERT_ID();
        END IF;

        SET NEW.department_id = v_waiting_dept_id;

    END IF;
END$$
DELIMITER ;

-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi
-- question, trong đó có tối đa 2 đáp án đúng.

DROP TRIGGER IF EXISTS q7_insert;
DELIMITER $$
CREATE TRIGGER q7_insert
BEFORE INSERT ON `answer`
FOR EACH ROW
BEGIN
    DECLARE v_total_count INT DEFAULT 0;
    DECLARE v_correct_count INT DEFAULT 0;

    SELECT COUNT(*) INTO v_total_count
    FROM answer
    WHERE question_id = NEW.question_id;

    SELECT COUNT(*) INTO v_correct_count
    FROM answer
    WHERE question_id = NEW.question_id
      AND is_correct = 'CORRECT';

    IF v_total_count >= 4 THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Question cannot have more than 4 answers';
    END IF;

    IF NEW.is_correct = 'CORRECT' AND v_correct_count >= 2 THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Question cannot have more than 2 correct answers';
    END IF;
END$$
DELIMITER ;

-- Question 8: Viết trigger sửa lại dữ liệu cho đúng:
-- Nếu người dùng nhập vào gender của account là nam, nữ, chưa xác định
-- Thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database

ALTER TABLE `account`
ADD COLUMN gender ENUM('M', 'F', 'U') DEFAULT 'U';

DROP TRIGGER IF EXISTS q8;
DELIMITER $$
CREATE TRIGGER q8
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    IF NEW.gender = 'nam' THEN
        SET NEW.gender = 'M';
    ELSEIF NEW.gender = 'nữ' THEN
        SET NEW.gender = 'F';
    ELSEIF NEW.gender = 'chưa xác định' THEN
        SET NEW.gender = 'U';
    END IF;
END$$
DELIMITER ;

-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày

DROP TRIGGER IF EXISTS q9;
DELIMITER $$
CREATE TRIGGER q9
BEFORE DELETE ON `exam`
FOR EACH ROW
BEGIN
    IF OLD.create_date > DATE_SUB(NOW(), INTERVAL 2 DAY) THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Cannot delete exam that was created less than 2 days ago';
    END IF;
END$$
DELIMITER ;

-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, delete các
-- question khi question đó chưa nằm trong exam nào

DROP TRIGGER IF EXISTS q10_update;
DELIMITER $$
CREATE TRIGGER q10_update
BEFORE UPDATE ON `question`
FOR EACH ROW
BEGIN
    DECLARE v_exam_count INT DEFAULT 0;

    SELECT COUNT(*) INTO v_exam_count
    FROM exam_question
    WHERE question_id = OLD.question_id;

    IF v_exam_count > 0 THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Cannot update question that already belongs to an exam';
    END IF;
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS q10_delete;
DELIMITER $$
CREATE TRIGGER q10_delete
BEFORE DELETE ON `question`
FOR EACH ROW
BEGIN
    DECLARE v_exam_count INT DEFAULT 0;

    SELECT COUNT(*) INTO v_exam_count
    FROM exam_question
    WHERE question_id = OLD.question_id;

    IF v_exam_count > 0 THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Cannot delete question that already belongs to an exam';
    END IF;
END$$
DELIMITER ;

-- Question 12: Lấy ra thông tin exam trong đó:
-- Duration <= 30 thì sẽ đổi thành giá trị "Short time"
-- 30 < Duration <= 60 thì sẽ đổi thành giá trị "Medium time"
-- Duration > 60 thì sẽ đổi thành giá trị "Long time"

SELECT exam_id, `code`, title, category_id, 
		duration, CASE  WHEN TIME_TO_SEC(duration) / 60 <= 30 THEN 'Short time'
        WHEN TIME_TO_SEC(duration) / 60 <= 60 THEN 'Medium time' ELSE 'Long time' END 
        AS duration_label,
		creator_id,create_date
FROM exam;

-- Question 13: Thống kê số account trong mỗi group và in ra thêm 1 column nữa có tên
-- là the_number_user_amount và mang giá trị được quy định như sau:
-- Nếu số lượng user trong group =< 5 thì sẽ có giá trị là few
-- Nếu số lượng user trong group <= 20 và > 5 thì sẽ có giá trị là normal
-- Nếu số lượng user trong group > 20 thì sẽ có giá trị là higher

SELECT 
    g.group_id,
    g.group_name,
    COUNT(ga.account_id) AS total_user,
    CASE
		WHEN COUNT(ga.account_id) <= 5 THEN 'few' 
        WHEN COUNT(ga.account_id) <= 20 THEN 'normal' ELSE 'higher' END AS the_number_user_amount
FROM `group` g
LEFT JOIN group_account ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name;

-- Question 14: Thống kê số mỗi phòng ban có bao nhiêu user, nếu phòng ban nào
-- không có user thì sẽ thay đổi giá trị 0 thành "Không có User"

SELECT d.department_id, d.department_name,
    CASE 
		WHEN COUNT(a.account_id) = 0 THEN 'Không có User'
        ELSE CAST(COUNT(a.account_id) AS CHAR) END AS user_count
FROM department d
LEFT JOIN `account` a ON d.department_id = a.department_id
GROUP BY d.department_id, d.department_name;