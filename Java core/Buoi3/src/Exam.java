import java.time.Duration;
import java.util.Date;

public class Exam {
    int examId;
    String code;
    String title;
    CategoryQuestion categoryQuestion;
    Duration duration;
    Account creatorAccount;
    Date createDate;

    public Exam(int examId, String code, String title, CategoryQuestion categoryQuestion, Duration duration, Account creatorAccount, Date createDate) {
        this.examId = examId;
        this.code = code;
        this.title = title;
        this.categoryQuestion = categoryQuestion;
        this.duration = duration;
        this.creatorAccount = creatorAccount;
        this.createDate = createDate;
    }


    public void showInfo()
    {
        System.out.println("Exam ID: " + examId);
        System.out.println("Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Category Question: " + categoryQuestion.categoryId);
        System.out.println("Duration: " + duration.toString());
        System.out.println("Account creator: " + creatorAccount.accountId);
        System.out.println("Date created: " + createDate.toString());
    }
}
