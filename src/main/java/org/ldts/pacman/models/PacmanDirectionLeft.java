package org.ldts.pacman.models;

public class PacmanDirectionLeft extends PacmanDirection {
    public PacmanDirectionLeft(MovableEntity movableEntity) {
        super(movableEntity);
        this.drawSymbol = "=";
    }

    @Override
    public Position getNextPosition() {
        return new Position(this.movableEntity.getPosition().getX() - 1, this.movableEntity.getPosition().getY(), this.movableEntity.getArena());
    }

    @Override
    public Position getPositionToForwardLevel(int forwardLevel) {
        int newX = this.movableEntity.getPosition().getX() - forwardLevel;
        int newY = this.movableEntity.getPosition().getY();

        return new Position(newX, newY, this.movableEntity.getArena());
    }

}
