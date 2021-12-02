package model.ObserverPattern;

import model.Shooter;

public interface Object {

    void addShooterListener(Observer o);
    void removeShooterListener(Observer o);
    void notifyShooterObservers(Shooter.Event event);

}