package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionRight extends GhostDirection {
    public GhostDirectionRight(Ghost ghost) {
        super(ghost);
    }

    @Override
    public List<Position> getPossiblePositionsToMove() {
        int ghostX = this.ghost.getPosition().getX();
        int ghostY = this.ghost.getPosition().getY();
        Arena ghostArena = this.ghost.getPosition().getArena();
        
        Position rightPosition = new Position(ghostX + 1, ghostY, ghostArena);
        Position downPosition = new Position(ghostX, ghostY + 1, ghostArena);
        Position upPosition = new Position(ghostX, ghostY - 1, ghostArena);
        List<Position> result = new ArrayList<>(Arrays.asList(rightPosition, upPosition, downPosition));

        result.removeIf(position -> position.isInvalidOnTheContextOf(ghost));

        return result;
    }

    @Override
    protected void turnAround() {
        this.ghost.setCurrentDirectionTo(new GhostDirectionLeft(this.ghost));
    }
}
