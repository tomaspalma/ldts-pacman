package org.ldts.pacman.models;

import java.util.List;

public class ScatterToTopLeft implements ScatterStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Position topLeft = new Position(0, 0, ghost.getArena());
        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        List<Position> possibleGhostPositions = currentGhostDirection.getPossiblePositionsToMove();

        return topLeft.getClosestPositionFrom(possibleGhostPositions);
    }
    
}
