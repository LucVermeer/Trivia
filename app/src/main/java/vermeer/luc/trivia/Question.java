package vermeer.luc.trivia;

public class Question {
    private String question;
    private boolean answer;
    private String category;

    public Question(String question, boolean answer, String category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public boolean getAnswer() {
        return answer;
    }

    public String getCategory() {
        return category;
    }
}
