public class Player {
    private String name;
    private int score;

    public Player() {
        this.name = "";
        this.score = 0;
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        score += points;
    }
}
