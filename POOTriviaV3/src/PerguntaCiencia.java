import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Subclasse para perguntas de Ciências
public class PerguntaCiencia extends Pergunta {
    private List<String> opcoesRespostaFacil;
    private List<String> opcoesRespostaDificil;

    // Construtor
    public PerguntaCiencia(String texto, List<String> opcoesResposta, String solucaoCorreta, int pontuacaoBase) {
        super(texto, opcoesResposta, solucaoCorreta, pontuacaoBase);
        this.opcoesRespostaFacil = selecionarOpcoesRespostaFacil(opcoesResposta, solucaoCorreta);
        this.opcoesRespostaDificil = opcoesResposta;
    }

    @Override
    public String getArea() {
        return "Ciencia";
    }

    // Método para selecionar aleatoriamente as opções de resposta fáceis
    // Método para selecionar aleatoriamente as opções de resposta fáceis
    private static List<String> selecionarOpcoesRespostaFacil(List<String> opcoesResposta, String solucaoCorreta) {
        List<String> opcoesFacil = new ArrayList<>(opcoesResposta);
        opcoesFacil.remove(solucaoCorreta);

        // Embaralhar a lista
        Collections.shuffle(opcoesFacil);

        // Adicionar a solução correta de volta à lista se ainda não estiver presente
        if (!opcoesFacil.contains(solucaoCorreta)) {
            opcoesFacil.add(solucaoCorreta);
        }

        // Embaralhar novamente para garantir que a resposta correta não permaneça no índice 2
        Collections.shuffle(opcoesFacil);

        // Pegar as três primeiras opções (incluindo a solução correta)
        opcoesFacil = opcoesFacil.subList(0, Math.min(4, opcoesFacil.size()));

        return opcoesFacil;
    }


    // Implementação do método abstrato



//TODO logica para ter em conta o numero da pergunta para aumentar a dificuldade - ou seja mostrar opcoesRespostaDificil

    // Método para imprimir todas as opções de resposta
    @Override
    public void imprimirPergunta() {
        super.imprimirPergunta();

        if (getNumPergunta() < 3) {
            System.out.println("Opções Fáceis: " + opcoesRespostaFacil);
        } else {
            System.out.println("Opções Difíceis: " + opcoesRespostaDificil);
        }
    }

}
