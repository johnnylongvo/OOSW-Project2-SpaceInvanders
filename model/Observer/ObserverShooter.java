package model.Observer;

import java.awt.Color;

import view.GameBoard;
import view.TextDraw;

public class ObserverShooter implements Observer {

    private GameBoard gameBoard;
    private int enemyCount = 20; //enemy array goes down by 20 pixels
    private int hitCount = 4;

    public ObserverShooter(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    //Game Score and Game over
    @Override
    public void shooterHitEnemy() {
        int score = gameBoard.getScore();
        score += 10;
        enemyCount--;
        gameBoard.setScore(score);
        gameBoard.getScoreDisplay().setText("" + score);
        if(enemyCount == 0){
            gameBoard.setGameOver(true);
            gameBoard.getCanvas().getGameElements().add(new TextDraw("Congratulations, You WIN! Score: " + score, 111, 111, Color.ORANGE, 32));
        }
    }

    @Override
    public void enemyHitShooter() {
        --hitCount;
        if(hitCount == 0){
            gameBoard.setGameOver(true);
            int score = gameBoard.getScore();
            gameBoard.getCanvas().getGameElements().add(new TextDraw("You LOST!! Score: " + score, 111, 111, Color.RED, 32));
            gameBoard.setHighScore(score);
            gameBoard.getHighScoreDisplay().setText("" + gameBoard.getHighScore());
            gameBoard.setScore(0);
            gameBoard.getScoreDisplay().setText("" + gameBoard.getScore());
        }
    }
    
}