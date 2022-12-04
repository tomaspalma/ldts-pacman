package org.ldts.pacman.models;

import java.util.List;

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
        return new Position(0, 0, this.affectedGhost.getPosition().getArena());
    }
}
