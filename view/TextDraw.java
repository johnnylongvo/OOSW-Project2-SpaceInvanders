package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import model.GameElement;

public class TextDraw extends GameElement {

    private String text;
    private int size;

    public TextDraw(String text, int x, int y, Color color, int size) {
        super(x, y, color, false, 0, 0);
        this.text = text;
        this.size = size;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setFont(new Font("Courier", Font.BOLD, size));
        g2.drawString(text, x, y);
    }

    @Override
    public void animate() {
        // TODO Auto-generated method stub
        
    }

    
}