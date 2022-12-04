package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.List;

public class DeadState extends GhostState {
    public DeadState(Ghost ghost) {
        super(ghost);
    }

    @Override
    public GameActions.GhostCollisionWithPacman collideWithPacmanResult() {
        return null;
    }

    @Override
    public void applyChangesToGhost() {
        this.affectedGhost.setColor(TextColor.ANSI.WHITE);
    }

    @Override
    public Position getNextPosition(Position pacmanPosition, List<List<Entity>> gameGrid) {
        return new Position(0, 0);
    }
}
