package org.ldts.pacman.models;

import java.util.List;

public class ScatterToTopRight implements ScatterStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Position topRight = new Position(ghost.getArena().getWidth(), 0, ghost.getArena());
        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        List<Position> possibleGhostPositions = currentGhostDirection.getPossiblePositionsToMove();

        return topRight.getClosestPositionFrom(possibleGhostPositions);
    }
    
}
