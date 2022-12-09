package org.ldts.pacman.models;

public interface PacmanObserver extends GameObserver {
    public void changeOnPacmanEatFixedEdibleAt(Position position);
    public void changeOnPacmanCollisionWithGhostAt(Position position);
}
