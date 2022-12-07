package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionLeft extends GhostDirection {
    public GhostDirectionLeft(Ghost ghost) {
        super(ghost);
    }

    public List<Position> getPossiblePositionsToMove() {

        Position leftPosition = this.getPossiblePositionToMoveLeft();
        Position downPosition = this.getPossiblePositionToMoveDown();
        Position upPosition = this.getPossiblePositionToMoveUp();
        List<Position> result = new ArrayList<>(Arrays.asList(leftPosition, upPosition, downPosition));

        result.removeIf(position -> position.isInvalidTo(this.movableEntity));

        return result;
    }

    @Override
    protected void turnAround() {
        this.movableEntity.setCurrentDirectionTo(new GhostDirectionRight((Ghost) this.movableEntity));
    }
}
