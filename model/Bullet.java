package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameElement {

    public static final int WIDTH = 5; // pixels
    public static final int UNIT_MOVE = 10;

    // constructor
    public Bullet(int x, int y) {
        super(x, y, Color.red, true, WIDTH, WIDTH * 3);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        if (filled)
            g2.fillRect(x, y, width, height);
        else
            g2.drawRect(x, y, width, height);
    }

    @Override
    public void animate() {
        super.y -= UNIT_MOVE;
    }

}