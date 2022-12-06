package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionUp extends GhostDirection {
    public GhostDirectionUp(Ghost ghost) {
        super(ghost);
    }

    @Override
    public List<Position> getPossiblePositionsToMove() {
        Position leftPosition = new Position(movableEntityX - 1, movableEntityY, movableEntityArena);
        Position rightPosition = new Position(movableEntityX + 1, movableEntityY, movableEntityArena);
        Position upPosition = new Position(movableEntityX, movableEntityY - 1, movableEntityArena);
        List<Position> result = new ArrayList<>(Arrays.asList(leftPosition, rightPosition, upPosition));

        result.removeIf(position -> position.isInvalidOnTheContextOf((Ghost) movableEntity));

        return result;
    }

    @Override
    protected void turnAround() {
        this.movableEntity.setCurrentDirectionTo(new GhostDirectionDown((Ghost) this.movableEntity));
    }
}
