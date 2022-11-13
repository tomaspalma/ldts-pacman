package org.ldts.pacman.models;

public abstract class Entity {
    protected Position position;

    protected Entity(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }
}
