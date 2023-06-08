package game_tools;

import biuoop.DrawSurface;

import java.awt.Color;

//Itamar Cohen 318897089

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private String levelname;

    /**
     * Instantiates a new Score indicator.
     *
     * @param c    the c
     * @param name the name
     */
    public ScoreIndicator(Counter c, String name) {
        this.score = c;
        this.levelname = name;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 17, "Score: " + score.getValue(), 15);
        d.drawText(600, 17, this.levelname, 15);
    }

    @Override
    public void timePassed() {
    }

//    public void addToGame(GameLevel gameLevel) {
//        this.addToGame(gameLevel);
//    }
}
