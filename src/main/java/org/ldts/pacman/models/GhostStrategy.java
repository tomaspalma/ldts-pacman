package org.ldts.pacman.models;

public interface GhostStrategy {

    public void chaseItsTarget(Position targetPosition);

    public void vulnerableStageAction();

    public GameActions.GhostCollisionWithPacman collisionWithPacman();
}
