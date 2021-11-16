package model.Observer;

import model.Shooter;

public interface Object {
    void addShooterListener(Observer o);
    void removeShooterListener(Observer o);
    void notifyObservers(Shooter.Event event);
}