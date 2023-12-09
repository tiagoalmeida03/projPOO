import java.util.List;

public abstract class Questions {
    protected String textQuestion;
    protected List<String> options;
    protected String correctAnswer;

    public Questions(String text, List<String> options, String correctAnswer) {
        this.textQuestion = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return textQuestion;
    }

    public List<String> getOptions() {
        return options;
    }

    public abstract List<String> getAnswers();

    public abstract boolean isCorrectAnswer(String answer);

    public abstract List<String> getSublistOptions();
}