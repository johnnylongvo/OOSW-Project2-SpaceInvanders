package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Shooter extends GameElement {

    public static final int UNIT_MOVE = 10; // pixels
    public static final int MAX_BULLETS = 3;

    private ArrayList<GameElement> components = new ArrayList<>();
    private ArrayList<GameElement> weapons = new ArrayList<>();

    public Shooter(int x, int y) {
        super(x, y, 0, 0);

        var size = ShooterElement.SIZE;
        var s1 = new ShooterElement(x - size, y - size, Color.white, filled);
        var s2 = new ShooterElement(x, y - size, Color.white, filled);
        var s3 = new ShooterElement(x - size, y, Color.white, filled);
        var s4 = new ShooterElement(x, y, Color.white, filled);
        components.add(s1);
        components.add(s2);
        components.add(s3);
        components.add(s4);
    }

    public void moveRight() {
        super.x += UNIT_MOVE;
        for (var c : components) {
            c.x += UNIT_MOVE;
        }
    }

    public void moveLeft() {
        super.x -= UNIT_MOVE;
        for (var c : components) {
            c.x -= UNIT_MOVE;
        }
    }

    public boolean canFireMoreBullet() {
        return weapons.size() < MAX_BULLETS;
    }

    public void removeBulletsOutOfBound() {
        var remove = new ArrayList<GameElement>();
        for (var w : weapons) {
            if (w.y < 0)
                remove.add(w);
        }
        weapons.removeAll(remove);
    }

    public ArrayList<GameElement> getWeapons() {
        return weapons;
    }

    @Override
    public void render(Graphics2D g2) {
        for (var c : components) {
            c.render(g2);
        }
        for (var w : weapons)
            w.render(g2);
    }

    @Override
    public void animate() {
        for (var w : weapons)
            w.animate();
    }
}