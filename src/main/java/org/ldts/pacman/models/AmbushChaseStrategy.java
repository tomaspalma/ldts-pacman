package org.ldts.pacman.models;

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
