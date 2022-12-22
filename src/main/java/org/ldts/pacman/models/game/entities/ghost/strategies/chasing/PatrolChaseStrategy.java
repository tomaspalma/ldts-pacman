package org.ldts.pacman.models.game.entities.ghost.strategies.chasing;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.Vector;
import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.entities.ghost.Blinky;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.RegularGhost;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.ChaseStrategy;
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirection;

import java.util.List;

public class PatrolChaseStrategy implements ChaseStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        Arena arena = ghost.getArena();
        PacmanDirection pacmanDirection = (PacmanDirection) arena.getPacman().getCurrentDirection();

        Position desiredPosition = pacmanDirection.getGhostTargetTileWithForwardLevel(2);
        Position blinkyPosition = this.getBlinkyPositionIn(arena);
        assert blinkyPosition != null;

        Vector desiredPosToBlinkyPosVector = blinkyPosition.getVectorTo(desiredPosition);
        desiredPosition = desiredPosToBlinkyPosVector.getPositionBasedOnSumWith(desiredPosition);

        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        List<Position> possibleGhostPositions = currentGhostDirection.getPossiblePositionsToMove();

        return desiredPosition.getClosestPositionFrom(possibleGhostPositions);
    }

    private Position getBlinkyPositionIn(Arena arena) {
        Position result = null;

        for(RegularGhost ghost: arena.getRegularGhostsList()) {
            if (ghost instanceof Blinky) {
                result = ghost.getPosition();
                break;
            }
        }

        return result;
    }
}