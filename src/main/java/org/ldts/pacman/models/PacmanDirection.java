package org.ldts.pacman.models;

public abstract class PacmanDirection extends MovableEntityDirection {
    protected String drawSymbol;
    public PacmanDirection(MovableEntity movableEntity) {
        super(movableEntity);
    }

    public abstract Position getNextPosition();

    @Override
    public String getDrawSymbol() {
        return this.drawSymbol;
    }
}
