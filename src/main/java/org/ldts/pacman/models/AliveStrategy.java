package org.ldts.pacman.models;

public class AliveStrategy implements GhostStrategy {

    @Override
    public GameActions.GhostCollisionWithPacman collisionWithPacman() {
        return GameActions.GhostCollisionWithPacman.KILL_PACMAN;
    }
}
