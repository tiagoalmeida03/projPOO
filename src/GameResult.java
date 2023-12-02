import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GameResult implements Comparable<GameResult> {
    private String dateTime;
    private Player player;
    private List<String> correctAnswers;
    private List<String> incorrectAnswers;

    public GameResult(Player player, List<String> correctAnswers, List<String> incorrectAnswers) {
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        this.player = player;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Player getPlayer() {
        return player;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    @Override
    public int compareTo(GameResult other) {
        return Integer.compare(other.getPlayer().getScore(), this.getPlayer().getScore());
    }
}
