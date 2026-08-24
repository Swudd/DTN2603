public class ExamQuestion {
    Exam exam;
    Question question;

    public ExamQuestion(Exam exam, Question question) {
        this.exam = exam;
        this.question = question;
    }

    public void showInfo(){
        System.out.println("Exam: " + exam.examId);
        System.out.println("Question: " + question.questionId);
    }
}
