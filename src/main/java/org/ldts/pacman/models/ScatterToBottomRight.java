package org.ldts.pacman.models;

import java.util.List;

public class ScatterToBottomRight implements ScatterStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Position bottomRight = new Position(ghost.getArena().getWidth(), ghost.getArena().getHeight(), ghost.getArena());
        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        List<Position> possibleGhostPositions = currentGhostDirection.getPossiblePositionsToMove();

        return bottomRight.getClosestPositionFrom(possibleGhostPositions);
    }
    
}
