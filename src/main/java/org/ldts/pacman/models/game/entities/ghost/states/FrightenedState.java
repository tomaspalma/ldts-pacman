package org.ldts.pacman.models.game.entities.ghost.states;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.game.GameActions;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

public class FrightenedState extends GhostState {
    public FrightenedState(Ghost ghost) {
        super(ghost);
        this.canMoveOutsideGhostHouse = false;
    }

    @Override
    public GameActions.GhostCollisionWithPacman collideWithPacmanResult() {
        return GameActions.GhostCollisionWithPacman.KILL_GHOST;
    }

    @Override
    public void applyChangesToGhost() {
        this.affectedGhost.setColor(TextColor.ANSI.BLUE);
    }

    @Override
    public Position getNextPosition() {
        return this.affectedGhost.getFrightenedStrategy().getNextPosition(this.affectedGhost);
    }
}
