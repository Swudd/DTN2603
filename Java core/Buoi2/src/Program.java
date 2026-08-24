import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        // ================== 1. DEPARTMENT (>= 3) ==================
        Department dep1 = new Department("Software Development", 1);
        Department dep2 = new Department("Quality Assurance", 2);
        Department dep3 = new Department("Human Resources", 3);
        List<Department> departments = new ArrayList<>();
        departments.add(dep1);
        departments.add(dep2);
        departments.add(dep3);

        // ================== 2. POSITION (>= 3) ==================
        Position pos1 = new Position(1, PositionName.DEV);
        Position pos2 = new Position(2, PositionName.TEST);
        Position pos3 = new Position(3, PositionName.SCRUM_MASTER);
        List<Position> positions = new ArrayList<>();
        positions.add(pos1);
        positions.add(pos2);
        positions.add(pos3);

        // ================== 3. ACCOUNT (>= 3) ==================
        Account acc1 = new Account(1, "an.nguyen@company.com", "annguyen", "Nguyen Van An", pos1, new Date(), dep1);
        Account acc2 = new Account(2, "binh.tran@company.com", "binhtran", "Tran Thi Binh", pos2, new Date(), dep2);
        Account acc3 = new Account(3, "cuong.le@company.com", "cuongle", "Le Van Cuong", pos3, new Date(), dep3);
        List<Account> accounts = new ArrayList<>();
        accounts.add(acc1);
        accounts.add(acc2);
        accounts.add(acc3);

        // ================== 4. CATEGORY_QUESTION (>= 3) ==================
        CategoryQuestion cate1 = new CategoryQuestion(1, CategoryName.JAVA);
        CategoryQuestion cate2 = new CategoryQuestion(2, CategoryName.SQL);
        CategoryQuestion cate3 = new CategoryQuestion(3, CategoryName.POSTMAN);
        List<CategoryQuestion> categories = new ArrayList<>();
        categories.add(cate1);
        categories.add(cate2);
        categories.add(cate3);

        // ================== 5. TYPE_QUESTION (>= 3, chỉ có 2 giá trị enum nên lặp lại) ==================
        TypeQuestion type1 = new TypeQuestion(1, TypeName.ESSAY);
        TypeQuestion type2 = new TypeQuestion(2, TypeName.MULTIPLE_CHOICE);
        TypeQuestion type3 = new TypeQuestion(3, TypeName.MULTIPLE_CHOICE);
        List<TypeQuestion> types = new ArrayList<>();
        types.add(type1);
        types.add(type2);
        types.add(type3);

        // ================== 6. QUESTION (>= 3) ==================
        Question ques1 = new Question(1, "What is the difference between JDK and JRE?", cate1, type1, acc1, new Date());
        Question ques2 = new Question(2, "Write a SQL query to select all employees.", cate2, type2, acc2, new Date());
        Question ques3 = new Question(3, "How to send a POST request in Postman?", cate3, type3, acc3, new Date());
        List<Question> questions = new ArrayList<>();
        questions.add(ques1);
        questions.add(ques2);
        questions.add(ques3);

        // ================== 7. ANSWER (>= 3) ==================
        Answer ans1 = new Answer(1, "JDK includes JRE plus development tools", ques1, true);
        Answer ans2 = new Answer(2, "SELECT * FROM employees;", ques2, true);
        Answer ans3 = new Answer(3, "Set method to POST in Postman", ques3, true);
        List<Answer> answers = new ArrayList<>();
        answers.add(ans1);
        answers.add(ans2);
        answers.add(ans3);

        // ================== 8. EXAM (>= 3) ==================
        Exam exam1 = new Exam(1, "EX01", "Java Fundamentals Test", cate1, Duration.ofMinutes(60), acc1, new Date());
        Exam exam2 = new Exam(2, "EX02", "SQL Skill Assessment", cate2, Duration.ofMinutes(90), acc2, new Date());
        Exam exam3 = new Exam(3, "EX03", "Postman API Testing", cate3, Duration.ofMinutes(30), acc3, new Date());
        List<Exam> exams = new ArrayList<>();
        exams.add(exam1);
        exams.add(exam2);
        exams.add(exam3);

        // ================== 9. EXAM_QUESTION (>= 3) ==================
        ExamQuestion examQues1 = new ExamQuestion(exam1, ques1);
        ExamQuestion examQues2 = new ExamQuestion(exam2, ques2);
        ExamQuestion examQues3 = new ExamQuestion(exam3, ques3);
        List<ExamQuestion> examQuestions = new ArrayList<>();
        examQuestions.add(examQues1);
        examQuestions.add(examQues2);
        examQuestions.add(examQues3);

        // ================== 10. GROUP (>= 3) ==================
        Group group1 = new Group(1, "Java Backend Team");
        Group group2 = new Group(2, "QA Automation Team");
        Group group3 = new Group(3, "SQL Practice Group");
        List<Group> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);

        // ================== 11. GROUP_ACCOUNT (>= 3) ==================
        GroupAccount groupAcc1 = new GroupAccount(1, acc1, new Date());
        GroupAccount groupAcc2 = new GroupAccount(2, acc2, new Date());
        GroupAccount groupAcc3 = new GroupAccount(3, acc3, new Date());
        List<GroupAccount> groupAccounts = new ArrayList<>();
        groupAccounts.add(groupAcc1);
        groupAccounts.add(groupAcc2);
        groupAccounts.add(groupAcc3);

        q1(accounts.get(1));
        q2(accounts.get(1), groups, groupAccounts);
        q3(accounts.get(1));
        q4(accounts.get(0));
        q5(groups.get(0), groupAccounts);
        q6(accounts.get(1), groups, groupAccounts);
        q7(accounts.get(0));
        q8(accounts);
        q9(departments);
        q10(accounts);
        q11(departments);
        q12(departments);
        q13(accounts);
        q14(accounts);
        q15();
        q16(accounts);
        q17(accounts);

    }
    // Question 1: IF - kiểm tra department của account
    public static void q1(Account account) {
        System.out.println("\n=== Question 1 ===");
        if (account.getDepartment() == null) {
            System.out.println("Nhân viên này chưa có phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là " + account.getDepartment().departmentName);
        }
    }

    // Question 2: IF - kiểm tra số lượng group của account
    public static void q2(Account account, List<Group> groups, List<GroupAccount> groupAccounts) {
        System.out.println("\n=== Question 2 ===");
        List<String> groupNames = getGroupNamesOfAccount(account, groups, groupAccounts);
        int count = groupNames.size();

        if (count == 0) {
            System.out.println("Nhân viên này chưa có group");
        } else if (count == 1 || count == 2) {
            System.out.println("Group của nhân viên này là " + String.join(", ", groupNames));
        } else if (count == 3) {
            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
        } else {
            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }
    }

    // lấy danh sách tên group mà account tham gia
    private static List<String> getGroupNamesOfAccount(Account account, List<Group> groups, List<GroupAccount> groupAccounts) {
        List<String> result = new ArrayList<>();
        for (GroupAccount ga : groupAccounts) {
            if (ga.account.accountId == account.accountId) {
                for (Group g : groups) {
                    if (g.groupId == ga.groupId) {
                        result.add(g.groupName);
                    }
                }
            }
        }
        return result;
    }

    // Question 3: Ternary - làm lại Question 1
    public static void q3(Account account) {
        System.out.println("\n=== Question 3 (ternary) ===");
        String result = account.getDepartment() == null
                ? "Nhân viên này chưa có phòng ban"
                : "Phòng ban của nhân viên này là " + account.getDepartment().departmentName;
        System.out.println(result);
    }

    // Question 4: Ternary - kiểm tra Position
    public static void q4(Account account) {
        System.out.println("\n=== Question 4 (ternary) ===");
        String result = account.getPosition().positionName == PositionName.DEV
                ? "Đây là Developer"
                : "Người này không phải là Developer";
        System.out.println(result);
    }

    // Question 5: SWITCH CASE - số lượng thành viên trong group
    public static void q5(Group group, List<GroupAccount> groupAccounts) {
        System.out.println("\n=== Question 5 (switch) ===");
        int count = 0;
        for (GroupAccount ga : groupAccounts) {
            if (ga.groupId == group.groupId) {
                count++;
            }
        }

        switch (count) {
            case 1:
                System.out.println("Nhóm có một thành viên");
                break;
            case 2:
                System.out.println("Nhóm có hai thành viên");
                break;
            case 3:
                System.out.println("Nhóm có ba thành viên");
                break;
            default:
                System.out.println("Nhóm có nhiều thành viên");
        }
    }

    // Question 6: SWITCH CASE - làm lại Question 2
    public static void q6(Account account, List<Group> groups, List<GroupAccount> groupAccounts) {
        System.out.println("\n=== Question 6 (switch) ===");
        List<String> groupNames = getGroupNamesOfAccount(account, groups, groupAccounts);
        int count = groupNames.size();

        switch (count) {
            case 0:
                System.out.println("Nhân viên này chưa có group");
                break;
            case 1:
            case 2:
                System.out.println("Group của nhân viên này là " + String.join(", ", groupNames));
                break;
            case 3:
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                break;
            default:
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }
    }

    // Question 7: SWITCH CASE - làm lại Question 4
    public static void q7(Account account) {
        System.out.println("\n=== Question 7 (switch) ===");
        switch (account.getPosition().positionName) {
            case DEV:
                System.out.println("Đây là Developer");
                break;
            default:
                System.out.println("Người này không phải là Developer");
        }
    }

    // Question 8: FOREACH - in bảng thông tin account
    public static void q8(List<Account> accounts) {
        System.out.println("\n=== Question 8 ===");
        System.out.printf("|%-30s|%-20s|%-15s%n", "Email", "Full Name", "Department");
        System.out.println("-".repeat(65));
        for (Account a : accounts) {
            String deptName = a.getDepartment() == null ? "N/A" : a.getDepartment().departmentName;
            System.out.printf("|%-30s|%-20s|%-15s%n", a.getEmail(), a.getFullName(), deptName);
        }
    }

    // Question 9: FOREACH - in bảng thông tin department
    public static void q9(List<Department> departments) {
        System.out.println("\n=== Question 9 ===");
        System.out.printf("|%-5s|%-20s%n", "ID", "Name");
        System.out.println("-".repeat(25));
        for (Department d : departments) {
            System.out.printf("|%-5d|%-20s%n", d.departmentId, d.departmentName);
        }
    }

    // Question 10: FOR - in thông tin account theo format có thứ tự
    public static void q10(List<Account> accounts) {
        System.out.println("\n=== Question 10 ===");
        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);
            String deptName = a.getDepartment() == null ? "N/A" : a.getDepartment().departmentName;
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.printf("%-12s: %s%n", "Email", a.getEmail());
            System.out.printf("%-12s: %s%n", "Full name", a.getFullName());
            System.out.printf("%-12s: %s%n", "Phòng ban", deptName);
            System.out.println();
        }
    }

    // Question 11: FOR - in thông tin department theo format có thứ tự
    public static void q11(List<Department> departments) {
        System.out.println("\n=== Question 11 ===");
        for (int i = 0; i < departments.size(); i++) {
            Department d = departments.get(i);
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.printf("%-8s: %d%n", "Id", d.departmentId);
            System.out.printf("%-8s: %s%n", "Name", d.departmentName);
            System.out.println();
        }
    }

    // Question 12: FOR - chỉ in 2 department đầu tiên
    public static void q12(List<Department> departments) {
        System.out.println("\n=== Question 12 ===");
        for (int i = 0; i < departments.size() && i < 2; i++) {
            Department d = departments.get(i);
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.printf("%-8s: %d%n", "Id", d.departmentId);
            System.out.printf("%-8s: %s%n", "Name", d.departmentName);
            System.out.println();
        }
    }

    // Question 13: FOR - in tất cả account trừ account thứ 2
    public static void q13(List<Account> accounts) {
        System.out.println("\n=== Question 13 ===");
        System.out.printf("|%-30s|%-20s%n", "Email", "Full Name");
        System.out.println("-".repeat(52));
        for (int i = 0; i < accounts.size(); i++) {
            if (i == 1) continue;
            Account a = accounts.get(i);
            System.out.printf("|%-30s|%-20s%n", a.getEmail(), a.getFullName());
        }
    }

    // Question 14: FOR - in account có id < 4
    public static void q14(List<Account> accounts) {
        System.out.println("\n=== Question 14 ===");
        System.out.printf("%-5s %-30s %-20s%n", "ID", "Email", "Full Name");
        System.out.println("-".repeat(58));
        for (Account a : accounts) {
            if (a.getAccountId() < 4) {
                System.out.printf("%-5d %-30s %-20s%n", a.getAccountId(), a.getEmail(), a.getFullName());
            }
        }
    }

    // Question 15: FOR - in các số chẵn <= 20
    public static void q15() {
        System.out.println("\n=== Question 15 ===");
        System.out.printf("%-10s%n", "Số chẵn");
        System.out.println("-".repeat(11));
        for (int i = 2; i <= 20; i += 2) {
            System.out.printf("%-10d%n", i);
        }
    }

    // Question 16: WHILE - làm lại Q13, Q14, Q15 kèm break/continue
    public static void q16(List<Account> accounts) {
        System.out.println("\n=== Question 16 (WHILE) ===");

        System.out.println("-- Q13 bằng WHILE (dùng continue) --");
        int i = 0;
        while (i < accounts.size()) {
            if (i == 1) {
                i++;
                continue;
            }
            Account a = accounts.get(i);
            System.out.printf("%-30s %-20s%n", a.getEmail(), a.getFullName());
            i++;
        }

        System.out.println("-- Q14 bằng WHILE (dùng continue) --");
        int j = 0;
        while (j < accounts.size()) {
            Account a = accounts.get(j);
            if (a.getAccountId() >= 4) {
                j++;
                continue;
            }
            System.out.printf("%-5d %-30s %-20s%n", a.getAccountId(), a.getEmail(), a.getFullName());
            j++;
        }

        System.out.println("-- Q15 bằng WHILE (dùng break) --");
        int num = 2;
        while (true) {
            if (num > 20) {
                break;
            }
            System.out.printf("%-10d%n", num);
            num += 2;
        }
    }

    // Question 17: DO-WHILE - làm lại Q13, Q15 kèm break/continue
    public static void q17(List<Account> accounts) {
        System.out.println("\n=== Question 17 (DO-WHILE) ===");

        System.out.println("-- Q13 bằng DO-WHILE (dùng continue) --");
        int i = 0;
        do {
            if (i == 1) {
                i++;
                continue;
            }
            Account a = accounts.get(i);
            System.out.printf("%-30s %-20s%n", a.getEmail(), a.getFullName());
            i++;
        } while (i < accounts.size());

        System.out.println("-- Q15 bằng DO-WHILE (dùng break) --");
        int num = 2;
        do {
            if (num > 20) {
                break;
            }
            System.out.printf("%-10d%n", num);
            num += 2;
        } while (true);
    }
}