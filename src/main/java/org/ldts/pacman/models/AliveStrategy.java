package org.ldts.pacman.models;

public class AliveStrategy implements GhostStrategy {

    @Override
    public GameActions.ghostCollisionWithPacman collisionWithPacman() {
        return GameActions.ghostCollisionWithPacman.KILL_PACMAN;
    }
}
