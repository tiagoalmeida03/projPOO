import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Player class represents a player in the POO Trivia game. It implements the Serializable
 * interface to allow object serialization for saving player information to files.
 * The class includes methods for managing player data, such as increasing the score,
 * adding correct and incorrect questions, creating object files, retrieving top players,
 * and obtaining the player's initials.
 *
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int score;
    private List<String> correctQuestions;
    private List<String> incorrectQuestions;

    /**
     * Constructs a Player object with the specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.correctQuestions = new ArrayList<>();
        this.incorrectQuestions = new ArrayList<>();
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getPlayerName() {
        return name;
    }

    /**
     * Gets the score of the player.
     *
     * @return The score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Increases the player's score by the specified points.
     *
     * @param points The points to increase the score by.
     */
    public void increaseScore(int points) {
        score += points;
    }

    /**
     * Adds a correct question to the player's record.
     *
     * @param question The correct question to add.
     */
    public void addCorrectQuestion(String question) {
        correctQuestions.add(question);
    }

    /**
     * Adds an incorrect question to the player's record.
     *
     * @param question The incorrect question to add.
     */
    public void addIncorrectQuestion(String question) {
        incorrectQuestions.add(question);
    }

    /**
     * Sets the list of correct questions for the player.
     *
     * @param correctQuestions The list of correct questions.
     */
    private void setCorrectQuestions(List<String> correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    /**
     * Sets the list of incorrect questions for the player.
     *
     * @param incorrectQuestions The list of incorrect questions.
     */
    private void setIncorrectQuestions(List<String> incorrectQuestions) {
        this.incorrectQuestions = incorrectQuestions;
    }

    /**
     * Gets the list of correct questions answered by the player.
     *
     * @return The list of correct questions.
     */
    public List<String> getCorrectQuestions() {
        return correctQuestions;
    }

    /**
     * Gets the list of incorrect questions answered by the player.
     *
     * @return The list of incorrect questions.
     */
    public List<String> getIncorrectQuestions() {
        return incorrectQuestions;
    }

    /**
     * Creates an object file to save the player's information.
     */
    public void createObjectFile() {
        try {
            // Generate a timestamp for the file name
            String dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            // Extract initials from the player's name
            String nameInitials = getInitials();
            // Construct the file name with timestamp and initials
            String fileName = "/Users/joaotomasquerido/Documents/LEI/3 ANO/POO/projPOO/ObjFolder/pootrivia_jogo_" + dateTime + "_" + nameInitials + ".dat";

            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

                // Write player information to the object file
                objectOutputStream.writeObject(this);
                objectOutputStream.writeObject(correctQuestions);
                objectOutputStream.writeObject(incorrectQuestions);

                System.out.println("Object file created: " + fileName);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file could not be found or created: " + e.getMessage());
        } catch (NotSerializableException e) {
            System.err.println("The Player class is not serializable: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the initials of the player based on their name.
     *
     * @return The initials of the player.
     */
    private String getInitials() {
        String[] nameParts = name.split("\\s+");
        StringBuilder initials = new StringBuilder();

        // Extract the first character of each name part
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }

        return initials.toString();
    }

    /**
     * Retrieves the top players based on their scores.
     *
     * @return The list of top players.
     */
    public static List<Player> getTopPlayers() {
        List<Player> players = new ArrayList<>();

        // Specify the directory where object files are located
        String directoryPath = "/Users/joaotomasquerido/Documents/LEI/3 ANO/POO/projPOO/ObjFolder";
        File directory = new File(directoryPath);

        // List all files in the directory
        File[] files = directory.listFiles();

        if (files != null) {
            // Deserialize Player objects from each object file
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".dat")) {
                    try (FileInputStream fileInputStream = new FileInputStream(file);
                         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                        Player player = (Player) objectInputStream.readObject();
                        player.setCorrectQuestions((List<String>) objectInputStream.readObject());
                        player.setIncorrectQuestions((List<String>) objectInputStream.readObject());
                        players.add(player);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // Sort players based on their scores in descending order
            players.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));

            // Display information of the top players
            System.out.println("Top Players:");
            for (int i = 0; i < Math.min(players.size(), 3); i++) {
                Player player = players.get(i);
                System.out.println("Player: " + player.getPlayerName() + ", Score: " + player.getScore());
            }

            return players;
        } else {
            System.out.println("No objects found in the directory...");
            return players;
        }
    }
}
