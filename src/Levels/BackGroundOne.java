package Levels;

import biuoop.DrawSurface;
import game_tools.Sprite;

import java.awt.Color;


/**
 * The type Back ground one.
 */
public class BackGroundOne implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        for (int i = 0, length = 50; i < 3; i++, length = length + 25) {
            d.drawCircle(407, 180, length);
        }
        d.drawLine(425, 182, 545, 182);
        d.drawLine(270, 182, 390, 182);
        d.drawLine(407, 50, 407, 165);
        d.drawLine(407, 182, 407, 300);
    }

    @Override
    public void timePassed() {

    }
}
