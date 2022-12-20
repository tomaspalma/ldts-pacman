package org.ldts.pacman.models;

import org.ldts.pacman.models.game.Position;

public interface PacmanObservable extends GameObservable {
    void notifyObserversItAteFixedEdibleAt(Position position);
    void notifyObserversItCollidedWithGhostAt(Position position) throws InterruptedException;
}
