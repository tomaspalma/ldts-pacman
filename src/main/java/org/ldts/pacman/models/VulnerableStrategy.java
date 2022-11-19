package org.ldts.pacman.models;

public class VulnerableStrategy implements GhostStrategy{
    @Override
    public GameActions.ghostCollisionWithPacman collisionWithPacman() {
        return GameActions.ghostCollisionWithPacman.KILL_GHOST;
    }

}
