package org.ldts.pacman.models;

public interface PacmanObserver {
    public void handlePacmanEatFixedEdible();
    public void handlePacmanCollisionWithGhost();
}
