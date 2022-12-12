package org.ldts.pacman.models.game.entities.ghost.states;

import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

public class ChasingState extends GhostState {

    public ChasingState(Ghost ghost) {
        super(ghost);
        this.canMoveOutsideGhostHouse = true;
    }

    @Override
    public GameActions.GhostCollisionWithPacman collideWithPacmanResult() {
        return GameActions.GhostCollisionWithPacman.KILL_PACMAN;
    }

    @Override
    public void applyChangesToGhost() {
        this.affectedGhost.setColor(this.affectedGhost.getOriginalColor());
    }

    @Override
    public Position getNextPosition() {
        return this.affectedGhost.getChaseStrategy().getNextPosition(this.affectedGhost);
    }
}
