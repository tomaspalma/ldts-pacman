package org.ldts.pacman.models;

import java.util.List;

public class AgressiveChaseStrategy implements ChaseStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Position pacmanPosition = ghost.getPosition().getArena().getPacman().getPosition();
        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();

        List<Position> possibleGhostPositions = currentGhostDirection.getPossiblePositionsToMove();

        return pacmanPosition.getClosestPositionFrom(possibleGhostPositions);

    }
    
}
