package org.ldts.pacman.models.game.entities.ghost.directions;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionRight extends GhostDirection {
    public GhostDirectionRight(Ghost ghost) {
        super(ghost);
    }

    @Override
    public List<Position> getPossiblePositionsToMove() {

        Position rightPosition = this.getPossiblePositionToMoveRight();
        Position upPosition = this.getPossiblePositionToMoveUp();
        Position downPosition = this.getPossiblePositionToMoveDown();

        List<Position> result = new ArrayList<>(Arrays.asList(rightPosition, upPosition, downPosition));
        result.removeIf(((Ghost)this.movableEntity)::willBeInInvalidPosition);

        return result;
    }

    @Override
    public void turnAround() {
        this.movableEntity.setCurrentDirectionTo(new GhostDirectionLeft((Ghost) this.movableEntity));
    }
}
