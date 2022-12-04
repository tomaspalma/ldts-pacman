package org.ldts.pacman.models;

public class ScatteringState extends GhostState {
    public ScatteringState(Ghost ghost) {
        super(ghost);
    }

    @Override
    public GameActions.GhostCollisionWithPacman collideWithPacmanResult() {
        return GameActions.GhostCollisionWithPacman.KILL_PACMAN;
    }

    @Override
    public void applyChangesToGhost() {

    }

    @Override
    public Position getNextPosition() {
        return this.affectedGhost.getScatterStrategy().getNextPosition(this.affectedGhost);
    }
}
