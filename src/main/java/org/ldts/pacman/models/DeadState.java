package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class DeadState extends GhostState {
    public DeadState(Ghost ghost) {
        super(ghost);
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
