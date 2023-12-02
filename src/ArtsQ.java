import java.util.List;

public class ArtsQ extends Questions {
    private String text;
    private List<String> options;
    private String correctAnswer;

    public ArtsQ(String text, List<String> options, String correctAnswer) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getQuestion() {
        return text;
    }

    @Override
    public List<String> getAnswers() {
        return options;
    }

    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }
}
