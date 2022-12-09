package org.ldts.pacman.models;

public class GhostHouseDyingStrategy implements DyingStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Position ghostRevivalSpot = ghost.getArena().getGhostHouse().getGhostRevivalSpot();
        return ghostRevivalSpot;
    }
}
