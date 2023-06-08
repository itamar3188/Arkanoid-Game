package Levels;

import biuoop.DrawSurface;
import game_tools.Sprite;

import java.awt.*;

/**
 * The type Back ground three.
 */
public class BackGroundThree implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Color forest = new Color(34, 139, 34);
        d.setColor(forest);
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.BLACK);
        d.fillRectangle(70, 430, 100, 170);
        d.setColor(Color.white);
        int y = 440;
        for (int i = 0; i < 5; i++) {
            int x = 80;
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(x, y, 10, 30);
                x = x + 10 + 8;
            }
            y = y + 30 + 5;
        }

        d.setColor(Color.white);
        d.fillRectangle(115, 330, 7, 100);
        d.setColor(Color.BLACK);
        d.drawRectangle(115, 330, 7, 100);

        d.setColor(Color.ORANGE);
        d.fillCircle(118, 321, 11);
        d.setColor(Color.black);
        d.drawCircle(118, 321, 11);
    }

    @Override
    public void timePassed() {

    }
}
