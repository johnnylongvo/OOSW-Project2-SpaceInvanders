package model.ObserverPattern;

import java.awt.Color;

import view.GameBoard;
import view.TextDraw;

public class ObserverShooter implements Observer {

    private GameBoard gameBoard;
    
    private int enemiesCount = 20;
    private int healthCount = 4;

    public ObserverShooter(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    //Game Score & Game over
    @Override
    public void shooterEnemy() {
        int score = gameBoard.getScore();
        score += 10;
        enemiesCount--;
        gameBoard.setScore(score);
        gameBoard.getDisplayScore().setText("" + score);

        if (enemiesCount == 0) {
            gameBoard.setGameOver(true);
            gameBoard.getCanvas().getGameElements().add(new TextDraw("You WIN! Score: " + score, 18, 150, Color.GREEN, 27));
        }
    }

    @Override
    public void hitEnemy() {
        --healthCount;

        if (healthCount == 0) {
            gameBoard.setGameOver(true);
            int score = gameBoard.getScore();
            gameBoard.getCanvas().getGameElements().add(new TextDraw("You LOST!! Score: " + score, 80, 150, Color.RED, 27));
            gameBoard.setHighScore(score);
            gameBoard.getDisplayHighScore().setText("" + gameBoard.getHighScore());
            gameBoard.setScore(0);
            gameBoard.getDisplayScore().setText("" + gameBoard.getScore());
        }
    }

    @Override
    public void enemyHealth() {}

    @Override
    public void enemyDamage() {}
    
}