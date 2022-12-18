package org.ldts.pacman.models.game.entities.ghost.directions;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionLeft extends GhostDirection {
    public GhostDirectionLeft(Ghost ghost) {
        super(ghost);
    }

    public List<Position> getPossiblePositionsToMove() {

        Position leftPosition = this.getPossiblePositionToMoveLeft();
        Position upPosition = this.getPossiblePositionToMoveUp();
        Position downPosition = this.getPossiblePositionToMoveDown();
        List<Position> result = new ArrayList<>(Arrays.asList(leftPosition, upPosition, downPosition));

        result.removeIf(((Ghost)this.movableEntity)::willBeInInvalidPosition);

        return result;
    }

    @Override
    public void turnAround() {
        this.movableEntity.setCurrentDirectionTo(new GhostDirectionRight((Ghost) this.movableEntity));
    }
}
