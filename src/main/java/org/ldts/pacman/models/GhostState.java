package org.ldts.pacman.models;

public abstract class GhostState {
    final Ghost affectedGhost;

    public GhostState(Ghost ghost) {
       this.affectedGhost = ghost;
    }
    public abstract GameActions.GhostCollisionWithPacman collideWithPacmanResult();

    public abstract void applyChangesToGhost();

    public abstract Position getNextPosition();

    public void transitionToState(GhostState newGhostState) {
        this.affectedGhost.setCurrentStateTo(newGhostState);
        ((GhostDirection) this.affectedGhost.getCurrentDirection()).turnAround();
        this.affectedGhost.setPreviousStateTo(this);
    }
}
