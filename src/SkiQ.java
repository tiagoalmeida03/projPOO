import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The SkiQ class represents a skiing-related question in the POO Trivia game.
 * It extends the SportsQ abstract class, providing specific functionality for skiing questions.
 *
 */
public class SkiQ extends SportsQ {

    /**
     * Constructs a SkiQ object with the specified text, options, and correct answer.
     *
     * @param text          The text of the skiing question.
     * @param options       The list of options for the skiing question.
     * @param correctAnswer The correct answer to the skiing question.
     */
    public SkiQ(String text, List<String> options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    /**
     * Gets the type of the question, which is "Ski" for this class.
     *
     * @return The type of the question.
     */
    @Override
    public String questionType() {
        return "Ski";
    }

    /**
     * Gets the list of possible answers to the skiing question.
     *
     * @return The list of possible answers.
     */
    @Override
    public List<String> getAnswers() {
        return options;
    }

    /**
     * Checks if the provided answer is correct for the skiing question.
     *
     * @param answer The answer to check.
     * @return True if the answer is correct, false otherwise.
     */
    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    /**
     * Gets the correct answer to the skiing question.
     *
     * @return The correct answer.
     */
    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Gets a sublist of options for the skiing question. The sublist contains three options,
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
     * Calculates and returns the points awarded for answering the skiing question.
     * Points are calculated based on a predefined formula.
     *
     * @return The points awarded for the skiing question.
     */
    @Override
    public int returnPoints() {
        points = (5 + 3) * 2;
        return points;
    }

    /**
     * Checks if the provided True/False answer is correct for the skiing question.
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
