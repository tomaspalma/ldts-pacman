package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionDown extends GhostDirection {
    public GhostDirectionDown(Ghost ghost) {
        super(ghost);
    }

    @Override
    public List<Position> getPossiblePositionsToMove(List<List<Entity>> gameGrid) {
        Position leftPosition = new Position(this.ghost.getPosition().getX() - 1, this.ghost.getPosition().getY());
        Position rightPosition = new Position(this.ghost.getPosition().getX() + 1, this.ghost.getPosition().getY());
        Position downPosition = new Position(this.ghost.getPosition().getX(), this.ghost.getPosition().getY() + 1);
        List<Position> result = new ArrayList<>(Arrays.asList(leftPosition, rightPosition, downPosition));

        result.removeIf(position -> isPositionInvalid(position, gameGrid));

        return result;
    }

    @Override
    protected void turnAround() {
        this.ghost.setCurrentDirectionTo(new GhostDirectionUp(this.ghost));
    }
}
