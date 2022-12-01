package org.ldts.pacman.models;

public abstract class GhostState {
    Ghost affectedGhost;

    public GhostState(Ghost ghost) {
       this.affectedGhost = ghost;
    }
    public abstract GameActions.GhostCollisionWithPacman collideWithPacmanResult();

    public abstract void applyChangesToGhost();

    public abstract Position getNextPosition();

    public void transitionToState(GhostState newGhostState) {
        this.affectedGhost.setCurrentState(newGhostState);
        this.affectedGhost.setPreviousState(this);
    }
}
