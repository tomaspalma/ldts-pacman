package org.ldts.pacman.models.game.entities.ghost.strategies.chasing;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.Entity;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirection;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;

public class AmbushChaseStrategy implements ChaseStrategy {

    private Entity secondaryEntityBesidesPacman;

    @Override
    public Position getNextPosition(Ghost ghost) {
        PacmanDirection currentPacmanDirection = (PacmanDirection) ghost.getArena().getPacman().getCurrentDirection();
        Position desiredPosition = currentPacmanDirection.getGhostTargetTileWithForwardLevel(4);

        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();

        return desiredPosition.getClosestPositionFrom(currentGhostDirection.getPossiblePositionsToMove());
    }
}
