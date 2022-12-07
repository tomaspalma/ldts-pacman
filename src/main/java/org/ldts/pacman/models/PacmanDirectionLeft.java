package org.ldts.pacman.models;

import java.util.List;

public class PacmanDirectionLeft extends PacmanDirection {
    public PacmanDirectionLeft(MovableEntity movableEntity) {
        super(movableEntity);
        this.drawSymbol = "=";
    }

    @Override
    public Position getNextPosition() {
        return new Position(this.movableEntity.getPosition().getX() - 1, this.movableEntity.getPosition().getY(), this.movableEntity.getArena());
    }

}
