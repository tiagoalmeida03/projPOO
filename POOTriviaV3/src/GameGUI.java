import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameGUI extends JFrame {
    private List<Pergunta> questions;
    private Player player;
    private int numPergunta;
    private JLabel perguntaLabel;
    private List<JButton> botoesResposta;
    private JPanel buttonPanel;

    public GameGUI(List<Pergunta> questions, Player player) {
        this.questions = questions;
        this.player = player;
        this.numPergunta = 0;

        showMainMenu();
    }
    private void showMainMenu() {
        JPanel mainMenuPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton novoJogoButton = new JButton("Novo Jogo");
        JButton top3Button = new JButton("Top 3");
        JButton sairButton = new JButton("Sair");

        novoJogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        top3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTopPlayers();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        mainMenuPanel.add(novoJogoButton);
        mainMenuPanel.add(top3Button);
        mainMenuPanel.add(sairButton);

        add(mainMenuPanel);
        setTitle("POOTRIVIA - Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centralizar na tela
        setVisible(true);
    }

    private void startNewGame() {
        removeMainMenu(); // Remover o menu principal antes de iniciar o jogo
        initializeUI(); // Adicionar a inicialização da interface do jogo
        goToNextQuestion();
    }

    private void showTopPlayers() {
        List<Player> topPlayers = Player.getTopPlayers();

        StringBuilder topPlayersInfo = new StringBuilder("Top 3 Players:\n");

        int count = Math.min(3, topPlayers.size());
        for (int i = 0; i < count; i++) {
            Player player = topPlayers.get(i);
            topPlayersInfo.append((i + 1) + ". " + player.getName() + " - Score: " + player.getScore() + "\n");
            topPlayersInfo.append("Perguntas Certas:\n");
            for (String pergunta : player.getPerguntasCertas()) {
                topPlayersInfo.append("- " + pergunta + "\n");
            }
            topPlayersInfo.append("Perguntas Erradas:\n");
            for (String pergunta : player.getPerguntasErradas()) {
                topPlayersInfo.append("- " + pergunta + "\n");
            }
            topPlayersInfo.append("\n");
        }

        JTextArea topPlayersTextArea = new JTextArea(topPlayersInfo.toString());
        topPlayersTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(topPlayersTextArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Top 3 Players", JOptionPane.PLAIN_MESSAGE);
    }

    private void closeWindow() {
        System.exit(0);
    }

    private void removeMainMenu() {
        getContentPane().removeAll();
        revalidate();
        repaint();
    }


    private void initializeUI() {
        setTitle("Jogo de Perguntas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        perguntaLabel = new JLabel();
        perguntaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        perguntaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(perguntaLabel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void goToNextQuestion() {
        if (numPergunta < 5) {
            Pergunta currentQuestion = questions.get(numPergunta);
            currentQuestion.setNumPergunta(numPergunta + 1);

            perguntaLabel.setText("Pergunta nº: " + currentQuestion.getNumPergunta() + "\n" + currentQuestion.getTexto());

            List<String> opcoesParaImprimir = new ArrayList<>(currentQuestion.getOpcoesResposta());
            if (currentQuestion.getNumPergunta() > 3) {
                Collections.shuffle(opcoesParaImprimir);
            } else {
                opcoesParaImprimir = selecionarOpcoesRespostaFacil(opcoesParaImprimir, currentQuestion.getSolucaoCorreta());
            }

            buttonPanel.removeAll();
            for (int i = 0; i < opcoesParaImprimir.size(); i++) {
                JButton button = new JButton(opcoesParaImprimir.get(i));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(e.getActionCommand());
                    }
                });
                buttonPanel.add(button);
            }

            revalidate();
            repaint();
        } else {
            endGame();
        }
    }

    private void handleButtonClick(String respostaUsuario) {
        Pergunta currentQuestion = questions.get(numPergunta);
        String isCorrect = currentQuestion.getSolucaoCorreta();

        if (isCorrect.equals(respostaUsuario)) {
            int pontuacaoFinal = currentQuestion.getMajoracao();
            JOptionPane.showMessageDialog(this, "Resposta correta! Pontuação Total: " + pontuacaoFinal);
            player.addToScore(pontuacaoFinal);
            player.addPerguntaCerta(currentQuestion.getTexto());
            System.out.println(currentQuestion.getTexto());
        } else {
            JOptionPane.showMessageDialog(this, "Resposta incorreta!");
            player.addPerguntaErrada(currentQuestion.getTexto());
            System.out.println(currentQuestion.getTexto());


        }

        numPergunta++;
        goToNextQuestion();
    }

    private void endGame() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Fim do jogo. Pontuação final: " + player.getScore());
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        JTextField playerNameField = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Digite o seu nome:"));
        inputPanel.add(playerNameField);
        panel.add(inputPanel, BorderLayout.SOUTH);

        int result = JOptionPane.showConfirmDialog(this, panel, "Fim do Jogo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String playerName = playerNameField.getText();
            player.setName(playerName);
            player.createObjectFile();

            int playAgainResult = JOptionPane.showConfirmDialog(this,
                    "Deseja jogar novamente?", "Novo Jogo", JOptionPane.YES_NO_OPTION);

            if (playAgainResult == JOptionPane.YES_OPTION) {
                numPergunta = 0;
                player.resetScore();
                goToNextQuestion();
            } else {
                dispose();
            }
        } else {
            dispose();
        }
    }

    private static List<String> selecionarOpcoesRespostaFacil(List<String> opcoesResposta, String solucaoCorreta) {
        List<String> opcoesIncorretas = new ArrayList<>(opcoesResposta);
        opcoesIncorretas.remove(solucaoCorreta);
        Collections.shuffle(opcoesIncorretas);
        opcoesIncorretas = opcoesIncorretas.subList(0, Math.min(4, opcoesIncorretas.size()));
        List<String> novoArray = new ArrayList<>(opcoesIncorretas);
        novoArray.add(solucaoCorreta);
        Collections.shuffle(novoArray);
        return novoArray;
    }
}