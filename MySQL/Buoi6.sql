USE testing_system;
-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó.

DROP PROCEDURE IF EXISTS get_account_by_de;

DELIMITER $$
CREATE PROCEDURE get_account_by_de (IN dep_name CHAR(100))
	BEGIN
		SELECT a.*, d. department_name
		FROM `account` a
		JOIN department d on d.department_id = a.department_id
		WHERE d.department_name = dep_name;
	END$$
DELIMITER ;

CALL get_account_by_de('Sales');
        
-- Question 2: Tạo store để in ra số lượng account trong mỗi group.

DROP PROCEDURE IF EXISTS get_account_count_by_group;

DELIMITER $$
CREATE PROCEDURE get_account_count_by_group()
BEGIN
    SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS account_count
    FROM `group` g
    LEFT JOIN group_account ga ON g.group_id = ga.group_id
    GROUP BY g.group_id, g.group_name;
END$$
DELIMITER ;

CALL get_account_count_by_group();

-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại.

DROP PROCEDURE IF EXISTS get_question_count_by_type_current_month;

DELIMITER $$
CREATE PROCEDURE get_question_count_by_type_current_month()
BEGIN
    SELECT t.type_id, t.type_name, COUNT(q.question_id) AS question_count
    FROM type_question t
    LEFT JOIN question q 
        ON t.type_id = q.type_id
        AND MONTH(q.create_date) = MONTH(CURDATE())
        AND YEAR(q.create_date) = YEAR(CURDATE())
    GROUP BY t.type_id, t.type_name;
END$$
DELIMITER ;

CALL get_question_count_by_type_current_month();

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất.

DROP PROCEDURE IF EXISTS get_type_id_most_questions;
DELIMITER $$
CREATE PROCEDURE get_type_id_most_questions(OUT most_type_id INT)
BEGIN
    SELECT t.type_id
    INTO most_type_id
    FROM type_question t
    LEFT JOIN question q ON t.type_id = q.type_id
    GROUP BY t.type_id
    ORDER BY COUNT(q.question_id) DESC
    LIMIT 1;
END$$
DELIMITER ;

CALL get_type_id_most_questions(@most_type_id);
SELECT @most_type_id;

-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question.

CALL get_type_id_most_questions(@most_type_id);

SELECT type_id, type_name
FROM type_question
WHERE type_id = @most_type_id;

-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên chứa chuỗi của người dùng nhập vào
-- hoặc trả về user có username chứa chuỗi của người dùng nhập vào.

DROP PROCEDURE IF EXISTS search_group_or_account;

DELIMITER $$
CREATE PROCEDURE search_group_or_account (IN search_input VARCHAR(100))
BEGIN
	SELECT *
    FROM `group`
    WHERE group_name LIKE CONCAT('%', search_input, '%');
    SELECT *
    FROM `account`
    WHERE username LIKE concat('%', search_input,'%');
END$$
DELIMITER ;

CALL search_group_or_account('an');


-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:
-- username sẽ giống email nhưng bỏ phần @..mail đi
-- positionID: sẽ có default là developer
-- departmentID: sẽ được cho vào 1 phòng chờ
-- Sau đó in ra kết quả tạo thành công

DROP PROCEDURE IF EXISTS create_account_default;
DELIMITER $$
CREATE PROCEDURE create_account_default (
    IN p_full_name VARCHAR(100),
    IN p_email VARCHAR(100)
)
BEGIN
    DECLARE v_username VARCHAR(100);
    DECLARE v_position_id INT;
    DECLARE v_department_id INT;
    DECLARE v_new_account_id INT;

    --  Tạo username từ email (lấy phần trước dấu @)
    SET v_username = SUBSTRING_INDEX(p_email, '@', 1);

    SELECT position_id INTO v_position_id
    FROM `position`
    WHERE position_name = 'DEV'
    LIMIT 1;

    --  Lấy department_id của "phòng chờ" (nếu chưa có thì tự tạo)
    SELECT department_id INTO v_department_id
    FROM department
    WHERE department_name = 'Phòng chờ'
    LIMIT 1;

    IF v_department_id IS NULL THEN
        INSERT INTO department (department_name) VALUES ('Phòng chờ');
        SET v_department_id = LAST_INSERT_ID();
    END IF;
    
--    INSERT INTO department(department_name)
--    SELECT 'Phòng chờ'
--    WHERE NOT EXISTS (
	-- SELECT 1 FROM department WHERE department_name LIKE 'Phòng chờ'
-- );

    --  Thêm account mới
    INSERT INTO `account` (email, username, full_name, department_id, position_id)
    VALUES (p_email, v_username, p_full_name, v_department_id, v_position_id);

    SET v_new_account_id = LAST_INSERT_ID();

    --  In ra kết quả tạo thành công
    SELECT 
        CONCAT('Tạo account thành công! account_id = ', v_new_account_id) AS message,
        v_new_account_id AS account_id,
        p_full_name AS full_name,
        p_email AS email,
        v_username AS username,
        v_department_id AS department_id,
        v_position_id AS position_id;
END$$
DELIMITER ;

CALL create_account_default('Nguyen Duc Trung', 'Trung0801@gmail.com');
-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice
-- để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất

DROP PROCEDURE IF EXISTS get_longest_question_by_type;
DELIMITER $$
CREATE PROCEDURE get_longest_question_by_type (IN p_type_name VARCHAR(50))
BEGIN
    SELECT q.question_id, q.content, CHAR_LENGTH(q.content) AS content_length, t.type_name,
        c.category_name, acc.full_name AS creator_name, q.create_date
    FROM question q
    JOIN type_question t ON q.type_id = t.type_id
    JOIN category_question c ON q.category_id = c.category_id
    JOIN `account` acc ON q.creator_id = acc.account_id
    WHERE t.type_name = p_type_name
    ORDER BY CHAR_LENGTH(q.content) DESC
    LIMIT 1;
END$$
DELIMITER ;

CALL get_longest_question_by_type('Essay');

CALL get_longest_question_by_type('Multiple Choice');

-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID

DROP PROCEDURE IF EXISTS delete_exam_by_id;

DELIMITER $$
CREATE PROCEDURE delete_exam_by_id (IN p_exam_id INT)
BEGIN
    
    DELETE FROM exam_question
    WHERE exam_id = p_exam_id;

    DELETE FROM exam
    WHERE exam_id = p_exam_id;
END$$
DELIMITER ;

CALL delete_exam_by_id(3);

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử
-- dụng store ở câu 9 để xóa)
-- Sau đó in số lượng record đã remove từ các table liên quan trong khi removing



DELIMITER $$
CREATE PROCEDURE q10()
	BEGIN
		DECLARE count_exam_delete int;
        DECLARE count_exam_question_delete int;
        DECLARE
    END $$
DELIMTER ;

-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng
-- nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được
-- chuyển về phòng ban default là phòng ban chờ việc

DROP PROCEDURE IF EXISTS delete_department_by_name;
DELIMITER $$
CREATE PROCEDURE delete_department_by_name (IN p_department_name VARCHAR(100))
BEGIN
    DECLARE v_department_id INT;
    DECLARE v_default_department_id INT;

    --  Lấy department_id của phòng ban cần xóa
    SELECT department_id INTO v_department_id
    FROM department
    WHERE department_name = p_department_name
    LIMIT 1;

    -- Nếu không tìm thấy phòng ban thì dừng lại, không làm gì thêm
    IF v_department_id IS NOT NULL THEN

        --  Lấy department_id của "Phòng chờ" (nếu chưa có thì tự tạo)
        SELECT department_id INTO v_default_department_id
        FROM department
        WHERE department_name = 'Phòng chờ'
        LIMIT 1;

        IF v_default_department_id IS NULL THEN
            INSERT INTO department (department_name) VALUES ('Phòng chờ');
            SET v_default_department_id = LAST_INSERT_ID();
        END IF;



            --  Chuyển toàn bộ account thuộc phòng ban cũ sang "Phòng chờ"
            UPDATE `account`
            SET department_id = v_default_department_id
            WHERE department_id = v_department_id;

            --  Xóa phòng ban cũ
            DELETE FROM department
            WHERE department_id = v_department_id;

    END IF;
END$$
DELIMITER ;

CALL delete_department_by_name('Marketing');

-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay

DROP PROCEDURE IF EXISTS get_question_count_by_month_current_year;
DELIMITER $$
CREATE PROCEDURE get_question_count_by_month_current_year()
BEGIN
    SELECT 
        MONTH(q.create_date) AS month,
        COUNT(q.question_id) AS question_count
    FROM question q
    WHERE YEAR(q.create_date) = YEAR(CURDATE())
    GROUP BY MONTH(q.create_date)
    ORDER BY month;
END$$
DELIMITER ;

CALL get_question_count_by_month_current_year();

-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 
-- tháng gần đây nhất
-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong tháng")

