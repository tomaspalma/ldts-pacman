package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GhostDirectionLeft extends GhostDirection {
    public GhostDirectionLeft(Ghost ghost) {
        super(ghost);
    }

    @Override
    public List<Position> getPossiblePositionsToMove() {

        int ghostX = this.ghost.getPosition().getX();
        int ghostY = this.ghost.getPosition().getY();
        Arena ghostArena = this.ghost.getPosition().getArena();

        Position leftPosition = new Position(ghostX - 1, ghostY, ghostArena);
        Position downPosition = new Position(ghostX, ghostY + 1, ghostArena);
        Position upPosition = new Position(ghostX, ghostY - 1, ghostArena);
        List<Position> result = new ArrayList<>(Arrays.asList(leftPosition, upPosition, downPosition));

        result.removeIf(position -> position.isInvalidOnTheContextOf(ghost));

        if(result == null) {
            System.out.println("cummy whammy");
        }

        return result;
    }

    @Override
    protected void turnAround() {
        this.ghost.setCurrentDirectionTo(new GhostDirectionRight(this.ghost));
    }
}
