import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int score;
    private List<String> correctQuestions;
    private List<String> incorrectQuestions;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.correctQuestions = new ArrayList<>();
        this.incorrectQuestions = new ArrayList<>();
    }

    public String getPlayerName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        score += points;
    }

    public void addCorrectQuestion(String question) {
        correctQuestions.add(question);
    }

    public void addIncorrectQuestion(String question) {
        incorrectQuestions.add(question);
    }

    private void setCorrectQuestions(List<String> correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    private void setIncorrectQuestions(List<String> incorrectQuestions) {
        this.incorrectQuestions = incorrectQuestions;
    }

    public List<String> getCorrectQuestions() {
        return correctQuestions;
    }

    public List<String> getIncorrectQuestions() {
        return incorrectQuestions;
    }

    public void createObjectFile() {
        try {
            String dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String nameInitials = getInitials();
            String fileName = "/Users/tiagoalmeida/Library/CloudStorage/OneDrive-UniversidadedeCoimbra/UC/3º ANO/1º Semestre/POAO/projPOO/ObjFolder/pootrivia_jogo_" + dateTime + "_" + nameInitials + ".dat";

            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

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

    private String getInitials() {
        String[] nameParts = name.split("\\s+");
        StringBuilder initials = new StringBuilder();

        for (String part : nameParts) {
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }

        return initials.toString();
    }

    public static List<Player> getTopPlayers() {
        List<Player> players = new ArrayList<>();
    
        // Specify the directory where object files are located
        String directoryPath = "/Users/tiagoalmeida/Library/CloudStorage/OneDrive-UniversidadedeCoimbra/UC/3º ANO/1º Semestre/POAO/projPOO/ObjFolder";
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
