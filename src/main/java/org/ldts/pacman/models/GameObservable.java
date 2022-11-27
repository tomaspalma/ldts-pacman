package org.ldts.pacman.models;

public interface GameObservable {
    public void addObserver(GameObserver observer);
    public void removeObserver(GameObserver observer);
    public void notifyObservers();
}
