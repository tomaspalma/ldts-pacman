package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.List;

public class ChasingState extends GhostState {

    public ChasingState(Ghost ghost) {
        super(ghost);
    }

    @Override
    public GameActions.GhostCollisionWithPacman collideWithPacmanResult() {
        return GameActions.GhostCollisionWithPacman.KILL_PACMAN;
    }

    @Override
    public void applyChangesToGhost() {
        this.affectedGhost.setColor(TextColor.ANSI.WHITE);
    }

    @Override
    public Position getNextPosition(Position pacmanPosition, List<List<Entity>> gameGrid) {
        return this.affectedGhost.getChaseStrategy().getNextPosition(this.affectedGhost, pacmanPosition, gameGrid);
    }
}
