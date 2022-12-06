package org.ldts.pacman.models;

import java.util.List;

public class AgressiveChaseStrategy implements ChaseStrategy {

    @Override
    public void execute(Ghost ghost) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Position getNextPosition(Ghost ghost) {
        Position pacmanPosition = ghost.getPosition().getArena().getPacman().getPosition();
        List<Position> possibleGhostPositions = ghost.getCurrentDirection().getPossiblePositionsToMove();

        double currentMinDirection = Double.MAX_VALUE;
        Position closestPossiblePosition = null;

        for(Position position: possibleGhostPositions) {
            if(position.getDistanceTo(pacmanPosition) < currentMinDirection) {
                currentMinDirection = position.getDistanceTo(pacmanPosition);
                closestPossiblePosition = position;
            }
        }

        return closestPossiblePosition;
    }
    
}
