package org.ldts.pacman.models;

public interface PowerPelletObservable extends GameObservable {
    void notifyObservers();
}
