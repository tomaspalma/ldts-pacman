package org.ldts.pacman.models;

import java.util.List;

public abstract class GhostState {
    Ghost affectedGhost;

    public GhostState(Ghost ghost) {
       this.affectedGhost = ghost;
    }
    public abstract GameActions.GhostCollisionWithPacman collideWithPacmanResult();

    public abstract void applyChangesToGhost();

    public abstract Position getNextPosition(Position pacmanPosition, List<List<Entity>> gameGrid);

    public void transitionToState(GhostState newGhostState) {
        this.affectedGhost.setCurrentStateTo(newGhostState);
        this.affectedGhost.setPreviousStateTo(this);
    }
}
