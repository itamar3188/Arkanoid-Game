package game_tools;

import Animations.EndScreen;
import Animations.PauseScreen;
import Animations.AnimationRunner;
import Animations.Animation;
import Animations.CountdownAnimation;
import Levels.LevelInformation;
import biuoop.DrawSurface;

import java.util.List;

import game_parts.Point;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game_parts.Block;
import game_parts.Rectangle;
import game_parts.Ball;
import game_parts.Paddle;
import game_parts.Velocity;

import java.awt.Color;

//Itamar Cohen 318897089

/**
 * This Java class defines a Game object that initializes and manages the game's elements.
 * The game is composed of a collection of Sprites, which can be collidable or not, and a game environment.
 * The environment includes a collection of Collidables, which are the objects that can collide with the balls.
 * The game window has dimensions of 800x600 pixels, and it includes a top, a bottom, and two side blocks.
 * The game also has a Paddle object that can move horizontally to bounce the balls, and two Ball objects
 * that are added to the game with initial positions and velocities.
 */
public class GameLevel implements Animation {
    //    private static final int SPEED = 6;
    private static final int WINDOW_WIDTH = 800;    //dimensions of the main window
    private static final int WINDOW_HEIGHT = 600;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;
    private boolean isFinal;


    /**
     * Instantiates a new Game level.
     *
     * @param levelInformation the level information
     * @param k                the k
     * @param r                the r
     * @param s                the s
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor k, AnimationRunner r, Counter s) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInfo = levelInformation;
        this.keyboard = k;
        this.gui = r.getGui();
        this.scoreIndicator = new ScoreIndicator(s, levelInformation.levelName());
        this.scoreCounter = s;
        this.runner = r;
        this.isFinal = false;
//        this.currentLevel = levelInformation;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();

        // Create the background sprite
        Sprite background = this.levelInfo.getBackground();
        this.sprites.addSprite(background);
        Block scoreBlock = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.WHITE);
        scoreBlock.addToGame(this);
        this.addSprite(scoreIndicator);

        Block topBlock = new Block(new Rectangle(new Point(20, 20), 800, 20), Color.GRAY);
        topBlock.addToGame(this);

        // Add the side blocks
        Block leftBlock = new Block(new Rectangle(new Point(0, 20), 20, 580), Color.GRAY);
        leftBlock.addToGame(this);

        Block rightBlock = new Block(new Rectangle(new Point(780, 20), 20, 580), Color.GRAY);
        rightBlock.addToGame(this);

        //lister to the balls
        int numberOfBalls = this.levelInfo.numberOfBalls();
        this.ballsCounter = new Counter(numberOfBalls);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);

        //Bottom block
        Block bottomBlock = new Block(new Rectangle(new Point(0, 600), 800, 20), Color.BLUE);
        bottomBlock.addHitListener(ballRemover);
        bottomBlock.addToGame(this);

        // Create the blocks
        List<Block> blocks = this.levelInfo.blocks();
        this.blocksCounter = new Counter(blocks.size());
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(new BlockRemover(this, this.blocksCounter));
            block.addHitListener(new ScoreTrackingListener(this.scoreCounter));
        }

        int paddleX = (WINDOW_WIDTH - this.levelInfo.paddleWidth()) / 2;
        int paddleY = WINDOW_HEIGHT - 20; // Adjust the y-coordinate as needed
        Block paddleBlock = new Block(new Rectangle(new Point(paddleX, paddleY),
                this.levelInfo.paddleWidth(), 20), Color.ORANGE);
        Paddle paddle = new Paddle(this.gui, paddleBlock);
        paddle.setSpeed(this.levelInfo.paddleSpeed());
        paddle.addToGame(this);

        List<Velocity> ballVelocities = this.levelInfo.initialBallVelocities();
        paddle.setBallSpeed(9);
        List<Point> ballStartPoints = this.levelInfo.initialBallPoints();

        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball = new Ball(ballStartPoints.get(i), 5, Color.WHITE);
            // Adjust the initial position and size as needed
            ball.setVelocity(ballVelocities.get(i));
            ball.addToGame(this);
            ball.setEnvironment(this.environment);
        }
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (blocksCounter.getValue() <= 0 || ballsCounter.getValue() <= 0) {
            if (blocksCounter.getValue() <= 0) {
                this.scoreCounter.increase(100);
            }
            this.running = false;
            // this.gui.close();
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
    }

    /**
     * Run.
     */
    public void run() {
        //this.createBallsOnTopOfPaddle();

        this.runner.run(new CountdownAnimation(120, 4, this.sprites));
        // or a similar method
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        if (ballsCounter.getValue() <= 0 || isFinal) {
            EndScreen endScreen = new EndScreen(this.keyboard, ballsCounter.getValue() > 0, scoreCounter.getValue());
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, endScreen));
            gui.close();
        }
    }

    /**
     * Gets num balls.
     *
     * @return the num balls
     */
    int getNumBalls() {
        return this.ballsCounter.getValue();
    }

    /**
     * Gets num blocks.
     *
     * @return the num blocks
     */
    int getNumBlocks() {
        return this.blocksCounter.getValue();
    }

    /**
     * Gets score counter.
     *
     * @return the score counter
     */
    Counter getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    GUI getGui() {
        return this.gui;
    }

    /**
     * Sets final.
     *
     * @param aFinal the a final
     */
    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
