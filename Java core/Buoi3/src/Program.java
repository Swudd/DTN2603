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
        Account acc1 = new Account(1, "an.nguyen@company.com", "annguyen", "Nguyen Van An", dep1, pos1, new Date(), 5240.5f);
        Account acc2 = new Account(2, "binh.tran@company.com", "binhtran", "Tran Thi Binh", dep1, pos2, new Date(), 10970.055f);
        Account acc3 = new Account(3, "cuong.le@company.com", "cuongle", "Le Van Cuong", dep1, pos3, new Date(), 1234.3432f);
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
        Group group1 = new Group("1", "Java Backend Team");
        Group group2 = new Group("2", "QA Automation Team");
        Group group3 = new Group("3", "SQL Practice Group");
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

        Exercise1.question1(acc1.salary);
        System.out.println(Exercise1.question2(2));
        Exercise1.question3(Exercise1.question2(2));
        Exercise1.question4(4,6);


        for(Account a:Exercise2.question1(5)){
            a.showInfo();
        }

        Integer salary = 5000;
        Excersise3.question1(salary);

        Excersise3.question2("123");

        System.out.println(Exercise4.question1("dem  so tu"));
        System.out.println(Exercise4.question2("he","lo"));
        //Exercise4.question3();
        Exercise4.question4();


    }
}