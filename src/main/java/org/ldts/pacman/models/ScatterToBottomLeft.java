package org.ldts.pacman.models;

import java.util.List;

public class ScatterToBottomLeft implements ScatterStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Position bottomLeft = new Position(0, ghost.getArena().getHeight(), ghost.getArena());
        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        List<Position> possibleGhostPositions = currentGhostDirection.getPossiblePositionsToMove();

        return bottomLeft.getClosestPositionFrom(possibleGhostPositions);
    }
    
}
