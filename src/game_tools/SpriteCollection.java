package game_tools;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

//Itamar Cohen 318897089

/**
 * This is a class that manages a collection of sprites. It has three methods: addSprite(), notifyAllTimePassed(),
 * and drawAllOn().
 * The addSprite() method adds a sprite to the collection.
 * The notifyAllTimePassed() method calls the timePassed() method on all sprites in the collection.
 * The drawAllOn() method calls the drawOn() method on all sprites in the collection, passing in a DrawSurface
 * object that represents the surface on which the sprites should be drawn.
 * Overall, this class is useful for managing a group of sprites that need to be updated and drawn on a screen.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notify all time passed. call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * Draw all on. call drawOn(d) on all sprites.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}