package org.ldts.pacman.models;

public class MovableEntity extends Entity {
    protected MovableEntity(Position position) {
        super(position);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
