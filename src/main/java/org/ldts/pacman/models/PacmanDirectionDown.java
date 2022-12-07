package org.ldts.pacman.models;

import java.util.List;

public class PacmanDirectionDown extends PacmanDirection {
    public PacmanDirectionDown(MovableEntity movableEntity) {
        super(movableEntity);
        this.drawSymbol = ">";
    }

    @Override
    public Position getNextPosition() {
        return new Position(this.movableEntity.position.getX(), this.movableEntity.position.getY() + 1, this.movableEntity.getArena());
    }
}
