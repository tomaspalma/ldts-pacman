package org.ldts.pacman.models;

import java.util.List;

public abstract class GhostDirection extends MovableEntityDirection {
    public GhostDirection(Ghost ghost) {
        super(ghost);
    }

    public abstract List<Position> getPossiblePositionsToMove();

    protected abstract void turnAround();

    public GhostDirection generateNextDirectionAfterChangeTo(Position nextPosition) {
        boolean movedToLeft = this.movableEntity.position.getX() > nextPosition.getX();
        boolean movedToRight = this.movableEntity.position.getX() < nextPosition.getX();
        boolean movedUp = this.movableEntity.position.getY() > nextPosition.getY();
        boolean movedDown = this.movableEntity.position.getY() < nextPosition.getY();

        boolean notAlreadyInLeft = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionLeft);
        boolean notAlreadyInRight = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionRight);
        boolean notAlreadyUp = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionUp);
        boolean notAlreadyDown = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionDown);

        if (movedToLeft && notAlreadyInLeft) return new GhostDirectionLeft((Ghost) this.movableEntity);
        if (movedToRight && notAlreadyInRight) return new GhostDirectionRight((Ghost) this.movableEntity);
        if (movedUp && notAlreadyUp) return new GhostDirectionUp((Ghost) this.movableEntity);
        if (movedDown && notAlreadyDown) return new GhostDirectionDown((Ghost) this.movableEntity);

        return (GhostDirection) this.movableEntity.getCurrentDirection();
    }
}
