    import java.util.List;

    public class PerguntaNatacao extends PerguntaDesporto {

        // Construtor
        public PerguntaNatacao(String texto, List<String> opcoesResposta, String solucaoCorreta, int pontuacaoBase) {
            super(texto, opcoesResposta, solucaoCorreta, pontuacaoBase);
        }

        @Override
        public String getArea() {
            return "Natacao";
        }

    }