package org.ldts.pacman.models;

import java.util.List;
import java.util.Objects;

public class FrightenedRunAwayStrategy implements FrightenedStrategy {

    @Override
    public void execute(Ghost ghost) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Position getNextPosition(Ghost ghost) {
        List<Position> possiblePositions = ghost.getCurrentDirection().getPossiblePositionsToMove();

        if(possiblePositions.isEmpty()) return ghost.getPosition();

        int chosenPositionIndex = (int)Math.floor(Math.random() * (possiblePositions.size() - 1 - 0 + 1) + 0);

        if(possiblePositions.get(chosenPositionIndex) == null) {
            System.out.println("fsdfhjuskdfhsjkh");
        }

        return possiblePositions.get(chosenPositionIndex);
    }
}
