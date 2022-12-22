package org.ldts.pacman.models.game.observer;

public interface PowerPelletObservable extends GameObservable {
    void notifyObservers();
}
