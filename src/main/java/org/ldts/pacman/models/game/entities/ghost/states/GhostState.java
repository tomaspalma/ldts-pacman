package org.ldts.pacman.models.game.entities.ghost.states;

import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;

public abstract class GhostState {
    protected final Ghost affectedGhost;
    protected boolean canMoveOutsideGhostHouse;

    public GhostState(Ghost ghost) {
       this.affectedGhost = ghost;
    }
    public abstract GameActions.GhostCollisionWithPacman collideWithPacmanResult();

    public abstract void applyChangesToGhost();

    public boolean canMoveOutsideGhostHouse() {
        return canMoveOutsideGhostHouse;
    }

    public abstract Position getNextPosition();

    public void transitionToState(GhostState newGhostState) {
        this.affectedGhost.setCurrentStateTo(newGhostState);

        GhostDirection currentDirection = (GhostDirection) this.affectedGhost.getCurrentDirection();
        currentDirection.turnAround();

        this.affectedGhost.setPreviousStateTo(this);
    }
}
