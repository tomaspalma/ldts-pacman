package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionRight extends GhostDirection {
    public GhostDirectionRight(Ghost ghost) {
        super(ghost);
    }

    @Override
    public List<Position> getPossiblePositionsToMove(List<List<Entity>> gameGrid) {
        Position rightPosition = new Position(this.ghost.getPosition().getX() + 1, this.ghost.getPosition().getY());
        Position downPosition = new Position(this.ghost.getPosition().getX(), this.ghost.getPosition().getY() + 1);
        Position upPosition = new Position(this.ghost.getPosition().getX(), this.ghost.getPosition().getY() - 1);
        List<Position> result = new ArrayList<>(Arrays.asList(rightPosition, upPosition, downPosition));

        result.removeIf(position -> isPositionInvalid(position, gameGrid));

        return result;
    }

    @Override
    protected void turnAround() {
        this.ghost.setCurrentDirectionTo(new GhostDirectionLeft(this.ghost));
    }
}
