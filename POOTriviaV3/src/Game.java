import java.util.List;

public class Game {
    private List<Pergunta> questions;
    private Player player;
    private int numPergunta;
    private GameGUI gameGUI;  // Adicionada a referência para a GUI

    public Game(List<Pergunta> questions, Player player) {
        this.questions = questions;
        this.player = player;
        this.numPergunta = 0;
        this.gameGUI = new GameGUI(questions, player);  // Inicializa a GUI

    }

    public void startGame() {
        System.out.println("Bem-vindo ao jogo! Vamos começar.");
        goToNextQuestion();
    }

    private void goToNextQuestion() {
        if (numPergunta < 5) {
            Pergunta currentQuestion = questions.get(numPergunta);
            currentQuestion.setNumPergunta(numPergunta + 1);

            numPergunta++;
        } else {
            endGame();
        }
    }

    public void handleAnswer(String respostaUsuario) {
        Pergunta currentQuestion = questions.get(numPergunta - 1);
        String isCorrect = currentQuestion.getSolucaoCorreta();

        if (isCorrect.equals(respostaUsuario)) {
            int pontuacaoFinal = currentQuestion.getMajoracao();
            System.out.println("Resposta correta!\nPontuação final: " + pontuacaoFinal);
            player.addToScore(pontuacaoFinal);
        } else {
            System.out.println("Resposta incorreta!");
        }

        goToNextQuestion();
    }

    private void endGame() {
        System.out.println("\nFim do jogo. Pontuação final: " + player.getScore());
         // Adicione mais lógica de fim de jogo conforme necessário
    }
}
