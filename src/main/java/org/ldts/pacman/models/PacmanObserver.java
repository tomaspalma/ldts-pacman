package org.ldts.pacman.models;

import org.ldts.pacman.models.game.Position;

public interface PacmanObserver extends GameObserver {
    public void changeOnPacmanEatFixedEdibleAt(Position position);
    public void changeOnPacmanCollisionWithGhostAt(Position position);
}
