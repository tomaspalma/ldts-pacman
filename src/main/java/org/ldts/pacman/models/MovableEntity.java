package org.ldts.pacman.models;

public abstract class MovableEntity extends Entity {
    protected MovableEntityDirection currentDirection;
    protected MovableEntity(Position position, Arena arena) {
        super(position, arena);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public MovableEntityDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirectionTo(MovableEntityDirection currentDirection) {
        this.drawSymbol = currentDirection.getDrawSymbol();
        this.currentDirection = currentDirection;
    }
}
