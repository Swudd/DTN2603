import java.util.Date;

public class Question {
    int questionId;
    String content;
    CategoryQuestion categoryQuestion;
    TypeQuestion  typeQuestion;
    Account creatorAccount;
    Date createDate;

    public Question(int questionId, String content, CategoryQuestion categoryQuestion, TypeQuestion typeQuestion, Account creatorAccount, Date createDate) {
        this.questionId = questionId;
        this.content = content;
        this.categoryQuestion = categoryQuestion;
        this.typeQuestion = typeQuestion;
        this.creatorAccount = creatorAccount;
        this.createDate = createDate;
    }

    public void showInfo(){
        System.out.println("Question Id: " + questionId);
        System.out.println("Question Content: " + content);
        System.out.println("CategoryQuestion: " + categoryQuestion.categoryId);
        System.out.println("TypeQuestion: " + typeQuestion.typeId);
        System.out.println("Account creator: " + creatorAccount.accountId);
        System.out.println("Create date: " + createDate);
    }
}
