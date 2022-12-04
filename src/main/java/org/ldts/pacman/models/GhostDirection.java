package org.ldts.pacman.models;

import java.util.List;

public abstract class GhostDirection {
    protected Ghost ghost;

    public GhostDirection(Ghost ghost) {
        this.ghost = ghost;
    }

    public abstract List<Position> getPossiblePositionsToMove(List<List<Entity>> gameGrid);

    protected boolean isPositionInvalid(Position position, List<List<Entity>> gameGrid) {
        return isOutOfBounds(position, gameGrid) && isOnObstaclePosition(position, gameGrid);
    }

    protected abstract void turnAround();

    public GhostDirection generateNextDirectionAfterChangeTo(Position nextPosition) {
       boolean movedToLeft = this.ghost.position.getX() > nextPosition.getX();
       boolean movedToRight = this.ghost.position.getX() < nextPosition.getX();
       boolean movedUp = this.ghost.position.getY() > nextPosition.getY();
       boolean movedDown = this.ghost.position.getY() < nextPosition.getY();

       if(movedToLeft) return new GhostDirectionLeft(this.ghost);
       if(movedToRight) return new GhostDirectionRight(this.ghost);
       if(movedUp) return new GhostDirectionUp(this.ghost);
       if(movedDown) return new GhostDirectionDown(this.ghost);

       return this.ghost.getCurrentDirection();
    }

    private boolean isOutOfBounds(Position position, List<List<Entity>> gameGrid) {
        boolean isOutOfBoundsHorizontally = position.getX() >= 0 && position.getX() < gameGrid.get(0).size();
        boolean isOutOfBoundsVertically = position.getY() >= 1 && position.getY() < gameGrid.size();

        return isOutOfBoundsHorizontally && isOutOfBoundsVertically;
    }

    private boolean isOnObstaclePosition(Position position, List<List<Entity>> gameGrid) {
        Entity entityInPosition = gameGrid.get(position.getY() - 1).get(position.getX());

        return entityInPosition instanceof Obstacle;
    }
}
