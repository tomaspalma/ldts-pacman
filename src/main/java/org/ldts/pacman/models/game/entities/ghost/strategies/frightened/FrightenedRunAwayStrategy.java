package org.ldts.pacman.models.game.entities.ghost.strategies.frightened;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;

import java.util.List;

public class FrightenedRunAwayStrategy implements FrightenedStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        List<Position> possiblePositions = currentGhostDirection.getPossiblePositionsToMove();

        if(possiblePositions.isEmpty()) return ghost.getPosition();

        int chosenPositionIndex = (int)Math.floor(Math.random() * (possiblePositions.size() - 1 + 1) + 0);

        return possiblePositions.get(chosenPositionIndex);
    }
}
