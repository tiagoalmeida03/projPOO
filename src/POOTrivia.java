import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/**
 * The POOTrivia class represents the main game panel for the POO Trivia game.
 * It extends JPanel and provides the graphical user interface for the game.
 * The game involves answering various types of questions.
 *
 */

public class POOTrivia extends JPanel {
    // Instance variables for managing game state
    private List<Questions> questions;
    private int currentQuestionIndex;
    private int currentGameScore;

    // GUI components
    private JButton[] answerButtons;
    private JLabel questionLabel;
    private JLabel scoreLabel;
    private JButton trueButton;
    private JButton falseButton;

    /**
     * Constructs a new instance of the POOTrivia class.
     * Initializes the GUI components and adds action listeners.
     */
    public POOTrivia() {
        // Display the first panel
        firstPanel();

        trueButton = new JButton("True");
        falseButton = new JButton("False");

        trueButton.addActionListener(new TrueFalseButtonListener(true));
        falseButton.addActionListener(new TrueFalseButtonListener(false));
    }

    /**
     * Creates and returns the first panel displayed when the game starts.
     * This panel includes a welcome message and start/cancel buttons.
     *
     * @return The first panel for the game.
     */
    public JPanel firstPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "<html><div style='text-align: center;'>Welcome to POOTrivia!!!<br>Please press the button to Start or Cancel the Game</div></html>");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(20f)); // Increase the font size
        panel.add(welcomeLabel, BorderLayout.CENTER); // Add the label to the center of the panel

        JButton startButton = new JButton("Start");
        startButton.setForeground(Color.GREEN);
        startButton.setPreferredSize(new Dimension(80, 30));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your logic for the Start button here
                System.out.println("Game Started!");

                // Create a new instance of POOTrivia
                POOTrivia.this.startComponents();
                POOTrivia.this.loadQuestions();
                POOTrivia.this.startNewGame();

                // Set the POOTrivia panel visible
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                frame.getContentPane().removeAll();
                frame.add(POOTrivia.this);
                frame.revalidate();
                frame.repaint();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(80, 30)); // Set button size
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your logic for the Cancel button here
                System.out.println("Game Canceled.");
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
        buttonPanel.add(cancelButton);
        buttonPanel.add(startButton);

        panel.add(buttonPanel, BorderLayout.SOUTH); // Align buttons to the bottom

        return panel;
    }

    /**
     * Initializes the GUI components used in the game.
     * Called when the game starts to set up the initial state.
     */
    private void startComponents() {
        questionLabel = new JLabel();
        scoreLabel = new JLabel();
        answerButtons = new JButton[5];
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new JButton();
            answerButtons[i].addActionListener(new AnswerButtonListener());
        }

        // Add GUI components to the panel
        setLayout(new GridLayout(6, 1));
        add(questionLabel);
        for (JButton button : answerButtons) {
            add(button);
        }
        add(scoreLabel);

        // Add True/False buttons to the panel
        JPanel trueFalsePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        trueFalsePanel.add(trueButton);
        trueFalsePanel.add(falseButton);
        add(trueFalsePanel);
    }

    /**
     * ActionListener implementation for True/False buttons.
     * Handles True/False button clicks and updates the game state accordingly.
     */
    private class TrueFalseButtonListener implements ActionListener {
        private boolean isTrue;

        public TrueFalseButtonListener(boolean isTrue) {
            this.isTrue = isTrue;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle the True/False button click
            handleTrueFalseButtonClick(isTrue);
        }
    }

    /**
     * Loads questions from the "pootrivia.txt" file and initializes the questions list.
     */
    private void loadQuestions() {
        List<List<Object>> rawQuestions = new ArrayList<>();

        // Code for loading questions from file
        try {
            File file = new File("pootrivia.txt");
            if (!file.exists()) {
                System.err.println("Error: File 'pootrivia.txt' not found.");
                return; // Exit the method if the file doesn't exist
            }

            Scanner scanner = new Scanner(file);

            System.out.println("Loading questions from 'pootrivia.txt'... ");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.startsWith("#")) {
                    String type = line.substring(1, line.length() - 1).trim();

                    List<Object> questionData = new ArrayList<>();
                    questionData.add(type);
                    rawQuestions.add(questionData);

                } else if (line.startsWith("-")) {
                    // It's a question
                    String question = line.substring(1).trim();
                    rawQuestions.get(rawQuestions.size() - 1).add(question);

                    // Read options
                    List<String> options = new ArrayList<>();
                    for (int j = 0; j < 5; j++) {
                        String option = scanner.nextLine().trim();
                        if (option.substring(0, 1).equalsIgnoreCase("*")) {
                            String correctAnswer = option.substring(1);
                            rawQuestions.get(rawQuestions.size() - 1).add(correctAnswer);
                            options.add(correctAnswer);
                        } else {
                            options.add(option);
                        }
                    }
                    rawQuestions.get(rawQuestions.size() - 1).add(options);
                }
            }

            scanner.close();

            // Convert raw data to Questions objects
            questions = new ArrayList<>();
            for (List<Object> questionData : rawQuestions) {
                String questionType = (String) questionData.get(0);
                for (int i = 1; i < questionData.size(); i += 3) {
                    String questionText = (String) questionData.get(i);
                    List<String> questionOptions = (List<String>) questionData.get(i + 2);
                    String correctAnswer = (String) questionData.get(i + 1);

                    // Create the appropriate Questions subclass based on type
                    Questions question;
                    if (questionType.startsWith("Arts")) {
                        question = new ArtsQ(questionText, questionOptions, correctAnswer);

                    } else if (questionType.startsWith("Science")) {
                        question = new ScienceQ(questionText, questionOptions, correctAnswer);
                    } else if (questionType.startsWith("Soccer")) {
                        question = new SoccerQ(questionText, questionOptions, correctAnswer);
                    } else if (questionType.startsWith("Ski")) {
                        question = new SkiQ(questionText, questionOptions, correctAnswer);
                    } else if (questionType.startsWith("Swimming")) {
                        question = new SwimmingQ(questionText, questionOptions, correctAnswer);
                    } else {
                        System.err.println("Error: Unknown question type.");
                        continue; // Skip this question
                    }

                    questions.add(question);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a new game by resetting the game state and displaying the first question.
     */
    private void startNewGame() {
        currentQuestionIndex = 0;
        currentGameScore = 0;
        Collections.shuffle(questions); // Shuffle questions for each new game
        displayNextQuestion();
    }

    /**
     * Displays the next question in the game.
     * Updates the GUI components to show the current question and answer options.
     */
    private void displayNextQuestion() {
        if (currentQuestionIndex < 5) {
            Questions question = questions.get(currentQuestionIndex);
    
            // Clear existing components
            removeAll();
    
            // Display the question text centered with a larger font and line wrap
            String questionText = "<html><div style='text-align: center; width: 400px;'>" + question.getQuestion() + "</div></html>";
            JLabel questionTextLabel = new JLabel(questionText);
            questionTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
            questionTextLabel.setFont(questionTextLabel.getFont().deriveFont(18f)); // Set a larger font size
            add(questionTextLabel);
    
            List<String> answers = question.getAnswers();
    
            if (!(question.questionType().equals("Ski") || question.questionType().equals("Swimming") || question.questionType().equals("Soccer"))) {
                // For answer submission questions, display only three options
                // Shuffle the options
                Collections.shuffle(answers);

                if (currentQuestionIndex < 3) {
                    // If it's one of the first three questions, display only three options
                    for (int i = 0; i < 3; i++) {
                        answerButtons[i].setText(answers.get(i));
                        answerButtons[i].setFont(answerButtons[i].getFont().deriveFont(18f)); // Set a larger font size
                        add(answerButtons[i]);
                    }

                    if (answers.get(0) != question.getCorrectAnswer() && answers.get(1) != question.getCorrectAnswer() && answers.get(2) != question.getCorrectAnswer()){
                        Random random = new Random();
                        int randomIndex = random.nextInt(3);
                        answerButtons[randomIndex].setText(question.getCorrectAnswer());
                        answerButtons[randomIndex].setFont(answerButtons[randomIndex].getFont().deriveFont(18f)); // Set a larger font size
                        add(answerButtons[randomIndex]);
                    }

                } else {
                    // If it's the fourth or fifth question, display all five options
                    for (int i = 0; i < 5; i++) {
                        answerButtons[i].setText(answers.get(i));
                        answerButtons[i].setFont(answerButtons[i].getFont().deriveFont(18f)); // Set a larger font size
                        add(answerButtons[i]);
                    }
                }
            } else if (question.questionType().equals("Soccer")) {
                // For SoccerQ questions, display the correct answer as one of the options
                Collections.shuffle(answers);
                
                if (currentQuestionIndex < 3){
                    // If it's one of the first three questions, display only three options
                    for (int i = 0; i < 3; i++) {
                        answerButtons[i].setText(getNameFromSoccerOption(answers.get(i)));
                        answerButtons[i].setFont(answerButtons[i].getFont().deriveFont(18f)); // Set a larger font size
                        add(answerButtons[i]);
                    }

                    if (answers.get(0) != question.getCorrectAnswer() && answers.get(1) != question.getCorrectAnswer() && answers.get(2) != question.getCorrectAnswer()){
                        Random random = new Random();
                        int randomIndex = random.nextInt(3);
                        answerButtons[randomIndex].setText(getNameFromSoccerOption(question.getCorrectAnswer()));
                        answerButtons[randomIndex].setFont(answerButtons[randomIndex].getFont().deriveFont(18f)); // Set a larger font size
                        add(answerButtons[randomIndex]);
                    }

                } else {
                    // If it's the fourth or fifth question, display all five options
                    for (int i = 0; i < 5; i++) {
                        answerButtons[i].setText(getNumShirtFromSoccerOption(answers.get(i)));
                        answerButtons[i].setFont(answerButtons[i].getFont().deriveFont(18f)); // Set a larger font size
                        add(answerButtons[i]);
                    }
                }
            } else {
                // For Ski and Swimming questions, display the selected option without buttons
                Collections.shuffle(answers);
                String selectedOption = question.getCorrectAnswer(); // Display the correct answer
                JLabel selectedOptionLabel = new JLabel(selectedOption);
                selectedOptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
                selectedOptionLabel.setFont(selectedOptionLabel.getFont().deriveFont(18f)); // Set a larger font size
                add(selectedOptionLabel);
            }
    
            // Add True/False buttons for Ski and Swimming questions
            if (question.questionType().equals("Ski") || question.questionType().equals("Swimming")) {
                JPanel trueFalsePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                trueFalsePanel.add(trueButton);
                trueFalsePanel.add(falseButton);
                add(trueFalsePanel);
            }
    
            currentQuestionIndex++;
            revalidate();
            repaint();
        } else {
            endGame();
        }
    }

    /**
     * Extracts and returns the name from a Soccer option.
     *
     * @param soccerOption The Soccer option containing both the name and the shirt number.
     * @return The name extracted from the Soccer option.
     */
    private String getNameFromSoccerOption(String soccerOption) {
        // Split the Soccer option into parts using space as the delimiter
        String[] parts = soccerOption.split(" ");
        
        // Exclude the last part (which represents the shirt number)
        String[] allExceptLast = Arrays.copyOfRange(parts, 0, parts.length - 1);
        
        // Join the parts back together to get the name
        return String.join(" ", allExceptLast);
    }

    /**
     * Extracts and returns the shirt number from a Soccer option.
     *
     * @param soccerOption The Soccer option containing both the name and the shirt number.
     * @return The shirt number extracted from the Soccer option.
     */
    private String getNumShirtFromSoccerOption(String soccerOption) {
        // Split the Soccer option into parts using space as the delimiter
        String[] parts = soccerOption.split(" ");
        
        // Return the last part, which represents the shirt number
        return parts[parts.length - 1];
    }


    /**
     * Handles True/False button clicks and updates the game state.
     *
     * @param selectedAnswer The selected True or False answer.
     */
    private void handleTrueFalseButtonClick(boolean selectedAnswer) {
        // Check if the selected answer is correct for True/False questions
        Questions currentQuestion = questions.get(currentQuestionIndex - 1);
        boolean isCorrectAnswer = currentQuestion.isTrueFalseCorrect(selectedAnswer);
        Player player = new Player("temp");

        if (isCorrectAnswer) {
            player.addCorrectQuestion(questionLabel.getText());
            String questionType = currentQuestion.toString();
            questionType = questionType.substring(0, questionType.indexOf("@"));
            if (questionType.equalsIgnoreCase("skiq")){
                currentGameScore += currentQuestion.returnPoints();
            } else if (questionType.equalsIgnoreCase("swimmingq")){
                currentGameScore += currentQuestion.returnPoints();
            }
        }

        else if(!isCorrectAnswer) {
            player.addCorrectQuestion(questionLabel.getText());
        }

        // Display the next question or end the game
        if (currentQuestionIndex < questions.size()) {
            displayNextQuestion();
        } else {
            endGame();
        }
    }

    /**
     * Handles answer submissions and updates the game state.
     *
     * @param selectedAnswer The selected answer submitted by the player.
     */
    private void handleAnswerSubmission(String selectedAnswer) {
        // Check if the selected answer is correct
        Questions currentQuestion = questions.get(currentQuestionIndex - 1);
        String questionType = currentQuestion.toString();
        questionType = questionType.substring(0, questionType.indexOf("@"));

        if (currentQuestion.isCorrectAnswer(selectedAnswer)) {
            if (questionType.equalsIgnoreCase("soccerq")){
                currentGameScore += currentQuestion.returnPoints();
            } else if (questionType.equalsIgnoreCase("artsq")) {
                currentGameScore += currentQuestion.returnPoints();
            } else if (questionType.equalsIgnoreCase("scienceq")){
                currentGameScore += currentQuestion.returnPoints();
            }
        }

        // Display the next question or end the game
        if (currentQuestionIndex < questions.size()) {
            displayNextQuestion();
        } else {
            endGame();
        }
    }

    /**
     * Ends the game, displays the final score, and prompts for the player's name.
     */
    private void endGame() {
        // Dispose the main panel
        SwingUtilities.getWindowAncestor(this).dispose();
    
        // Show final score
        JOptionPane.showMessageDialog(this, "Game Over! Your final score is: " + currentGameScore);
    
        // Prompt for player's name
        String playerName = JOptionPane.showInputDialog(this, "Enter your name:");
    
        // Check if playerName is null, meaning the Cancel button was pressed
        if (playerName == null) {
            // Ask for confirmation before exiting
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the game?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                // If the player chooses not to exit, show the name input panel again
                endGame();
            }
        } else {
            // Continue with the normal flow
            Player player = new Player(playerName);
    
            // Save the correct and incorrect questions
            for (Questions question : questions) {
                if (question.isCorrectAnswer(playerName)) {
                    player.addCorrectQuestion(question.getQuestion());
                } else {
                    player.addIncorrectQuestion(question.getQuestion());
                }
            }
    
            // Call createObjectFile to save player's information to the object file
            player.createObjectFile();

            // Show the top 3 players
            leaderBoard();

            // Exit the game
            System.exit(0);
        }
    }
    
    /**
     * Displays the top 3 players and their scores, along with their correct and incorrect answers.
     */
    public void leaderBoard() {
        // Obtém a lista dos melhores jogadores
        List<Player> topPlayers = Player.getTopPlayers();

        StringBuilder topPlayersInfo = new StringBuilder("Top 3 Players:\n");
        // Max 3 Jogadores
        int count = Math.min(3, topPlayers.size());

        for (int i = 0; i < count; i++) {
            // Jogador atual
            Player player = topPlayers.get(i);
            // Info sobre Jogador - nome, socre
            topPlayersInfo.append((i + 1) + ". " + player.getPlayerName() + " - Score: " + player.getScore() + "\n");
            // Perguntas
            topPlayersInfo.append("Right Answers:\n");
            for (String pergunta : player.getCorrectQuestions()) {
                topPlayersInfo.append("- " + pergunta + "\n");
            }
            topPlayersInfo.append("Wrong Answers:\n");
            for (String pergunta : player.getIncorrectQuestions()) {
                topPlayersInfo.append("- " + pergunta + "\n");
            }
            topPlayersInfo.append("\n");
        }
        // Cria caixa de texto com a informacao a mostrar
        JTextArea topPlayersTextArea = new JTextArea(topPlayersInfo.toString());
        topPlayersTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(topPlayersTextArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Top 3 Players", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * ActionListener implementation for answer buttons.
     * Handles answer button clicks and updates the game state accordingly.
     */
    private class AnswerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String selectedAnswer = button.getText();
            handleAnswerSubmission(selectedAnswer);
        }
    }
}