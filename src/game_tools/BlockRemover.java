package game_tools;

import game_listeners.HitListener;
import game_parts.Ball;
import game_parts.Block;
//Itamar Cohen 318897089
/**
 * The type Block remover.
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel the game
     * @param c    the c
     */
    public BlockRemover(GameLevel gameLevel, Counter c) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = c;
    }
/**
    Blocks that are hit should be removed
    from the game. Remember to remove this listener from the block
    that is being removed from the game.
 */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeCollidable(beingHit);
        this.gameLevel.removeSprite(beingHit);
        this.remainingBlocks.decrease(1);
    }
}