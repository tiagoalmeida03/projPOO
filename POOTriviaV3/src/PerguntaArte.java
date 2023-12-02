import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PerguntaArte extends Pergunta {

    public PerguntaArte(String texto, List<String> opcoesResposta, String solucaoCorreta, int pontuacaoBase) {
        super(texto, opcoesResposta, solucaoCorreta, pontuacaoBase);
    }

    @Override
    public String getArea() {
        return "Arte";
    }

    @Override
    public void imprimirPergunta() {
        super.imprimirPergunta();

        List<String> opcoesParaImprimir = new ArrayList<>(getOpcoesResposta());
        if (getNumPergunta() < 3) {
            // Se a pergunta for apresentada antes da 3ª pergunta, garantir que a resposta correta esteja presente
            opcoesParaImprimir = selecionarOpcoesRespostaFacil(opcoesParaImprimir, getSolucaoCorreta());
            System.out.println("Resposta Correta: " + getSolucaoCorreta());
        }

        System.out.println("Opções de Resposta: " + opcoesParaImprimir);
    }

    @Override
    public List<String> getOpcoesResposta() {
        return super.getOpcoesResposta();
    }

    private static List<String> selecionarOpcoesRespostaFacil(List<String> opcoesResposta, String solucaoCorreta) {
        // Criar uma lista com opções incorretas (diferentes da solução correta)
        List<String> opcoesIncorretas = new ArrayList<>(opcoesResposta);
        opcoesIncorretas.remove(solucaoCorreta);

        // Embaralhar a lista de opções incorretas
        Collections.shuffle(opcoesIncorretas);

        // Pegar as duas primeiras opções incorretas
        opcoesIncorretas = opcoesIncorretas.subList(0, Math.min(4, opcoesIncorretas.size()));

        // Criar um novo array com as opções incorretas e a resposta correta
        List<String> novoArray = new ArrayList<>(opcoesIncorretas);
        novoArray.add(solucaoCorreta);

        // Embaralhar novamente o novo array
        Collections.shuffle(novoArray);

        return novoArray;
    }
}
