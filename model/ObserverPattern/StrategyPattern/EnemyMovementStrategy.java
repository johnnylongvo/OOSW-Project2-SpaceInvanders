package model.ObserverPattern.StrategyPattern;

public interface EnemyMovementStrategy {

    int rightAxis();
    int leftAxis();
    boolean bottomAxis();
    boolean speedLevel(int x);
    
}