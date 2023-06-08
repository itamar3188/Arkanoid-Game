package Levels;

import biuoop.DrawSurface;
import game_tools.Sprite;

import java.awt.*;

public class BackGroundTwo implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(new Color(255, 234, 200));
        d.fillCircle(135, 135, 80);
        d.setColor(new Color(255, 234, 100));
        d.fillCircle(135, 135, 70);
        d.setColor(new Color(255, 234, 0));
        d.fillCircle(135, 135, 55);
        int start = 20;
        //Draws the sun's beams//
        while (start <= 680) {
            d.drawLine(135, 135, start, 250);
            start = start + 15;
        }
    }

    @Override
    public void timePassed() {
    }
}
