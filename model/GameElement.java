package model;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class GameElement {

    public int x;
    public int y;
    public Color color;
    public boolean filled;
    public int width;
    public int height;

    public GameElement(int x, int y, Color color, boolean filled, int width, int height) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.filled = filled;
        this.width = width;
        this.height = height;
    }

    public GameElement() {
        this(0, 0, Color.white, false, 0, 0);
    }

    public GameElement(int x, int y, int width, int height) {
        this(x, y, Color.white, false, width, height);
    }

    public boolean collideWith(GameElement another){
        if(another.x > x + width || x > another.x + another.width || y + height < another.y || y > another.y + another.height){
            return false;
        } else{
            return true;
        }
    }

    public abstract void render(Graphics2D g2);
    public abstract void animate();

}
