package org.ldts.pacman.models;

import java.util.List;

public abstract class GhostDirection extends MovableEntityDirection {
    public GhostDirection(Ghost ghost) {
        super(ghost);
    }

    @Override
    public String getDrawSymbol() {
       return this.movableEntity.getDrawSymbol();
    }

    public abstract  List<Position> getPossiblePositionsToMove();

    protected abstract void turnAround();

}
