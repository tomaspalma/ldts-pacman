package org.ldts.pacman.models;

public interface GhostStrategy {

    public GameActions.GhostCollisionWithPacman collisionWithPacman();
}
