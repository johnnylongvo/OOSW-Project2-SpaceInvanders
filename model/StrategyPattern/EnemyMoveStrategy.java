package model.StrategyPattern;

public interface EnemyMoveStrategy {
    int rightEnd();
    int leftEnd();
    boolean atBottom();
    boolean speedUp(int x);
}