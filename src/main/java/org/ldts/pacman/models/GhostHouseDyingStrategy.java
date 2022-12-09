package org.ldts.pacman.models;

import java.util.List;

public class GhostHouseDyingStrategy implements DyingStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Position ghostRevivalSpot = ghost.getArena().getGhostHouse().getGhostRevivalSpot();
        return ghostRevivalSpot;
    }
}
