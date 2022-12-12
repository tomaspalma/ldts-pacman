package org.ldts.pacman.models.game.entities.ghost.strategies.scattering;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;

import java.util.List;

public class ScatterToBottomLeft implements ScatterStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Position bottomLeft = new Position(0, ghost.getArena().getHeight());
        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        List<Position> possibleGhostPositions = currentGhostDirection.getPossiblePositionsToMove();

        return bottomLeft.getClosestPositionFrom(possibleGhostPositions);
    }
    
}
