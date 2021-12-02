package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.ObserverPattern.Object;
import model.ObserverPattern.Observer;

public class Shooter extends GameElement implements Object {

    public static final int UNIT_MOVE = 12;
    public static final int MAX_BULLETS = 8;

    private ArrayList<Observer> observers = new ArrayList<>();

    public enum Event {
        HitEnemy, BombHit
    }

    private ArrayList<GameElement> components = new ArrayList<>();
    private ArrayList<GameElement> weapons = new ArrayList<>();

    public Shooter(int x, int y) {
        super(x, y, 0, 0);

        var size = ShooterElement.SIZE;
        var s1 = new ShooterElement(x - size, y - size, Color.white, false);
        var s2 = new ShooterElement(x, y - size, Color.white, false);
        var s3 = new ShooterElement(x - size, y, Color.white, false);
        var s4 = new ShooterElement(x, y, Color.white, false);
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

    public void removeBulletsOutOfBounds() {
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

    public ArrayList<GameElement> getComponents() {
        return components;
    }

    @Override
    public void render(Graphics2D g2) {
        for (var c : components) {
            c.render(g2);
        }
        for (var w : weapons) {
            w.render(g2);
        }
    }

    @Override
    public void animate() {
        for (var w : weapons) {
            w.animate();
        }
    }

    @Override
    public void addShooterListener(Observer o) {
        observers.add(o);

    }

    @Override
    public void removeShooterListener(Observer o) {
        observers.remove(o);

    }

    @Override
    public void notifyShooterObservers(Event event) {
        switch(event){
            case HitEnemy:
                for(var o: observers){
                    o.shooterEnemy();
                }
                break;
            case BombHit:
                for(var o: observers){
                    o.hitEnemy();
                }
                break;
        }
    }
}