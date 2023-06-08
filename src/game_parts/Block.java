package game_parts;

import biuoop.DrawSurface;
import game_tools.Collidable;
import game_listeners.HitListener;
import game_listeners.HitNotifier;
import game_tools.Sprite;
import game_tools.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//Itamar Cohen 318897089

/**
 * The Block class represents a rectangular block in a game, which can be both a Collidable and a Sprite.
 * It has properties such as a Rectangle that defines its shape, a Color that defines its appearance, and methods
 * to draw it on a given DrawSurface, as well as to check for collisions with other objects.
 * The Block class is a fundamental building block, as it can be used to create obstacles, walls,
 * platforms, and more.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The constant Comparison_threshold.
     */
    static final double THRESHOLD = 0.00001;
    private Rectangle rec;
    private final Color color;
    private final Point upperLeft;
    private final double width;
    private final double height;
    private ArrayList<HitListener> hitListeners;

    /**
     * Instantiates a new Block.
     *
     * @param rec   the rec
     * @param color the color
     */
    public Block(Rectangle rec, Color color) {
        this.rec = rec;
        this.color = color;
        this.upperLeft = this.rec.getUpperLeft();
        this.width = this.rec.getWidth();
        this.height = this.rec.getHeight();
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param point  the point
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Block(Point point, double width, double height, Color color) {
        this.upperLeft = point;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rec = getRec();
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Double equals boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < THRESHOLD;
    }

    /**
     * Get color of Block.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(),
                (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(),
                (int) this.rec.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rec.getUpperLeft().getX(),
                (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(),
                (int) this.rec.getHeight());
    }

    /**
     * Time passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.upperLeft, this.width, this.height);
    }

    /**
     * Get rec rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRec() {
        Rectangle rec = new Rectangle(this.upperLeft, this.width, this.height);
        return rec;
    }

    // Implement the HitNotifier methods
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify hit.
     *
     * @param hitter the hitter
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener listener : listeners) {
            listener.hitEvent(this, hitter);
        }
    }

    /**
     * Hit velocity.
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the ball that hit
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (doubleEquals(collisionPoint.getY(), this.rec.getUpperLeft().getY())
                || doubleEquals(collisionPoint.getY(), (this.rec.getUpperLeft().getY() + this.rec.getHeight()))) {
            currentVelocity.setDy(-(currentVelocity.getDy()));
        }

        if (doubleEquals(collisionPoint.getX(), this.rec.getUpperLeft().getX())
                || doubleEquals(collisionPoint.getX(), (this.rec.getUpperLeft().getX() + this.rec.getWidth()))) {
            currentVelocity.setDx(-(currentVelocity.getDx()));
        }
        this.notifyHit(hitter);
        // Calculate the new velocity based on the collision angle and the current velocity.
        return currentVelocity;
    }

    /**
     * Adds this object to the given Game object as both a collidable and a sprite.
     *
     * @param g the Game object to add this object to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public boolean isInMe(Point p) {
        return (p.getX() < upperLeft.getX() + width && p.getX() > upperLeft.getX()) && (p.getY()
                < upperLeft.getY() + height && p.getY() > upperLeft.getY());
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * Row of blocks generator array list.
     *
     * @param x           the x
     * @param y           the y
     * @param width       the width
     * @param height      the height
     * @param numOfBlocks the num of blocks
     * @param color       the color
     * @return the array list
     */
    public static ArrayList<Block> rowOfBlocksBuilder(int x, int y, int width, int height,
                                                        int numOfBlocks, Color color) {
        ArrayList<Block> list = new ArrayList<>();
        for (int i = 0; i < numOfBlocks; i++) {
            list.add(new Block(new Rectangle(new Point(x, y), width, height), color));
            x = x - width;
        }
        return list;
    }
}