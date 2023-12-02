import java.util.List;

public class PerguntaSki extends PerguntaDesporto {

    // Construtor
    public PerguntaSki(String texto, List<String> opcoesResposta, String solucaoCorreta, int pontuacaoBase) {
        super(texto, opcoesResposta, solucaoCorreta, pontuacaoBase);
    }


    @Override
    public String getArea() {
        return "Ski";
    }

    @Override
    public void imprimirPergunta() {
        super.imprimirPergunta();
        System.out.println("Opcoes de Resposta" + getOpcoesResposta());
    }
}