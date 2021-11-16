package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import model.Bullet;
import model.Shooter;
import view.GameBoard;
import view.TextDraw;

public class TimerListener implements ActionListener {

    public enum EventType {
        KEY_RIGHT, KEY_LEFT, KEY_SPACE
    }

    private GameBoard gameBoard;
    private LinkedList<EventType> eventQueue;
    private final int BOMB_DROP_FREQ = 20;
    private int frameCounter = 0;

    public TimerListener(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        eventQueue = new LinkedList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameBoard.isGameOver()) {
            ++frameCounter;
            update();
            processEventQueue();
            processCollision();
            gameBoard.getCanvas().repaint();
        }
    }

    private void processEventQueue() {
        while (!eventQueue.isEmpty()) {
            var e = eventQueue.getFirst();
            eventQueue.removeFirst();
            Shooter shooter = gameBoard.getShooter();
            if (shooter == null)
                return;

            switch (e) {
                case KEY_LEFT:
                    shooter.moveLeft();
                    break;
                case KEY_RIGHT:
                    shooter.moveRight();
                    break;
                case KEY_SPACE:
                    if (shooter.canFireMoreBullet())
                        shooter.getWeapons().add(new Bullet(shooter.x, shooter.y));
                    break;
            }
        }

        if (frameCounter == BOMB_DROP_FREQ) {
            gameBoard.getEnemyComposite().shoot();
            frameCounter = 0;
        }
    }

    private void processCollision() {
        var shooter = gameBoard.getShooter();
        var enemyComposite = gameBoard.getEnemyComposite();

        shooter.removeBulletsOutOfBounds();
        enemyComposite.removeBombsOutOfBounds();
        enemyComposite.processCollision(shooter);
        if (enemyComposite.getMovement().atBottom()) {
            gameBoard.setGameOver(true);
            gameBoard.getCanvas().getGameElements().add(new TextDraw("You lose! Score: " + gameBoard.getScore(),100, 100, Color.red, 30));
            gameBoard.setHighScore(gameBoard.getScore());
            gameBoard.getHighScoreDisplay().setText("" + gameBoard.getHighScore());
            gameBoard.setScore(0);
            gameBoard.getScoreDisplay().setText("" + gameBoard.getScore());
        }
    }

    private void update(){
        for(var e: gameBoard.getCanvas().getGameElements()){
            e.animate();
        }
    }

    public LinkedList<EventType> getEventQueue() {
        return eventQueue;
    }
    
}