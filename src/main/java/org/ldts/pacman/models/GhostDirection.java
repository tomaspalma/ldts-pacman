package org.ldts.pacman.models;

import java.util.List;

public abstract class GhostDirection {
    protected Ghost ghost;

    public GhostDirection(Ghost ghost) {
        this.ghost = ghost;
    }

    public abstract List<Position> getPossiblePositionsToMove();

    protected abstract void turnAround();

    public GhostDirection generateNextDirectionAfterChangeTo(Position nextPosition) {
        boolean movedToLeft = this.ghost.position.getX() > nextPosition.getX();
        boolean movedToRight = this.ghost.position.getX() < nextPosition.getX();
        boolean movedUp = this.ghost.position.getY() > nextPosition.getY();
        boolean movedDown = this.ghost.position.getY() < nextPosition.getY();

        boolean notAlreadyInLeft = !(this.ghost.getCurrentDirection() instanceof GhostDirectionLeft);
        boolean notAlreadyInRight = !(this.ghost.getCurrentDirection() instanceof GhostDirectionRight);
        boolean notAlreadyUp = !(this.ghost.getCurrentDirection() instanceof GhostDirectionUp);
        boolean notAlreadyDown = !(this.ghost.getCurrentDirection() instanceof GhostDirectionDown);

        if (movedToLeft && notAlreadyInLeft) return new GhostDirectionLeft(this.ghost);
        if (movedToRight && notAlreadyInRight) return new GhostDirectionRight(this.ghost);
        if (movedUp && notAlreadyUp) return new GhostDirectionUp(this.ghost);
        if (movedDown && notAlreadyDown) return new GhostDirectionDown(this.ghost);

        return this.ghost.getCurrentDirection();
    }
}
