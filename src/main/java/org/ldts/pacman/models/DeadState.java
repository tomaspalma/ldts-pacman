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
        return new Position(3, 3, this.affectedGhost.getPosition().getArena());
    }
}
