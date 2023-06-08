package game_tools;
import game_parts.Point;

//Itamar Cohen 318897089

/**
 * The CollisionInfo class is used to represent information about a collision between two objects in the game.
 * It has two properties. "collisionPoint" is the Point object representing the exact point at which the collision
 * occurred, "collisionObject" The Collidable object involved in the collision.
 * The class also includes two methods, a constructor that creates a new CollisionInfo object with the specified
 * collisionPoint and collisionObject.
 */

public class CollisionInfo {
    // the point at which the collision occurs.
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}