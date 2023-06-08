package game_tools;
import biuoop.DrawSurface;

//Itamar Cohen 318897089

/**
 * This is an interface named "Sprite" which defines the methods that should be implemented by any class that wants
 * to be a sprite in a game.
 * The first method, drawOn, takes a DrawSurface object and is responsible for drawing the sprite on the given surface.
 * The second method, timePassed, doesn't take any arguments and is responsible for notifying the sprite that time
 * has passed in the game, allowing it to update its state accordingly.
 * By implementing this interface, a class can be used as a sprite in a game that utilizes sprites, and it is
 * expected to have both drawOn and timePassed methods implemented.
 */
public interface Sprite {
    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    void timePassed();
}