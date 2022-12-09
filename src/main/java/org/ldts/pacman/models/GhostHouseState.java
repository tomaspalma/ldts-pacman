package org.ldts.pacman.models;

public class GhostHouseState extends GhostState {
    public GhostHouseState(Ghost ghost) {
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
        return this.affectedGhost.getPosition();
    }
}
