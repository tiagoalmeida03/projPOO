import java.util.List;

public abstract class Questions {
    protected String text;
    protected List<String> options;
    protected String correctAnswer;

    public Questions(String text, List<String> options, String correctAnswer) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public abstract List<String> getAnswers();

    public abstract boolean isCorrectAnswer(String answer);

    public abstract List<String> getSublistOptions();
}