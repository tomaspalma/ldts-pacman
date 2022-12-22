package org.ldts.pacman.models.game.observer;

import org.ldts.pacman.models.game.Position;

public interface PacmanObserver extends GameObserver {
    void changeOnPacmanEatFixedEdibleAt(Position position);
    void changeOnPacmanCollisionWithGhostAt(Position position) throws InterruptedException;
}
