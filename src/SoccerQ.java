import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The SoccerQ class represents a soccer-related question in the POO Trivia game.
 * It extends the SportsQ abstract class, providing specific functionality for soccer questions.
 *
 */
public class SoccerQ extends SportsQ {

    /**
     * Constructs a SoccerQ object with the specified text, options, and correct answer.
     *
     * @param text          The text of the soccer question.
     * @param options       The list of options for the soccer question.
     * @param correctAnswer The correct answer to the soccer question.
     */
    public SoccerQ(String text, List<String> options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    /**
     * Gets the type of the question, which is "Soccer" for this class.
     *
     * @return The type of the question.
     */
    @Override
    public String questionType() {
        return "Soccer";
    }

    /**
     * Gets the list of possible answers to the soccer question.
     *
     * @return The list of possible answers.
     */
    @Override
    public List<String> getAnswers() {
        return options;
    }

    /**
     * Checks if the provided answer is correct for the soccer question.
     *
     * @param answer The answer to check.
     * @return True if the answer is correct, false otherwise.
     */
    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    /**
     * Gets the correct answer to the soccer question.
     *
     * @return The correct answer.
     */
    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Calculates and returns the points awarded for answering the soccer question.
     * Points are calculated based on a predefined formula.
     *
     * @return The points awarded for the soccer question.
     */
    @Override
    public int returnPoints() {
        points = 5 + 3 + 1;
        return points;
    }

    /**
     * Gets a sublist of options for the soccer question. The sublist contains three options,
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
