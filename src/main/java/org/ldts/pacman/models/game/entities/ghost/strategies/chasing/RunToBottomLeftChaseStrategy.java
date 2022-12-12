package org.ldts.pacman.models.game.entities.ghost.strategies.chasing;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.ChaseStrategy;

public class RunToBottomLeftChaseStrategy implements ChaseStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        int arenaHeight = ghost.getArena().getHeight();

        Position desiredPosition = new Position(2, arenaHeight - 2);

        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        return desiredPosition.getClosestPositionFrom(currentGhostDirection.getPossiblePositionsToMove());
    }
}
