package org.ldts.pacman.models;

import org.ldts.pacman.models.game.Position;

public interface PacmanObserver extends GameObserver {
    void changeOnPacmanEatFixedEdibleAt(Position position);
    void changeOnPacmanCollisionWithGhostAt(Position position);
}
