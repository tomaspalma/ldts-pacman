package org.ldts.pacman.models;

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

    protected abstract void turnAround();

    public Position getPossiblePositionToMoveLeft() {
        return new Position(movableEntity.getPosition().getX() - 1, movableEntity.getPosition().getY(), movableEntity.getArena());
    }

    public Position getPossiblePositionToMoveRight() {
        return new Position(movableEntity.getPosition().getX() + 1, movableEntity.getPosition().getY(), movableEntity.getArena());
    }

    public Position getPossiblePositionToMoveUp() {
        return new Position(movableEntity.getPosition().getX(), movableEntity.getPosition().getY() - 1, movableEntity.getArena());
    }

    public Position getPossiblePositionToMoveDown() {
        return new Position(movableEntity.getPosition().getX(), movableEntity.getPosition().getY() + 1, movableEntity.getArena());
    }

}
