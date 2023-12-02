import java.util.List;

public class PerguntaFutebol extends PerguntaDesporto {
    private List<String> opcoesNomesJogadores;
    private List<String> opcoesNumerosCamisolas;
    private String solucaoNomeJogador;
    private String solucaoNumeroCamisola;

    // Construtor
    public PerguntaFutebol(String texto, List<String> opcoesNomesJogadores, List<String> opcoesNumerosCamisolas,
                           String solucaoNomeJogador, String solucaoNumeroCamisola, int pontuacaoBase) {
        super(texto, List.of(), "", pontuacaoBase);  // Inicializa sem opções, solução vazia e pontuação base

        this.opcoesNomesJogadores = opcoesNomesJogadores;
        this.opcoesNumerosCamisolas = opcoesNumerosCamisolas;
        this.solucaoNomeJogador = solucaoNomeJogador;
        this.solucaoNumeroCamisola = solucaoNumeroCamisola;
    }

    @Override
    public List<String> getOpcoesResposta() {
        if (getNumPergunta() < 3) {
            return opcoesNomesJogadores;
        } else {
            return opcoesNumerosCamisolas;
        }
    }

    @Override
    public String getSolucaoCorreta() {
        if ( getNumPergunta() < 3 ){
            return solucaoNomeJogador;
        }else {
            return solucaoNumeroCamisola;
        }
    }

    // Implementação específica para Futebol
    public String getSolucaoNomeJogador() {
        return solucaoNomeJogador;
    }

    // Implementação específica para Futebol
    public String getSolucaoNumeroCamisola() {
        return solucaoNumeroCamisola;
    }

    // Implementação específica para Futebol
    @Override
    public void imprimirPergunta() {
        System.out.println("Pergunta nº: " + getNumPergunta() + getTexto());

        List<String> opcoesParaImprimir = getOpcoesResposta();
        System.out.println("Opções de Resposta: " + opcoesParaImprimir);

        if (getNumPergunta() < 3) {
            System.out.println("Resposta Correta: " + getSolucaoNomeJogador());
        } else {
            System.out.println("Resposta Correta: " + getSolucaoNumeroCamisola());
        }

        System.out.println("Pontos: " + getPontuacaoBase());
    }

    @Override
    public String getArea() {
        return "Futebol";
    }
}
