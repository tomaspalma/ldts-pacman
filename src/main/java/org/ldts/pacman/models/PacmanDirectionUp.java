package org.ldts.pacman.models;

import java.util.List;

public class PacmanDirectionUp extends PacmanDirection {
    public PacmanDirectionUp(MovableEntity movableEntity) {
        super(movableEntity);
        this.drawSymbol = "@";
    }

    @Override
    public Position getNextPosition() {
        return new Position(this.movableEntity.getPosition().getX(), this.movableEntity.getPosition().getY() - 1, this.movableEntity.getArena());
    }

}
