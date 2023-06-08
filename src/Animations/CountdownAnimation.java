package Animations;

import biuoop.DrawSurface;
import game_tools.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private SpriteCollection gameScreen;
    private int currentCount;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.currentCount = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw the game screen
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText(385, 350, "" + this.currentCount, 60);

        // Calculate the current count based on the time passed
        if (this.numOfSeconds % 40 == 0) {
            this.currentCount--;
        }
        this.numOfSeconds--;
        if (this.currentCount == 0) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
