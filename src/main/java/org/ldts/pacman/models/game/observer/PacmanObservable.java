package org.ldts.pacman.models.game.observer;

import org.ldts.pacman.models.game.Position;

public interface PacmanObservable extends GameObservable {
    void notifyObserversItAteFixedEdibleAt(Position position);
    void notifyObserversItCollidedWithGhostAt(Position position) throws InterruptedException;
}
