package org.ldts.pacman.models.game.entities.ghost.strategies.scattering;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;

import java.util.List;

public class ScatterToTopRight implements ScatterStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Position topRight = new Position(ghost.getArena().getWidth(), 0);
        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        List<Position> possibleGhostPositions = currentGhostDirection.getPossiblePositionsToMove();

        return topRight.getClosestPositionFrom(possibleGhostPositions);
    }
    
}
