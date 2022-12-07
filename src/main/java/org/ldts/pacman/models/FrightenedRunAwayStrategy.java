package org.ldts.pacman.models;

import java.util.List;

public class FrightenedRunAwayStrategy implements FrightenedStrategy {

    @Override
    public void execute(Ghost ghost) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Position getNextPosition(Ghost ghost) {
        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        List<Position> possiblePositions = currentGhostDirection.getPossiblePositionsToMove();

        if(possiblePositions.isEmpty()) return ghost.getPosition();

        int chosenPositionIndex = (int)Math.floor(Math.random() * (possiblePositions.size() - 1 + 1) + 0);

        return possiblePositions.get(chosenPositionIndex);
    }
}
