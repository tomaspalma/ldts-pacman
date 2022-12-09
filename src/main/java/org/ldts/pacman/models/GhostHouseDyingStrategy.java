package org.ldts.pacman.models;

public class GhostHouseDyingStrategy implements DyingStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        return ghost.getArena().getGhostHouse().getAvailablePosition();
    }
}
