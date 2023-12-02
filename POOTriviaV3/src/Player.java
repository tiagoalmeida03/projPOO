import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Player implements Serializable {
    private static final long serialVersionUID = 1L;  // Adicione esta linha

    private String name;
    private int score;
    private String nameInitials;
    private List<String> perguntasCertas;
    private List<String> perguntasErradas;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.nameInitials = getNameInitials();
        this.perguntasCertas = new ArrayList<>();
        this.perguntasErradas = new ArrayList<>();
    }

    public List<String> getPerguntasCertas() {
        return perguntasCertas;
    }

    public void setPerguntasCertas(List<String> perguntasCertas) {
        this.perguntasCertas = perguntasCertas;
    }

    public List<String> getPerguntasErradas() {
        return perguntasErradas;
    }

    public void setPerguntasErradas(List<String> perguntasErradas) {
        this.perguntasErradas = perguntasErradas;
    }

    private String getNameInitials() {
        if (name != null && !name.isEmpty()) {
            String[] words = name.split(" "); // Split the name into words
            StringBuilder initials = new StringBuilder();

            for (String word : words) {
                if (!word.isEmpty()) {
                    initials.append(Character.toUpperCase(word.charAt(0)));
                }
            }

            return initials.toString();
        }
        return "Unknown";   // devolve "unknown" se o nome do jogador for vazio
    }


    public void createObjectFile() {
        try {
            String dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = "/Users/joaogabriel/IdeaProjects/POOTriviaV3/ObjectFilesFolder/pootrivia_jogo_" + dateTime + "_" + nameInitials + ".dat";

            // Create FileOutputStream and ObjectOutputStream
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

                // Write the Player object to the file
                objectOutputStream.writeObject(this);
                objectOutputStream.writeObject(perguntasCertas);
                objectOutputStream.writeObject(perguntasErradas);

                System.out.println("Object file created: " + fileName);     // Remover print no final
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addPerguntaCerta(String pergunta) {
        perguntasCertas.add(pergunta);
    }

    public void addPerguntaErrada(String pergunta) {
        perguntasErradas.add(pergunta);
    }
    public static List<Player> getTopPlayers() {
        List<Player> players = new ArrayList<>();   // Lista temporaria de jogadores. Carregada para a memoria

        // Specify the directory where object files are located
        String directoryPath = "/Users/joaogabriel/IdeaProjects/POOTriviaV3/ObjectFilesFolder";
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
                        players.add(player);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // Sort players based on their scores in descending order
            players.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
            for (Player player : players) {
                System.out.println(player.getName() + " - Score: " + player.getScore());
                System.out.println("Perguntas Certas:");
                for (String pergunta : player.getPerguntasCertas()) {
                    System.out.println("- " + pergunta);
                }
                System.out.println("Perguntas Erradas:");
                for (String pergunta : player.getPerguntasErradas()) {
                    System.out.println("- " + pergunta);
                }
                System.out.println();
            }
            return players;
        } else {
            System.out.println("No object files found in the specified directory.");
            return players;
        }
    }


    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    public void addToScore(int pontuacao) {
        score += pontuacao;
    }

    // Cada vez que o nome do jogador é alterado, também têm que ser alteradas as iniciais correspondentes
    public void setName(String name) { this.name = name; this.nameInitials = getNameInitials(); }

    public void resetScore() {
        this.score = 0;
    }
}

