package org.ldts.pacman.models;

import java.util.List;

public class PacmanDirectionRight extends MovableEntityDirection {
    public PacmanDirectionRight(MovableEntity movableEntity) {
        super(movableEntity);
    }

    @Override
    public List<Position> getPossiblePositionsToMove() {
        return null;
    }
}
