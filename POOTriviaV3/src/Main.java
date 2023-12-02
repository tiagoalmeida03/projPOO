import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Supondo que as classes estejam no mesmo pacote ou importadas corretamente
        List<Pergunta> perguntas = ParserPergunta.lerPerguntasDoArquivo("/Users/joaogabriel/IdeaProjects/POOTriviaV3/src/Teste.txt");

        // Instanciar o jogador
        Player player = new Player("",0);

        // Instanciar e configurar o jogo
        Game game = new Game(perguntas, player);

        // Iniciar o jogo
        game.startGame();

        // respostas de Futebol nao estao a bater certo



        // Exibir informações sobre as perguntas lidas
        /*
        for (Pergunta pergunta : perguntas) {
            pergunta.imprimirPergunta();
            System.out.println(); // Adiciona uma linha em branco entre as perguntas
        }
        */
    }
}
