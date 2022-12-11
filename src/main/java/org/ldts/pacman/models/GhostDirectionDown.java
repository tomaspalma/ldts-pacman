package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionDown extends GhostDirection {
    public GhostDirectionDown(Ghost ghost) {
        super(ghost);
    }

    public List<Position> getPossiblePositionsToMove() {
        Position leftPosition = this.getPossiblePositionToMoveLeft();
        Position rightPosition = this.getPossiblePositionToMoveRight();
        Position downPosition = this.getPossiblePositionToMoveDown();

        List<Position> result = new ArrayList<>(Arrays.asList(leftPosition, rightPosition, downPosition));
        result.removeIf(((Ghost)this.movableEntity)::willBeInInvalidPosition);

        return result;
    }

    @Override
    protected void turnAround() {
        this.movableEntity.setCurrentDirectionTo(new GhostDirectionUp((Ghost) this.movableEntity));
    }
}
