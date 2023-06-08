package Levels;

import game_parts.Block;
import game_parts.Point;
import game_parts.Velocity;
import game_tools.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level two.
 */
public class LevelTwo implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Point> initialBallPoints() {
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Point(400, 500));
        }
        return list;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        for (int i = 0, angle = -100; angle <= -20; i++, angle = angle + 20) {
            list.add(Velocity.fromAngleAndSpeed(angle, 9));
        }

        for (int i = 0, angle = 20; angle <= 100; i++, angle = angle + 20) {
            list.add(Velocity.fromAngleAndSpeed(angle, 9));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return new String("Wide Easy");
    }

    @Override
    public Sprite getBackground() {
        return new BackGroundTwo();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        Color[] rowsColors = {Color.PINK, Color.CYAN, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.RED};
        int num;
        int width;
        for (int i = 0, x = 729; i < 7; i++, x = x - width * num) {
            if (i == 3) {
                num = 3;
                width = 52;
            } else {
                width = 50;
                num = 2;
            }
            list.addAll(Block.rowOfBlocksBuilder(x, 250, width, 20, num, rowsColors[i]));
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
