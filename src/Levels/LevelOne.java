package Levels;

import game_parts.Block;
import game_parts.Point;
import game_parts.Rectangle;
import game_parts.Velocity;
import game_tools.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level one.
 */
public class LevelOne implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(0, 9));
        return list;
    }

    @Override
    public List<Point> initialBallPoints() {
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(405, 560));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 3;
    }

    @Override
    public int paddleWidth() {
        return 75;
    }

    @Override
    public String levelName() {
        return new String("Direct Hit");
    }

    @Override
    public Sprite getBackground() {
        return new BackGroundOne();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        list.add(new Block(new Rectangle(new Point(395, 170), 25,
                25), Color.red));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
