package game_parts;

import biuoop.DrawSurface;
import game_tools.CollisionInfo;
import game_tools.GameLevel;
import game_tools.GameEnvironment;
import game_tools.Sprite;
import game_tools.Collidable;
import java.awt.Rectangle;
import java.awt.Color;

//Itamar Cohen 318897089

/**
 * The Ball class represents a circle-shaped object that can be displayed on a graphical user interface (GUI)
 * using a given center point, radius, and color. The class provides methods for setting and getting the ball's
 * velocity, as well as for drawing the ball on a given DrawSurface object. The Ball class also includes a static
 * final variable ZERO that represents the value zero, and two static final variables WINDOW_WIDTH and WINDOW_HEIGHT
 * that represent the dimensions of the GUI window. The class uses the Point and Velocity classes to store the ball's
 * center point and velocity, respectively, and the Rectangle class to define a bounding rectangle for the ball.
 * The class includes two constructors, one that takes a Point object and the other that takes x and y coordinates,
 * as well as a radius and color, to create a new Ball object.
 */
public class Ball implements Sprite {
    /**
     * The Zero.
     */
    static final int ZERO = 0;
    private static final int WINDOW_WIDTH = 200;
    private static final int WINDOW_HEIGHT = 200;
    private Point center;       // the center point of the ball
    private int radius;         // the radius of the ball
    private final Color color;        // the color of the ball
    private Velocity velocity;  // the velocity (direction and speed) of the ball
    private Rectangle rectangle;    // the bounding rectangle of the ball
    private GameEnvironment environment; // new instance variable


    /**
     * Instantiates a new Ball with a given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int radius, Color color) {
        // Assign the given center, radius, and color to the instance variables
        this.center = center;
        this.radius = radius;
        this.color = color;
        // Initialize a new velocity with 0 speed in both x and y directions, and assign it to the instance variable
        Velocity velocity = new Velocity(ZERO, ZERO);
        this.velocity = velocity;
        // Initialize a new rectangle with all sides having length 0, and assign it to the instance variable
        Rectangle rectangle = new Rectangle(ZERO, ZERO, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.rectangle = rectangle;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param point           the point
     * @param radius          the radius
     * @param velocity        the velocity
     * @param gameEnvironment the game environment
     */
    public Ball(Point point, int radius, Velocity velocity, GameEnvironment gameEnvironment) {
        this.center = point;
        this.radius = radius;
        this.velocity = velocity;
        this.environment = gameEnvironment;
        this.color = Color.BLACK;
    }


    /**
     * Sets environment.
     *
     * @param environment the environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Gets the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        // Set the color of the drawing surface to the ball's color
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(this.color);
        // Draw a filled circle at the ball's position with its radius
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        // Set the ball's velocity to the given velocity
        this.velocity = v;
    }

    /**
     * Set velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        // Set the ball's velocity to the given dx and dy values
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * Get velocity velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        // Return the ball's velocity
        return this.velocity;
    }

    /**
     * Set rectangle.
     *
     * @param r the r
     */
    public void setRectangle(Rectangle r) {
        // Set the ball's rectangle to the given rectangle
        this.rectangle = r;
    }

    /**
     * Get rectangle rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        // Return the ball's rectangle
        return this.rectangle;
    }

    /**
     * Sets center.
     *
     * @param x the x
     * @param y the y
     */
    public void setCenter(int x, int y) {
        Point point = new Point(x, y);
        this.center = point;
    }

    /**
     * Check rectangle.
     */
    public void checkRectangle() {
        int diameter = this.radius * 2;
        int width = (int) this.getRectangle().getWidth();
        if (diameter > width) {
            // The diameter of the ball is larger than the width of the rectangle
            // Set the radius to half of the rectangle's width
            this.radius = width / 2;
            int x = (int) this.getRectangle().getCenterX();
            int y = (int) this.getRectangle().getCenterY();
            this.setCenter(x, y);
        }
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


    /**
     * Move one step.
     */
    public void moveOneStep() {
        Point previous = this.center;
        Point nextCenter = this.getVelocity().applyToPoint(this.center);
        // Create a line representing the ball's trajectory from its current center to its next center
        Line trajectory = new Line(this.center, nextCenter);
        CollisionInfo collisionInfo = this.environment.getClosestCollision(trajectory);
        if (collisionInfo.collisionPoint() == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }

        Point p = new Point(collisionInfo.collisionPoint());
        this.center = fixCenter(this.velocity, collisionInfo.collisionPoint());
        collisionInfo.collisionObject().hit(this, p, this.velocity);

        while (isInObstacle(center) != null) {
            if (isInObstacle(previous) == null) {
                if (center.getY() > previous.getY()) {
                    center.setY(center.getY() - 2);
                } else {
                    center.setY(center.getY() - 2);
                }
            } else {
                while (isInObstacle(center) != null) {
                    center.setY(center.getY() - 1);
                }
            }
        }
    }

    private Point fixCenter(Velocity velocity, Point collisionPoint) {
        Point nextCenter = new Point(collisionPoint);
        if (velocity.getDx() < 0) {
            collisionPoint.setX(collisionPoint.getX() + 1);
            nextCenter = collisionPoint;
        }
        if (velocity.getDx() > 0) {
            collisionPoint.setX(collisionPoint.getX() - 1);
            nextCenter = collisionPoint;
        }
        if (velocity.getDy() > 0) {
            collisionPoint.setY(collisionPoint.getY() - 1);
            nextCenter = collisionPoint;
        }
        if (velocity.getDy() < 0) {
            collisionPoint.setY(collisionPoint.getY() + 1);
            nextCenter = collisionPoint;
        }
        return nextCenter;
    }


    private Collidable isInObstacle(Point p) {

        for (Collidable c : this.environment.getCollidables()) {
            if (c.isInMe(p)) {
                return c;
            }
        }
        return null;
    }


    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;
    }
}