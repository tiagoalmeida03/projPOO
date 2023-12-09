import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The SportsQ class is an abstract class representing sports-related questions in the POO Trivia game.
 * It extends the Questions abstract class and provides a common structure for sports questions.
 *
 */
public abstract class SportsQ extends Questions {

    /**
     * Constructs a SportsQ object with the specified text, options, and correct answer.
     *
     * @param text          The text of the sports question.
     * @param options       The list of options for the sports question.
     * @param correctAnswer The correct answer to the sports question.
     */
    public SportsQ(String text, List<String> options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    /**
     * Gets the list of possible answers to the sports question.
     *
     * @return The list of possible answers.
     */
    @Override
    public List<String> getAnswers() {
        return options;
    }

    /**
     * Checks if the provided answer is correct for the sports question.
     *
     * @param answer The answer to check.
     * @return True if the answer is correct, false otherwise.
     */
    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    /**
     * Abstract method to be implemented by subclasses. It defines how many points
     * are awarded for answering a sports question.
     *
     * @return The points awarded for the sports question.
     */
    public abstract int returnPoints();

    /**
     * Gets a sublist of options for the sports question. The sublist contains three options,
     * including the correct answer, and the options are shuffled.
     *
     * @return A sublist of shuffled options.
     */
    @Override
    public List<String> getSublistOptions() {
        // Create a sublist with three options, including the correct answer
        List<String> sublistOptions = new ArrayList<>(options);
        Collections.shuffle(sublistOptions); // Shuffle the options
        return sublistOptions.subList(0, 3); // Take the first three options
    }
}
