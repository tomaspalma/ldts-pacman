package org.ldts.pacman.models;

import java.util.List;

public class PacmanDirectionUp extends MovableEntityDirection {
    public PacmanDirectionUp(MovableEntity movableEntity) {
        super(movableEntity);
    }

    @Override
    public List<Position> getPossiblePositionsToMove() {
        return null;
    }
}
