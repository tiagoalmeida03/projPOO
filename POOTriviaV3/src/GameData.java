import java.io.Serializable;
import java.util.List;

public class GameData implements Serializable {
    private String playerName;
    private String dateTime;
    private int score;
    private List<Pergunta> perguntasCertas;
    private List<Pergunta> perguntasErradas;

    public GameData(String playerName, String dateTime, int score, List<Pergunta> perguntasCertas, List<Pergunta> perguntasErradas) {
        this.playerName = playerName;
        this.dateTime = dateTime;
        this.score = score;
        this.perguntasCertas = perguntasCertas;
        this.perguntasErradas = perguntasErradas;
    }

    // Getters e Setters (conforme necessário)
}
