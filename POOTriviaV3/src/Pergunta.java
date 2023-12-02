import java.util.List;

// Classe principal Pergunta
public abstract class Pergunta {
    private String texto;
    private List<String> opcoesResposta;
    private String solucaoCorreta;
    private int pontuacaoBase;
    private int numPergunta;
    // Construtor
    public Pergunta(String texto, List<String> opcoesResposta, String solucaoCorreta, int pontuacaoBase) {
        this.texto = texto;
        this.opcoesResposta = opcoesResposta;
        this.solucaoCorreta = solucaoCorreta;
        this.pontuacaoBase = pontuacaoBase;
        this.numPergunta = numPergunta;

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<String> getOpcoesResposta() {
        return opcoesResposta;
    }

    public void setOpcoesResposta(List<String> opcoesResposta) {
        this.opcoesResposta = opcoesResposta;
    }

    public String getSolucaoCorreta() {
        return solucaoCorreta;
    }

    public void setSolucaoCorreta(String solucaoCorreta) {
        this.solucaoCorreta = solucaoCorreta;
    }

    public int getPontuacaoBase() {
        return pontuacaoBase;
    }

    public void setNumPergunta(int numPergunta) {
        this.numPergunta = numPergunta;
    }

    public void setPontuacaoBase(int pontuacaoBase) {
        this.pontuacaoBase = pontuacaoBase;
    }

    // Método abstrato para obter o número da pergunta
    public int getNumPergunta() {
        return numPergunta;
    }
    public abstract String getArea();
   // public abstract String getSubArea();

// e ncessario passar do que esta aqui par cada uma das classes ? -> se sim temos que dar override dentro das classes
    // @Override getmajoracao reurtn getpontuacaoBase + 5

    public int getMajoracao() {
        String area = getArea();

        switch (area) {
            case "Arte":
                return pontuacaoBase * 10;
            case "Ciencia":
                return pontuacaoBase + 5;
            case "Futebol":

                return pontuacaoBase + 1 + 3 ;
            case "Natação":
                return pontuacaoBase + 3 + 10;
            case "Ski":
                return (pontuacaoBase+3) * 2;
        }

        return pontuacaoBase;  // Se não corresponder a nenhuma regra, retornar 0 de majoração
    }

    public List<String> getSolucao() {
        return null;
    }

    public void imprimirPergunta() {
        System.out.println("Pergunta nº:"+getNumPergunta()+"\n" + texto);
         System.out.println("Resposta Correta: " + solucaoCorreta);
        System.out.println("Pontos: " + pontuacaoBase);
    }
}
