import java.util.List;

/**
 * The Questions abstract class serves as the base class for various question types
 * in the POO Trivia game. It provides common attributes and methods for handling
 * questions, options, correct answers, and scoring. Subclasses must implement
 * specific methods to customize behavior for different question types.
 *
 */
public abstract class Questions {
    // Attributes common to all question types
    protected String textQuestion;
    protected List<String> options;
    protected String correctAnswer;
    protected int points;

    /**
     * Constructs a Questions object with the specified text, options, and correct answer.
     *
     * @param text          The text of the question.
     * @param options       The list of options for the question.
     * @param correctAnswer The correct answer to the question.
     */
    public Questions(String text, List<String> options, String correctAnswer) {
        this.textQuestion = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Returns the type of the question.
     *
     * @return The type of the question.
     */
    public String questionType() {
        return null; // To be overridden by subclasses
    }

    /**
     * Gets the text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestion() {
        return textQuestion;
    }

    /**
     * Gets the list of options for the question.
     *
     * @return The list of options.
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Gets the correct answer to the question.
     *
     * @return The correct answer.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Checks if the selected answer is correct for True/False questions.
     *
     * @param selectedAnswer The selected answer (True or False).
     * @return True if the answer is correct, otherwise false.
     */
    public boolean isTrueFalseCorrect(boolean selectedAnswer) {
        // Default implementation for non-True/False questions
        return selectedAnswer && correctAnswer.equalsIgnoreCase("True")
                || !selectedAnswer && correctAnswer.equalsIgnoreCase("False");
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Gets the list of answers for the question.
     *
     * @return The list of answers.
     */
    public abstract List<String> getAnswers();

    /**
     * Abstract method to be implemented by subclasses.
     * Checks if the provided answer is correct.
     *
     * @param answer The answer to check.
     * @return True if the answer is correct, otherwise false.
     */
    public abstract boolean isCorrectAnswer(String answer);

    /**
     * Abstract method to be implemented by subclasses.
     * Returns the points associated with the question.
     *
     * @return The points for the question.
     */
    public abstract int returnPoints();

    /**
     * Abstract method to be implemented by subclasses.
     * Gets a sublist of options based on specific rules for the question type.
     *
     * @return The sublist of options.
     */
    public abstract List<String> getSublistOptions();
}
