package org.ldts.pacman.models.game.entities.ghost.directions;

import org.ldts.pacman.models.game.entities.MovableEntityDirection;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

import java.util.List;

public abstract class GhostDirection extends MovableEntityDirection {
    public GhostDirection(Ghost ghost) {
        super(ghost);
    }

    @Override
    public String getDrawSymbol() {
        return this.movableEntity.getDrawSymbol();
    }

    public abstract List<Position> getPossiblePositionsToMove();

    public abstract void turnAround();

    public Position getPossiblePositionToMoveLeft() {
        return new Position(movableEntity.getPosition().getX() - 1, movableEntity.getPosition().getY());
    }

    public Position getPossiblePositionToMoveRight() {
        return new Position(movableEntity.getPosition().getX() + 1, movableEntity.getPosition().getY());
    }

    public Position getPossiblePositionToMoveUp() {
        return new Position(movableEntity.getPosition().getX(), movableEntity.getPosition().getY() - 1);
    }

    public Position getPossiblePositionToMoveDown() {
        return new Position(movableEntity.getPosition().getX(), movableEntity.getPosition().getY() + 1);
    }

    public GhostDirection generateNextDirectionAfterChangeTo(Position nextPosition) {
        boolean movedToLeft = this.movableEntity.getPosition().getX() > nextPosition.getX();
        boolean movedToRight = this.movableEntity.getPosition().getX() < nextPosition.getX();
        boolean movedUp = this.movableEntity.getPosition().getY() > nextPosition.getY();
        boolean movedDown = this.movableEntity.getPosition().getY() < nextPosition.getY();

        boolean notAlreadyInLeft = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionLeft);
        boolean notAlreadyInRight = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionRight);
        boolean notAlreadyUp = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionUp);
        boolean notAlreadyDown = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionDown);

        if (movedToLeft && notAlreadyInLeft) return new GhostDirectionLeft((Ghost) this.movableEntity);
        if (movedToRight && notAlreadyInRight) return new GhostDirectionRight((Ghost) this.movableEntity);
        if (movedUp && notAlreadyUp) return new GhostDirectionUp((Ghost) this.movableEntity);
        if (movedDown && notAlreadyDown) {
            return new GhostDirectionDown((Ghost) this.movableEntity);
        }

        return (GhostDirection) this.movableEntity.getCurrentDirection();
    }
}
