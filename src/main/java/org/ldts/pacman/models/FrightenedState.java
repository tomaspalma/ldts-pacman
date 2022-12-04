package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.List;

public class FrightenedState extends GhostState {
    public FrightenedState(Ghost ghost) {
        super(ghost);
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
    public Position getNextPosition(Position pacmanPosition, List<List<Entity>> gameGrid) {
        return this.affectedGhost.getFrightenedStrategy().getNextPosition(this.affectedGhost, pacmanPosition, gameGrid);
    }
}
