package model.StrategyPattern;

import model.EnemyComposite;
import view.GameBoard;

public class EnemyMovement implements EnemyMoveStrategy{

    private EnemyComposite enemy;

    public EnemyMovement(EnemyComposite enemy){
        this.enemy = enemy;
    }

    @Override
    public int rightEnd() {
        var rows = enemy.getRows();
        int xEnd = -100;
        for(var row: rows){
            if(row.size() == 0) continue;
            int x = row.get(row.size() - 1).x + EnemyComposite.getEnemySize();
            if(x > xEnd) xEnd = x;
        }
        return xEnd;
    }

    @Override
    public int leftEnd() {
        var rows = enemy.getRows();
        int xEnd = 9000;
        for(var row: rows){
            if(row.size() == 0) continue;
            int x = row.get(0).x;
            if(x < xEnd) xEnd = x;
        }
        return xEnd;
    }

    @Override
    public boolean atBottom() {
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
    public boolean speedUp(int x) {
        if(x >= 10){
            return true;
        } else return false;
    }
    
}