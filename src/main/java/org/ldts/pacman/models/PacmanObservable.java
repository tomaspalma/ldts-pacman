package org.ldts.pacman.models;

public interface PacmanObservable extends GameObservable {
    void notifyObserversItAteFixedEdibleAt(Position position);
    void notifyObserversItCollidedWithGhostAt(Position position);
}
