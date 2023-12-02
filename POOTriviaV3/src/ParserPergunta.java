import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParserPergunta {

    // Método para ler perguntas de um arquivo e retornar uma lista de instâncias de Pergunta
    public static List<Pergunta> lerPerguntasDoArquivo(String nomeArquivo) {
        List<Pergunta> perguntas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                Pergunta pergunta = parseLinhaPergunta(linha);
                if (pergunta != null) {
                    perguntas.add(pergunta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       Collections.shuffle(perguntas);

        return perguntas;
    }

    private static Pergunta parseLinhaPergunta(String linha) {
        String[] partes = linha.split(";");
        if (partes.length >= 5) {
            String area = partes[0];
            if (area.equalsIgnoreCase("Ciencia")) {
                return parsePerguntaCiencias(partes);
            } else if (area.equalsIgnoreCase("Arte")) {
                return parsePerguntaArte(partes);
            } else if (area.equalsIgnoreCase("Ski")) {
                return parsePerguntaSki(partes);
            } else if (area.equalsIgnoreCase("Natacao")) {
                return parsePerguntaNatacao(partes);
            } else if (area.equalsIgnoreCase("Futebol")) {
                return parsePerguntaFutebol(partes);
            }
            // Adicione outras verificações para diferentes tipos de perguntas aqui, se necessário
        }
        return null;
    }

    private static PerguntaDesporto parsePerguntaSki(String[] partes) {
        String texto = partes[1];
        String solucaoCorreta = partes[partes.length - 2];
        int pontuacaoBase = Integer.parseInt(partes[partes.length - 1]);

        // Verifique se a quantidade mínima de opções é atendida
        List<String> opcoesResposta = parseOpcoes(partes, 2, partes.length - 3);

        return new PerguntaSki(texto, opcoesResposta, solucaoCorreta, pontuacaoBase);
    }

    private static PerguntaDesporto parsePerguntaNatacao(String[] partes) {
        String texto = partes[1];
        String solucaoCorreta = partes[partes.length - 2];
        int pontuacaoBase = Integer.parseInt(partes[partes.length - 1]);

        // Verifique se a quantidade mínima de opções é atendida
        List<String> opcoesResposta = parseOpcoes(partes, 2, partes.length - 3);

        return new PerguntaNatacao(texto, opcoesResposta, solucaoCorreta, pontuacaoBase);
    }

    private static PerguntaFutebol parsePerguntaFutebol(String[] partes) {
        String texto = partes[1];

        // Encontrar a posição inicial das opções de nomes de jogadores e números de camisolas
        int inicioOpcoesNomes = 2;
        int fimOpcoesNomes = encontrarFimOpcoes(partes, inicioOpcoesNomes);

        int inicioOpcoesCamisolas = 3;
        int fimOpcoesCamisolas = encontrarFimOpcoes(partes, inicioOpcoesCamisolas);

        // Extrair as opções de nomes e camisolas
        List<String> opcoesNomesJogadores = Arrays.asList(partes[inicioOpcoesNomes].substring(1, partes[inicioOpcoesNomes].length() - 1).split(", "));
        List<String> opcoesNumerosCamisolas = Arrays.asList(partes[inicioOpcoesCamisolas].substring(1, partes[inicioOpcoesCamisolas].length() - 1).split(", "));

        // Extrair as soluções corretas

        String solucaoNomeJogador = partes[fimOpcoesCamisolas + 2];
        String solucaoNumeroCamisola = partes[partes.length -2 ];

        // Extrair pontuação
        int pontuacaoBase = Integer.parseInt(partes[partes.length - 1]);

        return new PerguntaFutebol(texto, opcoesNomesJogadores, opcoesNumerosCamisolas,
                solucaoNomeJogador, solucaoNumeroCamisola, pontuacaoBase);
    }


    // Método auxiliar para encontrar a posição final das opções
    private static int encontrarFimOpcoes(String[] partes, int inicio) {
        int i = inicio;
        while (i < partes.length && !partes[i].startsWith("[")) {
            i++;
        }
        return i - 1;
    }


    private static PerguntaArte parsePerguntaArte(String[] partes) {
        String texto = partes[1];
        List<String> opcoesResposta = parseOpcoes(partes, 2, partes.length - 3);
        String solucaoCorreta = partes[partes.length - 2];
        int pontuacaoBase = Integer.parseInt(partes[partes.length - 1]);

        return new PerguntaArte(texto, opcoesResposta, solucaoCorreta, pontuacaoBase);
    }

    private static PerguntaCiencia parsePerguntaCiencias(String[] partes) {
        String texto = partes[1];
        List<String> opcoesResposta = parseOpcoes(partes, 2, partes.length - 3);
        String solucaoCorreta = partes[partes.length - 2];
        int pontuacaoBase = Integer.parseInt(partes[partes.length - 1]);

        return new PerguntaCiencia(texto, opcoesResposta, solucaoCorreta, pontuacaoBase);
    }

    private static List<String> parseOpcoes(String[] partes, int inicio, int fim) {
        List<String> opcoes = new ArrayList<>();
        for (int i = inicio; i <= fim; i++) {
            opcoes.add(partes[i]);
        }
        return opcoes;
    }
}
