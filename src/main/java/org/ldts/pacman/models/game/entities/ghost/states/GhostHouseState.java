package org.ldts.pacman.models.game.entities.ghost.states;

import org.ldts.pacman.models.game.GameActions;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

public class GhostHouseState extends GhostState {
    public GhostHouseState(Ghost ghost) {
        super(ghost);
        this.canMoveOutsideGhostHouse = false;
    }

    @Override
    public GameActions.GhostCollisionWithPacman collideWithPacmanResult() {
        return GameActions.GhostCollisionWithPacman.NONE;
    }

    @Override
    public void applyChangesToGhost() {

    }

    @Override
    public Position getNextPosition() {
        return this.affectedGhost.getPosition();
    }
}
