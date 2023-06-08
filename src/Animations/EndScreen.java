package Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private int finishScore;
    private boolean status;
    private boolean stop;
    private KeyboardSensor keyboard;

    /**
     * Instantiates a new End screen.
     *
     * @param keyboard    the keyboard
     * @param status      the status
     * @param finishScore the finish score
     */
    public EndScreen(KeyboardSensor keyboard, boolean status, int finishScore) {
        this.keyboard = keyboard;
        this.status = status;
        this.finishScore = finishScore;
        this.stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.status) {
            d.drawText(165, 300, "You Win! Your score is " + this.finishScore, 35);
        } else {
            d.drawText(165, 300, "Game Over. Your score is " + this.finishScore, 35);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
