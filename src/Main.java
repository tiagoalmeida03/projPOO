/**
 * The Main class serves as the entry point for the POO Trivia application.
 * It creates a Swing GUI frame and initializes the application by
 * instantiating a POOTrivia object and displaying the initial panel.
 * 
 * @author Joao Querido 2021240660
 * @author Tiago Almeida 2021221615
 * @version 1.2
 */
import javax.swing.*;

public class Main {
    /**
     * The main method is the entry point for the POO Trivia application.
     * It sets up the Swing GUI by creating a frame, initializing the POOTrivia
     * object, and displaying the initial panel.
     * 
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main frame for the application
            JFrame mainFrame = new JFrame("POO Trivia");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(800, 800);

            // Create a POOTrivia instance
            POOTrivia pooTrivia = new POOTrivia();

            // Create a panel for the firstGUI
            JPanel firstGuiPanel = pooTrivia.firstPanel();

            // Add the firstGUI panel to the frame
            mainFrame.add(firstGuiPanel);

            // Set frame properties and make it visible
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
        });
    }
}
