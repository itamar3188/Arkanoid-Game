package game_tools;
import game_parts.Line;
import game_parts.Point;
import java.util.ArrayList;
import java.util.List;

//Itamar Cohen 318897089

/**
 * This is a Java class called GameEnvironment which represents the game world and its environment.
 * It contains a private field called collidables, which is a list of objects that can be collided with.
 * The constructor initializes collidables as a new ArrayList.
 * The class also has a public method called getCollidables, which returns the list of collidable objects.
 * The addCollidable method adds a collidable object to the list.
 * Finally, the getClosestCollision method takes a Line object as a parameter, which represents the trajectory of
 * an object in the game. The method checks for collisions between the trajectory and each collidable object in the
 * list, and returns information about the closest collision (if any). The information is returned as a CollisionInfo
 * object, which contains the point of collision and the collidable object that was collided with.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Add collidable.
     * add the given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }


    /**
     * Gets closest collision.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point currentCloset = null;
        Collidable object = null;
        for (Collidable c : this.collidables) {
            Point newClosestCollide = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (newClosestCollide != null) {
                if (currentCloset == null) {
                    currentCloset = newClosestCollide;
                    object = c;
                } else if (trajectory.start().distance(newClosestCollide)
                        < trajectory.start().distance(currentCloset)) {
                    currentCloset = newClosestCollide;
                    object = c;
                }
            }
        }
        return new CollisionInfo(currentCloset, object);
    }
}