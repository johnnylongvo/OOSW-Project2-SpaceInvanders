package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.KeyController;
import controller.TimerListener;
import model.EnemyComposite;
import model.Shooter;
import model.ShooterElement;
import model.ObserverPattern.ObserverShooter;

public class GameBoard {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;
    public static final int FPS = 20;
    public static final int DELAY = 1000 / FPS;
    
    private JFrame window;
    private MyCanvas canvas;
    private Shooter shooter;
    private Timer timer;
    private TimerListener timerListener;
    private EnemyComposite enemyComposite;

    //NEW INPUTS 
    private JLabel displayScore = new JLabel();
    private JLabel displayHighScore = new JLabel();
    private int score = 0;
    private int highScore = 0;
    
    private boolean gameOver = true;

    public GameBoard(JFrame window){
        this.window = window;
    }

    public void init(){
        Container cp = window.getContentPane();

        JPanel northPanel = new JPanel();
        displayScore.setText("");
        JLabel label = new JLabel("Score: ");
        northPanel.add(label);
        displayScore.setText("" + score);
        northPanel.add(displayScore);

        JLabel highScoreLabel = new JLabel("High Score: ");
        displayHighScore.setText("" + highScore);
        northPanel.add(highScoreLabel);
        northPanel.add(displayHighScore);
        cp.add(BorderLayout.NORTH, northPanel);

        canvas = new MyCanvas(this, WIDTH, HEIGHT);
        cp.add(BorderLayout.CENTER, canvas);
        canvas.addKeyListener(new KeyController(this));
        canvas.requestFocusInWindow();
        canvas.setFocusable(true);


        JButton startButton = new JButton("Start");
        JButton quitButton = new JButton("Quit");
        JButton clearButton = new JButton("Clear Highscore");
        startButton.setFocusable(false);
        quitButton.setFocusable(false);
        clearButton.setFocusable(false);

        JPanel southPanel = new JPanel();
        southPanel.add(startButton);
        southPanel.add(quitButton);
        southPanel.add(clearButton);
        cp.add(BorderLayout.SOUTH, southPanel);

        canvas.getGameElements().add(new TextDraw("Click <Start> to Play", 111, 111, Color.ORANGE, 32));

        timerListener = new TimerListener(this);
        timer = new Timer(DELAY, timerListener);
        //timer = new Timer(DELAY, new TimerListener(this));

        startButton.addActionListener(event -> {
            setGameOver(false);
            shooter = new Shooter(GameBoard.WIDTH / 2, GameBoard.HEIGHT - ShooterElement.SIZE);
            ObserverShooter observer = new ObserverShooter(this);
            shooter.addShooterListener(observer);
            enemyComposite = new EnemyComposite();
            canvas.getGameElements().clear();
            canvas.getGameElements().add(shooter);
            canvas.getGameElements().add(enemyComposite);
            timer.start();
        });

        clearButton.addActionListener(event -> {
            highScore = 0;
            displayHighScore.setText("" + highScore);
        });

        quitButton.addActionListener(event -> System.exit(0));

    }

    public JFrame getWindow() {
        return window;
    }

    public MyCanvas getCanvas() {
        return canvas;
    }

    public Timer getTimer() {
        return timer;
    }

    public TimerListener getTimerListener() {
        return timerListener;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public JLabel getDisplayScore() {
        return displayScore;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public JLabel getDisplayHighScore() {
        return displayHighScore;
    }

    public EnemyComposite getEnemyComposite() {
        return enemyComposite;
    }

    public Shooter getShooter() {
        return shooter;
    }

}