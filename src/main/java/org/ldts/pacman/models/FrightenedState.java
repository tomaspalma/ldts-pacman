package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

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
    public Position getNextPosition() {
        return this.affectedGhost.getFrightenedStrategy().getNextPosition(this.affectedGhost);
    }
}
