package org.ldts.pacman.models.game.entities.ghost.directions;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

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
        result.removeIf(((Ghost)this.movableEntity)::willBeInInvalidPosition);

        return result;
    }

    @Override
    public void turnAround() {
        this.movableEntity.setCurrentDirectionTo(new GhostDirectionDown((Ghost) this.movableEntity));
    }
}
