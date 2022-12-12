package org.ldts.pacman.models.game.entities.ghost.directions;

import org.ldts.pacman.models.MovableEntityDirection;
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

    public abstract  List<Position> getPossiblePositionsToMove();

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

}
