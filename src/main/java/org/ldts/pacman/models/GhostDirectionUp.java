package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionUp extends GhostDirection {
    public GhostDirectionUp(Ghost ghost) {
        super(ghost);
    }

    public List<Position> getPossiblePositionsToMove() {
        Position leftPosition = this.getPossiblePositionToMoveLeft();
        Position rightPosition = this.getPossiblePositionToMoveRight();
        Position upPosition = this.getPossiblePositionToMoveUp();
        List<Position> result = new ArrayList<>(Arrays.asList(leftPosition, rightPosition, upPosition));

        result.removeIf(position -> position.isInvalidTo(movableEntity));

        return result;
    }

    @Override
    protected void turnAround() {
        this.movableEntity.setCurrentDirectionTo(new GhostDirectionDown((Ghost) this.movableEntity));
    }
}
