package model.Observer;


import java.awt.Color;

import view.GameBoard;
import view.TextDraw;

public class ObserverShooter implements Observer {

    private GameBoard gameBoard;
    private int enemyCount = 20;
    private int hitCount = 4;

    public ObserverShooter(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }


    @Override
    public void shooterHitEnemy() {
        int score = gameBoard.getScore();
        score += 10;
        enemyCount--;
        gameBoard.setScore(score);
        gameBoard.getScoreDisplay().setText("" + score);
        if(enemyCount == 0){
            gameBoard.setGameOver(true);
            gameBoard.getCanvas().getGameElements().add(new TextDraw("You win! Score: " + score, 100, 100, Color.yellow, 30));
        }
    }

    @Override
    public void enemyHitShooter() {
        --hitCount;
        if(hitCount == 0){
            gameBoard.setGameOver(true);
            int score = gameBoard.getScore();
            gameBoard.getCanvas().getGameElements().add(new TextDraw("You lost! Score: " + score, 100, 100, Color.red, 30));
            gameBoard.setHighScore(score);
            gameBoard.getHighScoreDisplay().setText("" + gameBoard.getHighScore());
            gameBoard.setScore(0);
            gameBoard.getScoreDisplay().setText("" + gameBoard.getScore());
        }
    }
    
}