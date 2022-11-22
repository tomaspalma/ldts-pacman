package org.ldts.pacman.models;

public class VulnerableStrategy implements GhostStrategy{
    @Override
    public GameActions.GhostCollisionWithPacman collisionWithPacman() {
        return GameActions.GhostCollisionWithPacman.KILL_GHOST;
    }
}
