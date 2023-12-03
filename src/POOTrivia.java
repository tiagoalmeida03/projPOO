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

    private int questionsPerGame = 5;
    private int currentQuestionCount;
    private List<Integer> usedQuestionsIndices;

    public POOTrivia() {
        // ... (existing code)

        currentQuestionCount = 0;
        usedQuestionsIndices = new ArrayList<>();

        // Start a new game
        startNewGame();
    }

    private void loadQuestions() {
        questions = new ArrayList<>();

        try {
            File file = new File("teste.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                String questionText = parts[0];
                List<String> answers = Arrays.asList(parts[1].split(","));
                String correctAnswer = parts[2];

                Questions question = new ArtsQ(questionText, answers, correctAnswer); // Example using ArtsQ, adjust as needed
                questions.add(question);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadGameResults() {
        gameResults = new ArrayList<>();

        try {
            File file = new File("game_results.obj");
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                gameResults = (List<GameResult>) objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveGameResult(GameResult gameResult) {
        try {
            File file = new File("game_results.obj");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            gameResults.add(gameResult);
            objectOutputStream.writeObject(gameResults);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showLeaderboard() {
        // Retrieve the game results from the list
        List<GameResult> gameResults = getGameResults();

        // Sort the game results based on the scores
        Collections.sort(gameResults, Comparator.comparingInt(GameResult::getScore).reversed());

        // Display the leaderboard with player names and scores
        StringBuilder leaderboard = new StringBuilder();
        leaderboard.append("Leaderboard:\n");
        for (int i = 0; i < Math.min(gameResults.size(), 3); i++) {
            GameResult gameResult = gameResults.get(i);
            leaderboard.append(i + 1).append(". ").append(gameResult.getPlayer().getPlayerName()).append(": ").append(gameResult.getScore()).append("\n");
        }
        JOptionPane.showMessageDialog(null, leaderboard.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
    }

    private List<GameResult> getGameResults() {
        return gameResults;
    }

    private void startNewGame() {
        currentQuestionIndex = 0;
        currentGameScore = 0;
        currentQuestionCount = 0;
        usedQuestionsIndices.clear();

        Collections.shuffle(questions); // Shuffle questions for each new game
        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuestionCount < questionsPerGame) {
            int questionIndex = getNextRandomQuestionIndex();
            Questions question = questions.get(questionIndex);
            questionLabel.setText(question.getQuestion());

            List<String> answers;

            if (currentQuestionCount < 3 && question instanceof ArtsQ) {
                // Display a sublist with only 3 options for Arts questions before the 3rd question
                answers = ((ArtsQ) question).getSublistOptions();
            } else {
                // Display the full list of options for other questions
                answers = question.getAnswers();
            }

            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setText(answers.get(i));
            }

            currentQuestionCount++;
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
        showLeaderboard();
    }

    private int getNextRandomQuestionIndex() {
        int randomIndex;
        do {
            randomIndex = new Random().nextInt(questions.size());
        } while (usedQuestionsIndices.contains(randomIndex));

        usedQuestionsIndices.add(randomIndex);
        return randomIndex;
    }

    private class answerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String selectedAnswer = button.getText();
            handleAnswerSubmission(selectedAnswer);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("POO Trivia Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new POOTrivia());
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}