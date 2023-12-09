import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkiQ extends SportsQ {
    public SkiQ(String text, List<String> options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    @Override
    public List<String> getAnswers() {
        return options;
    }

    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    @Override
    public List<String> getSublistOptions() {
        // Create a sublist with three options, including the correct answer
        List<String> sublistOptions = new ArrayList<>(options);
        Collections.shuffle(sublistOptions); // Shuffle the options
        return sublistOptions.subList(0, 3); // Take the first three options
    }

    @Override
    public boolean isTrueFalseCorrect(boolean selectedAnswer) {
        // Determine the correct answer based on the shuffled options
        String correctOption = getSublistOptions().get(0);
        return selectedAnswer == correctOption.equalsIgnoreCase("True");
    }
}