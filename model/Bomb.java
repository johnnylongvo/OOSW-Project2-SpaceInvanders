package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bomb extends GameElement {

    public static final int SIZE = 5;
    public static final int UNIT_MOVE = 5;

    public Bomb(int x, int y) {
        super(x, y, Color.magenta, true, SIZE, SIZE*2);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        if (filled)
            g2.fillOval(x, y, width, height);
        else
            g2.drawOval(x, y, width, height);
    }

    @Override
    public void animate() {
        super.y += UNIT_MOVE;
    }

}