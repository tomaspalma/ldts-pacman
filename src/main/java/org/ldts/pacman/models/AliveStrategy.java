package org.ldts.pacman.models;

public class AliveStrategy implements GhostStrategy {

    @Override
    public AFTER_COLLISION_STATE collisionWithPacman() {
        return AFTER_COLLISION_STATE.KILL;
    }
}
