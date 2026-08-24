public class Answer {
    int answerId;
    String content;
    Question question;
    boolean isCorrect;

    public Answer(int answerId, String content, Question question, boolean isCorrect) {
        this.answerId = answerId;
        this.content = content;
        this.question = question;
        this.isCorrect = isCorrect;
    }

    public void showInfo()
    {
        System.out.println("Answer ID: " + answerId);
        System.out.println("Content: " + content);
        System.out.println("isCorrect: " + isCorrect);
        System.out.println("Question: " + question);
    }
}
