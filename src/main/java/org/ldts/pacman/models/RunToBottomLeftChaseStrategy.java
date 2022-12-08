package org.ldts.pacman.models;

public class RunToBottomLeftChaseStrategy implements ChaseStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        int arenaHeight = ghost.getArena().getHeight();

        Position desiredPosition = new Position(2, arenaHeight - 2, ghost.getArena());

        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        return desiredPosition.getClosestPositionFrom(currentGhostDirection.getPossiblePositionsToMove());
    }
}
