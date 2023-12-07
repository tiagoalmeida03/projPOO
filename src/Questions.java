import java.util.List;

public abstract class Questions {
    protected String questions;
    protected List<String> options;
    protected String correctAnswer;

    public Questions(String questions, List<String> options, String correctAnswer) {
        this.questions = questions;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return questions;
    }

    public List<String> getAnswers() {
        return options;
    }

    public abstract boolean isCorrectAnswer(String answer);

    public abstract List<String> getSublistOptions();
}
