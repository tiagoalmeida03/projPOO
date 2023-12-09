// Main.java
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("POO Trivia");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(800, 800);

            // Create a POOTrivia instance
            POOTrivia pooTrivia = new POOTrivia();

            // Create a panel for the firstGUI
            JPanel firstGuiPanel = pooTrivia.firstPanel();

            // Add the firstGUI panel to the frame
            mainFrame.add(firstGuiPanel);

            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
        });
    }
}
