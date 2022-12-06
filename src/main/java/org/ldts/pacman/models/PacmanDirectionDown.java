package org.ldts.pacman.models;

import java.util.List;

public class PacmanDirectionDown extends MovableEntityDirection {
    public PacmanDirectionDown(MovableEntity movableEntity) {
        super(movableEntity);
    }

    @Override
    public List<Position> getPossiblePositionsToMove() {
        return null;
    }
}
