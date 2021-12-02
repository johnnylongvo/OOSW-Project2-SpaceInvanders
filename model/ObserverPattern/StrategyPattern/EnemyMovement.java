package model.ObserverPattern.StrategyPattern;

import model.EnemyComposite;
import view.GameBoard;

public class EnemyMovement implements EnemyMovementStrategy{

    private EnemyComposite enemy;

    public EnemyMovement(EnemyComposite enemy){
        this.enemy = enemy;
    }

    @Override
    public boolean bottomAxis() {
        var rows = enemy.getRows();
        for(var row: rows){
            for(var e: row){
                if(e.y >= GameBoard.HEIGHT - EnemyComposite.getEnemySize()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int rightAxis() {
        var rows = enemy.getRows();
        int xAxis = -100;
        for(var row: rows){
            if(row.size() == 0) continue;
            int x = row.get(row.size() - 1).x + EnemyComposite.getEnemySize();
            if(x > xAxis) xAxis = x;
        }
        return xAxis;
    }

    @Override
    public int leftAxis() {
        var rows = enemy.getRows();
        int xAxis = 9000;
        for(var row: rows){
            if(row.size() == 0) continue;
            int x = row.get(0).x;
            if(x < xAxis) xAxis = x;
        }
        return xAxis;
    }

    @Override
    public boolean speedLevel(int x) {
        if(x >= 75){
            return true;
        } else 
        return false;
    }    
}