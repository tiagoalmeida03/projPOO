import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SportsQ extends Questions {
    public SportsQ(String text, List<String> options, String correctAnswer) {
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
}