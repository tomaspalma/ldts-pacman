package org.ldts.pacman.models;

public interface GameObservable {
    void addObserver(EatenPowerPelletObserver observer);
    void removeObserver(EatenPowerPelletObserver observer);
    void notifyObservers();
}
