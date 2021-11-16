package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import model.Shooter.Event;
import model.DesignPattern.WeaponComponent;
import model.StrategyPattern.EnemyMovement;
import view.GameBoard;

public class EnemyComposite extends GameElement implements WeaponComponent{

    public static final int NROWS = 2;
    public static final int NCOLS = 10;
    public static final int ENEMY_SIZE = 20;
    public static final int UNIT_MOVE = 5;

    private ArrayList<ArrayList<GameElement>> rows;
    private ArrayList<GameElement> bombs;
    private boolean movingToRight = true;
    private Random random = new Random();
    private EnemyMovement movement;
    private int hits = 0;

    public EnemyComposite(){
        rows = new ArrayList<>();
        bombs = new ArrayList<>();

        for(int r = 0; r < NROWS; r++){
            var oneRow = new ArrayList<GameElement>();
            rows.add(oneRow);
            for(int c = 0; c < NCOLS; c++){
                oneRow.add(new Enemy(c * ENEMY_SIZE * 2, r * ENEMY_SIZE * 2, ENEMY_SIZE, Color.yellow, true));
            }
        }
        movement = new EnemyMovement(this);
    }
    
    public ArrayList<ArrayList<GameElement>> getRows() {
        return rows;
    }

    public static int getEnemySize() {
        return ENEMY_SIZE;
    }

    public EnemyMovement getMovement() {
        return movement;
    }

    @Override
    public void render(Graphics2D g2) {
        // render enemy array
        for(var r: rows){
            for(var e: r){
                e.render(g2);
            }
        }

        // render bombs
        for(var b: bombs){
            b.render(g2);
        }
    }

    @Override
    public void animate() {
        int dx;
        if(movement.speedUp(hits)){
            dx = UNIT_MOVE + 2;
        }else{
            dx = UNIT_MOVE;
        }
        if(movingToRight){
            if(movement.rightEnd() >= GameBoard.WIDTH){
                dx = -dx;
                for(var row: rows){
                    for(var e: row){
                        e.y += ENEMY_SIZE;
                    }
                }
                movingToRight = false;
            }
        }else{
            dx = -dx;
            if(movement.leftEnd() <= 0){
                dx += dx;
                for(var row: rows){
                    for(var e: row){
                        e.y += ENEMY_SIZE;
                    }
                }
                movingToRight = true;
            }
        }

        // update x loc
        for(var row: rows){
            for(var e: row){
                e.x += dx;
            }
        }

        //animate bombs
        for(var b: bombs){
            b.animate();
        }
    }

    public ArrayList<GameElement> getBombs() {
        return bombs;
    }

    @Override
    public void shoot() {
        for(var row: rows){
            for(var e: row){
                if(random.nextFloat() < 0.1F){
                    bombs.add(new Bomb(e.x, e.y));
                }
            }
        }
    }

    public void removeBombsOutOfBounds(){
        var remove = new ArrayList<GameElement>();
        for(var b: bombs){
            if(b.y >= GameBoard.HEIGHT){
                remove.add(b);
            }
        }
        bombs.removeAll(remove);
    }

    public void processCollision(Shooter shooter){
        var removeBullets = new ArrayList<GameElement>();

        // bullets vs enemies
        for(var row: rows){
            var removeEnemies = new ArrayList<GameElement>();
            for(var enemy: row){
                for(var bullet: shooter.getWeapons()){
                    if(enemy.collideWith(bullet)){
                        shooter.notifyObservers(Event.HitEnemy);
                        removeBullets.add(bullet);
                        removeEnemies.add(enemy);
                        hits++;
                    }
                }
            }
            row.removeAll(removeEnemies);
        }
        shooter.getWeapons().removeAll(removeBullets);

        // bullets vs bombs
        var removeBombs = new ArrayList<GameElement>();
        removeBullets.clear();
        for(var b: bombs){
            for(var bullet: shooter.getWeapons()){
                if(b.collideWith(bullet)){
                    removeBombs.add(b);
                    removeBullets.add(bullet);
                }
            }
        }
        shooter.getWeapons().removeAll(removeBullets);
        bombs.removeAll(removeBombs);

        // bombs vs shooter
        removeBullets.clear();
        var removeComponents = new ArrayList<GameElement>();
        for(var b: bombs){
            for(var c: shooter.getComponents()){
                if(c.collideWith(b)){
                    shooter.notifyObservers(Event.BombHit);
                    removeBombs.add(b);
                    removeComponents.add(c);
                }
            }
        }
        shooter.getComponents().removeAll(removeComponents);
        bombs.removeAll(removeBombs);
    }
}