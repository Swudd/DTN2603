--  Question 1: Thêm ít nhất 10 record vào mỗi table
USE TesTINg_System;
INSERT INTO DePARTment (departMent_name) VALUES
('MARKeTing'),
('FiNAnCe'),
('SaLEs'),
('DeVOpS'),
('DaTA Engineering'),
('CuSToMer Support'),
('LeGAl'),
('R&D'),
('UI/UX Design'),
('SeCUrIty');
INSERT INTO `pOSITion` (positiOn_name) VALUES
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
INSERT INTO `aCCOUnt` (email, usernAme, full_Name, deparTment_id, positIon_id) VALUES
('PHUC.Vo@company.com', 'phucVo', 'Vo VAn Phuc', 5, 5),
('gIANg.do@company.com', 'gianGdo', 'Do THi Giang', 6, 6),
('hAI.bUi@company.com', 'haibUi', 'Bui Van Hai', 7, 7),
('iRIS.Dang@company.com', 'irisDang', 'Dang Thi Iris', 8, 8),
('kHOA.Ly', 'khoaLy', 'Ly TRong Khoa', 9, 9),
('lAN.mAi@company.com', 'lanmAi', 'Mai Thi Lan', 10, 10),
('MINH.Duong@company.com', 'minhDuong', 'DuonG Van Minh', 1, 1),
('nGA.tRuong@company.com', 'ngatRuong', 'TruoNg Thi Nga', 2, 2),
('oANH.Vu@company.com', 'oanhVu', 'Vu THi Oanh', 3, 3),
('pHONg.ho@company.com', 'phonGho', 'Ho VAn Phong', 4, 4);
INSERT INTO `gROUP` (group_Name, creatOr_id) VALUES
('.NET Core Team', 5),
('POSTmAn API Testers', 6),
('RUBY Developers', 7),
('DEVOpS Engineers', 8),
('DATA Team', 9),
('UI/UX Designers', 10),
('SECUrIty Team', 6),
('FRONtEnd Guild', 7),
('BACKeNd Guild', 8),
('MOBIlE Team', 9);
INSERT INTO GrOUP_account (group_Id, accouNt_id) VALUES
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
INSERT INTO TyPE_Question (type_nAme) VALUES
('MATChIng'),
('OrDErIng'),
('CoDInG Exercise'),
('CaSE Study'),
('ShORt Answer'),
('DrAG And Drop'),
('HoTSpOt'),
('ScENaRio Based'),
('DeBUgGing'),
('PrACtIcal Test');

INSERT INTO QuESTIon (contenT, categOry_id, type_Id, creatOr_id) VALUES
('WHAT Is polymorphism in Java?', 1, 1, 5),
('EXPLaIn dependency injection in .NET.', 2, 2, 6),
('WRITe a query to find duplicate emails in a table.', 3, 5, 7),
('HOW dO you send a POST request with a JSON body in Postman?', 4, 4, 8),
('WHAT Is a Ruby module and how is it different from a class?', 5, 2, 9),
('WHAT Is the difference between abstract class and interface in Java?', 1, 2, 10),
('EXPLaIn the MVC pattern in .NET.', 2, 1, 5),
('WRITe a SQL query using JOIN to combine two tables.', 3, 5, 6),
('HOW dO you use environment variables in Postman?', 4, 1, 7),
('WHAT Is the purpose of "yield" in Ruby?', 5, 2, 8);


INSERT INTO AnSWER (contenT, questIon_id, is_coRrect) VALUES
('ABILiTy of an object to take many forms', 6, 'CORRECT'),
('AbILiTy to inherit only one class', 6, 'WRONG'),
('A DEsIgn pattern to inject dependencies at runtime', 7, 'CORRECT'),
('A WAy to hide dependencies', 7, 'WRONG'),
('SELECT email, COUNT(*) FROM users GROUP BY email HAVING COUNT(*) > 1;', 8, 'CORRECT'),
('SeT mEthod to POST and add JSON in Body tab', 9, 'CORRECT'),
('A MOdUle cannot be instantiated, a class can', 10, 'CORRECT'),
('AbSTrAct class allows method implementation, interface does not (before Java 8)', 11, 'CORRECT'),
('MVC sEparates Model, View and Controller', 12, 'CORRECT'),
('YiELd transfers control to the block passed to a method', 15, 'CORRECT');

INSERT INTO ExAM (`code`, title, categOry_id, duratIon, creatOr_id) VALUES
(6, 'AdVANced Java Exam', 1, '01:15:00', 5),
(7, 'C# ADvanced Test', 2, '01:00:00', 6),
(8, 'SQL Advanced Query Test', 3, '01:30:00', 7),
(9, 'PoSTMan Automation Test', 4, '00:40:00', 8),
(10, 'RUBY Advanced Exam', 5, '01:00:00', 9),
(11, 'JAVA OOP Concepts', 1, '00:50:00', 10),
(12, '.NET MVC Exam', 2, '01:00:00', 5),
(13, 'SQL Joins & Subqueries', 3, '01:20:00', 6),
(14, 'POSTMan Environment Setup', 4, '00:30:00', 7),
(15, 'RUBY Modules & Classes', 5, '00:45:00', 8);
INSERT INTO ExAM_Question (exam_iD, questIon_id) VALUES
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
from dEPARTMeNt;

-- Question 3: lấy ra id của phòng ban "Sale"

select department_id
FROM department
wherE DEpartment_namE LIKe "Sales";

-- QuESTIon 4: lấy ra thông tin account có full name dài nhất

SELECT account_id, email, uSERNAMe, full_namE, LENGTH(full_naMe) AS name_lENGTH
FROM `accoUnT`
where LENGTH(FULl_name) = (SELECt LENGTH(full_namE) aS LENGTh
							from `acCoUNt`
							ORDER BY LENGTH(full_name) DESC
							LIMIt 1);

-- QUESTion 5: LẤY RA THông tin account có full name dài nhất và thuộc phòng ban có id= 3

SELECT account_id, email, username, fulL_NAME, departmenT_id, LENGTH(full_name) AS nAme_length
FROM `ACCOUNT`
where LENGTH(full_name) = (Select lengtH(FULl_NAME) as length
							FROm `ACCOUnt`
                            where department_id=3
							ORDER BY LENGTH(full_name) DESC
							LIMIT 1) AND DEPartment_iD=3;

-- QuestION 6: LấY Ra tên group đã Tham gia trước ngày 20/12/2019

select group_name, create_date 
from `group`
wheRE CREate_date < "2019-12-22";

-- Question 7: LẤY ra ID của quEstion có >= 4 câu trả lời

select question_id, count(1) as count
from answer
group by QUESTion_id
havinG COUNT(1) >= 4;

-- QUEstion 8: LẤY rA các mã đề thi CÓ THờI GIAN ThI >= 60 phút và được tạo trước ngày 20/12/2019

select `code`, duration, create_date
from exam
where duration > "00:60:00" and cREATE_date < "2019-12-20";

-- QuestiON 9: Lấy RA 5 group được tạo gần đây NHẤt

select *
fRom `group`
order by create_date desc
limit 5;

-- Question 10: Đếm số nhân viên thuỘC DEPaRtMENT có id = 2

SElECt count(1) as COUnT
FROm `account`
where department_id =2;

-- Question 11: Lấy ra nhân viên có tên BẮT ĐầU BẰNG ChỮ "D" và KẾT thúc bằng cHỮ "O"

select *
froM `account`
where full_name like "D%o";

-- Question 12: Xóa tất cả các exam được tạo trước ngày 20/12/2019

DELETE eq FROM exam_question eq
JOIN exam e ON eq.exam_id = e.exam_id
WHERE e.create_date < '2019-12-20';

DELETE FROM exam
WHERE create_date < '2019-12-20';

-- Question 13: Xóa tất cả các question có nội dung bắt đầu bằng từ "câu hỏi"

DELETE a FROM answer a
JOIN question q ON a.question_id = q.question_id
WHERE q.content LIKE 'câu hỏi%';

DELETE eq FROM exam_question eq
JOIN question q ON eq.question_id = q.question_id
WHERE q.content LIKE 'câu hỏi%';

DELETE FROM question
WHERE content LIKE 'câu hỏi%';

-- Question 14: Update thông tin của account có id = 5 thành tên "Nguyễn Bá Lộc" và email thành loc.nguyenba@vti.com.vn

update `account`
set username = 'Nguyễn Bá Lộc', email = "loc.nguyenba@vti.com.vn"
where account_id = 5;

-- Question 15: update account có id = 5 sẽ thuộc group có id = 4

update group_account
set group_id = 4
where account_id = 5;
