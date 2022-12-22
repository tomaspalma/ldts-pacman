package org.ldts.pacman.models.game.observer;

public interface GameObservable {
    void addObserver(GameObserver observer);
    void removeObserver(GameObserver observer);
}
