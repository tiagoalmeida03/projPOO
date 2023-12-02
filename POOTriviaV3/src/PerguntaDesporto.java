import java.util.List;

public abstract class PerguntaDesporto extends Pergunta {
    // Construtor para perguntas de Ski e Natacao
    public PerguntaDesporto(String texto, String solucaoCorreta, int pontuacaoBase) {
        super(texto, List.of("Verdadeiro", "Falso"), solucaoCorreta, pontuacaoBase);
    }

    // Construtor
    public PerguntaDesporto(String texto, List<String> opcoesResposta, String solucaoCorreta, int pontuacaoBase) {
        super(texto, opcoesResposta, solucaoCorreta, pontuacaoBase);
    }



    // Implementação específica para Desporto
    //@Override
    //public void imprimirOpcoesResposta() {
      //  System.out.println("Opções de Resposta: " + getOpcoesResposta());
    //}
}