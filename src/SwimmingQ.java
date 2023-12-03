import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwimmingQ extends SportsQ {
    public SwimmingQ(String text, List<String> options, String correctAnswer) {
        super(text, options, correctAnswer);
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