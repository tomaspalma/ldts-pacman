package org.ldts.pacman.models;

import java.util.List;
import java.util.Objects;

public class FrightenedRunAwayStrategy implements FrightenedStrategy {

    @Override
    public void execute(Ghost ghost) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Position getNextPosition(Ghost ghost, Position pacmanPosition, List<List<Entity>> gameGrid) {
        List<Position> possiblePositions = ghost.getCurrentDirection().getPossiblePositionsToMove(gameGrid);
        possiblePositions.removeIf(Objects::isNull);

        int chosenPositionIndex = (int)Math.floor(Math.random()*(possiblePositions.size() - 1 - 0 + 1) + 0);

        return possiblePositions.get(chosenPositionIndex);
    }
}
