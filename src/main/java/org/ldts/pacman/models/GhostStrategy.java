package org.ldts.pacman.models;

public interface GhostStrategy {
    enum AFTER_COLLISION_STATE {
        DIE,
        KILL,
    }

    public AFTER_COLLISION_STATE collisionWithPacman();
}
