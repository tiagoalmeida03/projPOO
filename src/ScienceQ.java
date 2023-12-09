import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ScienceQ class represents a science-related question in the POO Trivia game. It extends
 * the Questions abstract class and provides specific implementations for science questions.
 *
 */
public class ScienceQ extends Questions {

    /**
     * Constructs a ScienceQ object with the specified text, options, and correct answer.
     *
     * @param text          The text of the science question.
     * @param options       The list of options for the science question.
     * @param correctAnswer The correct answer to the science question.
     */
    public ScienceQ(String text, List<String> options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    /**
     * Gets the type of the question, which is "Science" for ScienceQ objects.
     *
     * @return The type of the question.
     */
    @Override
    public String questionType() {
        return "Science";
    }

    /**
     * Gets the text of the science question.
     *
     * @return The text of the science question.
     */
    @Override
    public String getQuestion() {
        return textQuestion;
    }

    /**
     * Gets the list of possible answers to the science question.
     *
     * @return The list of possible answers.
     */
    @Override
    public List<String> getAnswers() {
        return options;
    }

    /**
     * Checks if the provided answer is correct for the science question.
     *
     * @param answer The answer to check.
     * @return True if the answer is correct, false otherwise.
     */
    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    /**
     * Gets the correct answer to the science question.
     *
     * @return The correct answer.
     */
    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns the points awarded for answering the science question.
     *
     * @return The points awarded for the question.
     */
    @Override
    public int returnPoints() {
        // Award 10 points for answering a science question
        points = 5 + 5;
        return points;
    }

    /**
     * Gets a sublist of options for the science question. The sublist contains three options,
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
