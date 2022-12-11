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

        GhostDirection currentDirection = (GhostDirection) this.affectedGhost.getCurrentDirection();
        currentDirection.turnAround();

        this.affectedGhost.setPreviousStateTo(this);

        boolean ghostIsGoingToChaseMode = newGhostState instanceof ChasingState;
        boolean ghostWasOnGhostHouseMode = this.affectedGhost.getPreviousState() instanceof GhostHouseState;
        if(ghostWasOnGhostHouseMode && ghostIsGoingToChaseMode) {
            this.affectedGhost.setCanCurrentlyMoveToGhostHouseGateTo(true);
        }
    }
}
