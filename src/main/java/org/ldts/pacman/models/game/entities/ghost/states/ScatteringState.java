package org.ldts.pacman.models.game.entities.ghost.states;

import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

public class ScatteringState extends GhostState {
    public ScatteringState(Ghost ghost) {
        super(ghost);
        this.canMoveOutsideGhostHouse = true;
    }

    @Override
    public GameActions.GhostCollisionWithPacman collideWithPacmanResult() {
        return GameActions.GhostCollisionWithPacman.KILL_PACMAN;
    }

    @Override
    public void applyChangesToGhost() {

    }

    @Override
    public Position getNextPosition() {
        return this.affectedGhost.getScatterStrategy().getNextPosition(this.affectedGhost);
    }
}
