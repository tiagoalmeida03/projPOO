import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The SwimmingQ class represents a swimming-related question in the POO Trivia game.
 * It extends the SportsQ abstract class, providing specific functionality for swimming questions.
 *
 */
public class SwimmingQ extends SportsQ {

    /**
     * Constructs a SwimmingQ object with the specified text, options, and correct answer.
     *
     * @param text          The text of the swimming question.
     * @param options       The list of options for the swimming question.
     * @param correctAnswer The correct answer to the swimming question.
     */
    public SwimmingQ(String text, List<String> options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    /**
     * Gets the type of the question, which is "Swimming" for this class.
     *
     * @return The type of the question.
     */
    @Override
    public String questionType() {
        return "Swimming";
    }

    /**
     * Gets the list of possible answers to the swimming question.
     *
     * @return The list of possible answers.
     */
    @Override
    public List<String> getAnswers() {
        return options;
    }

    /**
     * Checks if the provided answer is correct for the swimming question.
     *
     * @param answer The answer to check.
     * @return True if the answer is correct, false otherwise.
     */
    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    /**
     * Gets the correct answer to the swimming question.
     *
     * @return The correct answer.
     */
    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Calculates and returns the points awarded for answering the swimming question.
     * Points are calculated based on a predefined formula.
     *
     * @return The points awarded for the swimming question.
     */
    @Override
    public int returnPoints() {
        points = 5 + 3 + 10;
        return points;
    }

    /**
     * Gets a sublist of options for the swimming question. The sublist contains three options,
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

    /**
     * Checks if the provided True/False answer is correct for the swimming question.
     * The correct answer is determined based on the shuffled options.
     *
     * @param selectedAnswer The selected True/False answer.
     * @return True if the answer is correct, false otherwise.
     */
    @Override
    public boolean isTrueFalseCorrect(boolean selectedAnswer) {
        // Determine the correct answer based on the shuffled options
        String correctOption = getSublistOptions().get(0);
        return selectedAnswer == correctOption.equalsIgnoreCase("True");
    }
}
