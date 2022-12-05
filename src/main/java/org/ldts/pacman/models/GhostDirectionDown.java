package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionDown extends GhostDirection {
    public GhostDirectionDown(Ghost ghost) {
        super(ghost);
    }

    @Override
    public List<Position> getPossiblePositionsToMove() {
        int ghostX = this.ghost.getPosition().getX();
        int ghostY = this.ghost.getPosition().getY();
        Arena ghostArena = this.ghost.getPosition().getArena();

        Position leftPosition = new Position(ghostX - 1, ghostY, ghostArena); 
        Position rightPosition = new Position(ghostX + 1, ghostY, ghostArena);
        Position downPosition = new Position(ghostX, ghostY + 1, ghostArena);
        List<Position> result = new ArrayList<>(Arrays.asList(leftPosition, rightPosition, downPosition));

        result.removeIf(position -> position.isInvalidOnTheContextOf(ghost));

        return result;
    }

    @Override
    protected void turnAround() {
        this.ghost.setCurrentDirectionTo(new GhostDirectionUp(this.ghost));
    }
}
