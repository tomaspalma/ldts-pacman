package org.ldts.pacman.models;

public interface PacmanCollisionSpecialEntitiesObserver extends GameObserver {
    void changeOnPacmanEatingPowerPellet();
    void changeOnPacmanCollidingWithGhost();
}
