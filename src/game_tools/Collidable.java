package game_tools;

import game_parts.Ball;
import game_parts.Velocity;
import game_parts.Point;
import game_parts.Rectangle;
//Itamar Cohen 318897089

/**
 * The Collidable interface defines the behavior for objects that can be collided with in the game.
 * It includes three methods: getCollisionRectangle(), hit(Point collisionPoint, Velocity currentVelocity),
 * Boolean isInMe(Point p).
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the hitter ball
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Is in me boolean.
     * check if the ball inside a block.
     *
     * @param p the p
     * @return the boolean
     */
    boolean isInMe(Point p);
}