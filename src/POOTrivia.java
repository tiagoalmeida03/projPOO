import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class POOTrivia extends JPanel {
    private List<Questions> questions;
    private List<GameResult> gameResults;
    private int currentQuestionIndex;
    private int currentGameScore;

    private JButton[] answerButtons;
    private JLabel questionLabel;
    private JLabel scoreLabel;

    public POOTrivia() {
        // Load questions from the file
        loadQuestions();

        // Start a new game
        //startNewGame();

        // Load previous game results
        //loadGameResults();

        // Initialize GUI components
        questionLabel = new JLabel();
        scoreLabel = new JLabel();
        answerButtons = new JButton[4];
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
    }

    private void loadQuestions() {
        List<List<Object>> rawQuestions = new ArrayList<>();
    
        try {
            File file = new File("pootrivia.txt");
            if (!file.exists()) {
                System.err.println("Error: File 'pootrivia.txt' not found.");
                return; // Exit the method if the file doesn't exist
            }
    
            Scanner scanner = new Scanner(file);

            System.out.println("Loading questions from 'pootrivia.txt'... " + scanner.hasNextLine());

            while (scanner.hasNextLine()) {
                String type = scanner.nextLine().trim(); // Read question type
                List<Object> questionData = new ArrayList<>();
                questionData.add(type);

    
                for (int i = 0; i < 5; i++) {
                    String question = scanner.nextLine().trim(); // Read question
                    questionData.add(question);
    
                    // Read options
                    List<String> options = new ArrayList<>();
                    for (int j = 0; j < 4; j++) {
                        String option = scanner.nextLine().trim();
                        options.add(option);
                    }
                    questionData.add(options);
    
                    // Read correct answer
                    String correctAnswer = scanner.nextLine().trim();
                    questionData.add(correctAnswer);
    
                    // Store correct answer as an option
                    options.add(correctAnswer);
                }
    
                rawQuestions.add(questionData);
            }
    
            scanner.close();
    
            // Convert raw data to Questions objects
            questions = new ArrayList<>();
            for (List<Object> questionData : rawQuestions) {
                String questionType = (String) questionData.get(0);
                for (int i = 1; i < questionData.size(); i += 5) {
                    String questionText = (String) questionData.get(i);
                    List<String> questionOptions = (List<String>) questionData.get(i + 1);
                    String correctAnswer = (String) questionData.get(i + 4);
    
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
    
            // Print the loaded questions
            for (Questions question : questions) {
                System.out.println(question);
            }
    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void startNewGame() {
        currentQuestionIndex = 0;
        currentGameScore = 0;
        Collections.shuffle(questions); // Shuffle questions for each new game
        displayNextQuestion();
    }
    
    private void loadGameResults() {
        gameResults = new ArrayList<>();
    
        try {
            File file = new File("game_results.obj");
    
            if (!file.exists()) {
                System.err.println("Error: File 'game_results.obj' not found.");
                return; // Exit the method if the file doesn't exist
            }
    
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = objectInputStream.readObject();
                if (obj instanceof List<?>) {
                    List<?> rawList = (List<?>) obj;

                    // Check if the elements in the list are of type GameResult
                    if (!rawList.isEmpty() && rawList.get(0) instanceof GameResult) {
                        List<GameResult> gameResults = new ArrayList<>();
                        for (Object element : rawList) {
                            gameResults.add((GameResult) element);
                        }
                        this.gameResults = gameResults;
                    } else {
                        System.err.println("Error: Unexpected element type in 'game_results.obj'.");
                    }
                } else {
                    System.err.println("Error: Unexpected object type in 'game_results.obj'.");
                }
            }
    
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void saveGameResult(GameResult gameResult) {
        try {
            File file = new File("game_results.obj");
    
            if (!file.exists()) {
                System.err.println("Error: File 'game_results.obj' not found.");
                return; // Exit the method if the file doesn't exist
            }
    
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = objectInputStream.readObject();
                if (obj instanceof List<?>) {
                    List<?> rawList = (List<?>) obj;

                    // Check if the elements in the list are of type GameResult
                    if (!rawList.isEmpty() && rawList.get(0) instanceof GameResult) {
                        List<GameResult> gameResults = new ArrayList<>();
                        for (Object element : rawList) {
                            gameResults.add((GameResult) element);
                        }
                        this.gameResults = gameResults;
                    } else {
                        System.err.println("Error: Unexpected element type in 'game_results.obj'.");
                    }
                } else {
                    System.err.println("Error: Unexpected object type in 'game_results.obj'.");
                }
            }
    
            // Add the new game result
            gameResults.add(gameResult);
    
            // Save the updated list of game results
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                objectOutputStream.writeObject(gameResults);
            }
    
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showLeaderboard(int numberOfTopScores) {
        // Retrieve the game results from the list
        List<GameResult> gameResults = getGameResults();
    
        // Sort the game results based on the scores
        Collections.sort(gameResults, Comparator.comparingInt(GameResult::getScore).reversed());
    
        // Display the leaderboard with player names and scores
        StringBuilder leaderboard = new StringBuilder();
        leaderboard.append("Leaderboard:\n");
        for (int i = 0; i < Math.min(gameResults.size(), numberOfTopScores); i++) {
            GameResult gameResult = gameResults.get(i);
            leaderboard.append(i + 1).append(". ").append(gameResult.getPlayer().getPlayerName()).append(": ").append(gameResult.getScore()).append("\n");
        }
        JOptionPane.showMessageDialog(null, leaderboard.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
    }
    

    private List<GameResult> getGameResults() {
        return gameResults;
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < 5) {
            Questions question = questions.get(currentQuestionIndex);
            questionLabel.setText(question.getQuestion());

            List<String> answers = question.getAnswers();
            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setText(answers.get(i));
            }

            currentQuestionIndex++;
        } else {
            endGame();
        }
    }

    private void handleAnswerSubmission(String selectedAnswer) {
        // Check if the selected answer is correct
        Questions currentQuestion = questions.get(currentQuestionIndex - 1);
        if (currentQuestion.isCorrectAnswer(selectedAnswer)) {
            // Increment the game score
            currentGameScore++;
        }

        // Display the next question or end the game
        if (currentQuestionIndex < questions.size()) {
            displayNextQuestion();
        } else {
            endGame();
        }
    }

    private void endGame() {
        // Show final score
        JOptionPane.showMessageDialog(this, "Game Over! Your final score is: " + currentGameScore);

        // Prompt for player's name
        String playerName = JOptionPane.showInputDialog(this, "Enter your name:");

        // Create a new game result
        GameResult gameResult = new GameResult(new Player(playerName), new ArrayList<>(), new ArrayList<>());

        // Save the game result
        saveGameResult(gameResult);

        // Show leaderboard
        showLeaderboard(3);
    }

    private class AnswerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String selectedAnswer = button.getText();
            handleAnswerSubmission(selectedAnswer);
        }
    }
}