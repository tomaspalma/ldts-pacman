package org.ldts.pacman.models.game.entities.ghost.strategies.chasing;

import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;
import org.ldts.pacman.models.game.Position;

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
