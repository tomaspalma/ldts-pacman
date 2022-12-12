package org.ldts.pacman.models.game.entities.ghost.states;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

public class DeadState extends GhostState {
    public DeadState(Ghost ghost) {
        super(ghost);
        this.canMoveOutsideGhostHouse = false;
    }

    @Override
    public GameActions.GhostCollisionWithPacman collideWithPacmanResult() {
        return GameActions.GhostCollisionWithPacman.NONE;
    }

    @Override
    public void applyChangesToGhost() {
        this.affectedGhost.setColor(TextColor.ANSI.WHITE);
    }

    @Override
    public Position getNextPosition() {
        return this.affectedGhost.getDyingStrategy().getNextPosition(this.affectedGhost);
    }
}
