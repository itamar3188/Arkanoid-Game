package Levels;

import game_parts.Block;
import game_parts.Point;
import game_parts.Velocity;
import game_tools.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level three.
 */
public class LevelThree implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(50, 9));
        velocities.add(Velocity.fromAngleAndSpeed(230, 9));
        return velocities;
    }

    @Override
    public List<Point> initialBallPoints() {
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(400, 560));
        list.add(new Point(400, 560));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 9;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return new String("Green 3");
    }

    @Override
    public Sprite getBackground() {
        return new BackGroundThree();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        Color[] rowsColors = {Color.darkGray, Color.red, Color.yellow, Color.BLUE, Color.white};
        for (int i = 0, j = 10, startingY = 150; i < 5; i++, j--) {
            list.addAll(Block.rowOfBlocksBuilder(730, startingY,
                    50, 20, j, rowsColors[i]));
            startingY = startingY + 20;
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 45;
    }
}
