package game_tools;

import game_listeners.HitListener;
import game_parts.Ball;
import game_parts.Block;

//Itamar Cohen 318897089

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel the game
     * @param c    the c
     */
    public BallRemover(GameLevel gameLevel, Counter c) {
        this.gameLevel = gameLevel;
        this.remainingBalls = c;
    }
    /**
     Blocks that are hit should be removed
     from the game. Remember to remove this listener from the block
     that is being removed from the game.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeSprite(hitter);
        this.remainingBalls.decrease(1);

    }
}