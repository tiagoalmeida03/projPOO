import java.util.List;

public class SoccerQ extends SportsQ {
    public SoccerQ(String text, List<String> options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    @Override
    public boolean isCorrect(String answer) {
        return answer.equals(correctAnswer);
    }
}