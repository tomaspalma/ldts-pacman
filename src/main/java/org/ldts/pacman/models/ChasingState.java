package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

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
        this.affectedGhost.setColor(this.affectedGhost.getOriginalColor());
    }

    @Override
    public Position getNextPosition() {
        return this.affectedGhost.getChaseStrategy().getNextPosition(this.affectedGhost);
    }
}
