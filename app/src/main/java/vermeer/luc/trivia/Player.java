package vermeer.luc.trivia;

import java.io.Serializable;

public class Player implements Serializable {
    private int lives;
    private int score;

    public Player() {
        lives = 3;
        score = 0;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
