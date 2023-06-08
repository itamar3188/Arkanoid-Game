package game_parts;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game_tools.Collidable;
import game_tools.GameLevel;
import game_tools.Sprite;

import java.awt.Color;

//Itamar Cohen 318897089

/**
 * The Paddle class implements two interfaces: Sprite and Collidable, and represents a paddle object in a game.
 * The Paddle class has a KeyboardSensor object and a Block object. The KeyboardSensor object is used for getting
 * input from the user, and the Block object is used for representing the graphical representation of the paddle.
 * The Block object is also used for implementing the Collidable interface, and for drawing the paddle on the screen
 * using the drawOn() method.
 */
public class Paddle implements Sprite, Collidable {
    private static final int FIRST_PART = 1;
    private static final int SECOND_PART = 2;
    private static final int THIRD_PART = 3;
    private static final int FOUR_PART = 4;
    private static final int FIFTH_PART = 5;
    private static final int FIRST_ANGLE = 300;
    private static final int SECOND_ANGLE = 330;
    private static final int FOUR_ANGLE = 30;
    private static final int FIVE_ANGLE = 60;
    private static final int START = 20;
    private static final int END = 680;

    /**
     * The Threshold.
     */
    static final double THRESHOLD = 0.00001;
    private final KeyboardSensor keyboard;
    private final Color color;
    private final Block block;
    private final Rectangle rec;
    private final Point upperLeft;
    private final double width;
    private final double height;
    private int paddleSpeed;
    private int ballSpeed;

    /**
     * Instantiates a new Paddle.
     *
     * @param gui   the gui
     * @param block the block
     */
    public Paddle(GUI gui, Block block) {
        this.keyboard = gui.getKeyboardSensor();
        this.block = block;
        this.rec = this.block.getRec();
        this.color = this.block.getColor();
        this.upperLeft = this.rec.getUpperLeft();
        this.width = this.rec.getWidth();
        this.height = this.rec.getHeight();
        this.paddleSpeed = 4;
        this.ballSpeed = 7;

    }

    /**
     * Moves the block object to the left by 4 units.
     */
    public void moveLeft() {
        this.block.getRec().getUpperLeft().setX(this.block.getRec().getUpperLeft().getX() - this.paddleSpeed);
    }

    /**
     * Moves the block object to the right by 4 units.
     */
    public void moveRight() {
        this.block.getRec().getUpperLeft().setX(this.block.getRec().getUpperLeft().getX() + this.paddleSpeed);
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * Sets ball speed.
     *
     * @param ballSpeed the ball speed
     */
    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    @Override
    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.upperLeft.getX() > START) {
                this.moveLeft();
            }
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.upperLeft.getX() < 780 - this.width) {
                this.moveRight();
            }
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getRec();
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

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int hitLocation = findLocation(collisionPoint);
        if (doubleEquals(collisionPoint.getY(), this.rec.getUpperLeft().getY())
                || doubleEquals(collisionPoint.getY(), (this.rec.getUpperLeft().getY() + this.rec.getHeight()))) {
            currentVelocity = paddleVelocityHit(hitLocation, currentVelocity);
        } else {
            currentVelocity.setDx(-(currentVelocity.getDx()));
        }
        return currentVelocity;
    }


    /**
     * The paddleVelocityHit method receives the current velocity of the ball and the location of the collision
     * as parameters. It then sets the new velocity of the ball based on the location of the collision.
     *
     * @param hitLocation     the hit part.
     * @param currentVelocity the velocity of the ball.
     * @return Velocity currentVelocity
     */
    private Velocity paddleVelocityHit(int hitLocation, Velocity currentVelocity) {
        if (hitLocation == FIRST_PART) {
            currentVelocity.setDx(Velocity.fromAngleAndSpeed(FIRST_ANGLE, this.ballSpeed).getDx());
            currentVelocity.setDy(Velocity.fromAngleAndSpeed(FIRST_ANGLE, this.ballSpeed).getDy());
        } else if (hitLocation == SECOND_PART) {
            currentVelocity.setDx(Velocity.fromAngleAndSpeed(SECOND_ANGLE, this.ballSpeed).getDx());
            currentVelocity.setDy(Velocity.fromAngleAndSpeed(SECOND_ANGLE, this.ballSpeed).getDy());
        } else if (hitLocation == THIRD_PART) {
            currentVelocity.setDy(-currentVelocity.getDy());
        } else if (hitLocation == FOUR_PART) {
            currentVelocity.setDx(Velocity.fromAngleAndSpeed(FOUR_ANGLE, this.ballSpeed).getDx());
            currentVelocity.setDy(Velocity.fromAngleAndSpeed(FOUR_ANGLE, this.ballSpeed).getDy());
        } else {
            currentVelocity.setDx(Velocity.fromAngleAndSpeed(FIVE_ANGLE, this.ballSpeed).getDx());
            currentVelocity.setDy(Velocity.fromAngleAndSpeed(FIVE_ANGLE, this.ballSpeed).getDy());
        }
        return currentVelocity;
    }

    /**
     * The findLocation method calculates the location of the collision on the paddle based on the collision point.
     * It does this by dividing the paddle width into 5 equal parts and returning the corresponding part number based
     * on the collision point's x-coordinate.
     *
     * @param collisionPoint the point where the ball hit the paddle.
     * @return the part of the paddle.
     */
    private int findLocation(Point collisionPoint) {
        double x = collisionPoint.getX() - this.upperLeft.getX();
        if (x <= this.width / FIFTH_PART) {
            return FIRST_PART;
        } else if (x <= (this.width / FIFTH_PART) * 2) {
            return SECOND_PART;
        } else if (x <= (this.width / FIFTH_PART) * 3) {
            return THIRD_PART;
        } else if (x <= (this.width / FIFTH_PART) * 4) {
            return FOUR_PART;
        } else {
            return FIFTH_PART;
        }
    }

    /**
     * Add to game.
     * Add this paddle to the game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public boolean isInMe(Point p) {
        return (p.getX() < upperLeft.getX() + width && p.getX() > upperLeft.getX()) && (p.getY()
                < upperLeft.getY() + height && p.getY() > upperLeft.getY());
    }
}