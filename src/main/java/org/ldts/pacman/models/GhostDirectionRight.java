package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionRight extends GhostDirection {
    public GhostDirectionRight(Ghost ghost) {
        super(ghost);
    }

    public List<Position> getPossiblePositionsToMove() {

        Position rightPosition = this.getPossiblePositionToMoveRight();
        Position downPosition = this.getPossiblePositionToMoveDown();
        Position upPosition = this.getPossiblePositionToMoveUp();
        List<Position> result = new ArrayList<>(Arrays.asList(rightPosition, upPosition, downPosition));

        result.removeIf(position -> position.isInvalidTo(movableEntity));

        return result;
    }

    @Override
    protected void turnAround() {
        this.movableEntity.setCurrentDirectionTo(new GhostDirectionLeft((Ghost) this.movableEntity));
    }
}
