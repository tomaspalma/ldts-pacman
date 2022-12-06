package org.ldts.pacman.models;

import java.util.List;

public class PacmanDirectionLeft extends MovableEntityDirection {
    public PacmanDirectionLeft(MovableEntity movableEntity) {
        super(movableEntity);
    }

    @Override
    public List<Position> getPossiblePositionsToMove() {
        return null;
    }
}
