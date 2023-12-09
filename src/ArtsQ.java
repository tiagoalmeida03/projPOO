import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ArtsQ class represents a question related to arts in the POO Trivia game.
 * It extends the abstract Questions class and provides specific implementations
 * for retrieving question details, answers, checking correctness, calculating points,
 * and creating a sublist of options.
 *
 * @author Your Name
 * @version 1.0
 */
public class ArtsQ extends Questions {

    /**
     * Constructs an ArtsQ object with the specified text, options, and correct answer.
     *
     * @param text          The text of the arts question.
     * @param options       The list of options for the arts question.
     * @param correctAnswer The correct answer to the arts question.
     */
    public ArtsQ(String text, List<String> options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    /**
     * Gets the type of the question.
     *
     * @return The type of the question, which is "Arts".
     */
    @Override
    public String questionType() {
        return "Arts";
    }

    /**
     * Gets the text of the arts question.
     *
     * @return The text of the arts question.
     */
    @Override
    public String getQuestion() {
        return textQuestion;
    }

    /**
     * Gets a list of all possible answers for the arts question.
     *
     * @return The list of options for the arts question.
     */
    @Override
    public List<String> getAnswers() {
        return options;
    }

    /**
     * Checks if a provided answer is correct for the arts question.
     *
     * @param answer The answer to check.
     * @return True if the answer is correct, otherwise False.
     */
    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    /**
     * Gets the correct answer to the arts question.
     *
     * @return The correct answer for the arts question.
     */
    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Calculates and returns the points associated with the arts question.
     *
     * @return The points for the arts question.
     */
    @Override
    public int returnPoints() {
        points = 5 * 10; // Assuming each arts question is worth 50 points
        return points;
    }

    /**
     * Creates a sublist of options for the arts question.
     * The sublist includes three options, including the correct answer.
     *
     * @return The sublist of options for the arts question.
     */
    @Override
    public List<String> getSublistOptions() {
        // Create a sublist with three options, including the correct answer
        List<String> sublistOptions = new ArrayList<>(options);
        Collections.shuffle(sublistOptions); // Shuffle the options
        return sublistOptions.subList(0, 3); // Take the first three options
    }
}
