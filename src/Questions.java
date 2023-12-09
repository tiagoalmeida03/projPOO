import java.util.List;

public abstract class Questions {
    protected String textQuestion;
    protected List<String> options;
    protected String correctAnswer;
    protected int points;

    public Questions(String text, List<String> options, String correctAnswer) {
        this.textQuestion = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String questionType(){return null;}

    public String getQuestion() {
        return textQuestion;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isTrueFalseCorrect(boolean selectedAnswer) {
        // Default implementation for non-True/False questions
        return selectedAnswer && correctAnswer.equalsIgnoreCase("True")
                || !selectedAnswer && correctAnswer.equalsIgnoreCase("False");
    }

    public abstract List<String> getAnswers();

    public abstract boolean isCorrectAnswer(String answer);

    public abstract int returnPoints();

    public abstract List<String> getSublistOptions();
}