package org.ldts.pacman.models.game.entities.pacman.directions;

import org.ldts.pacman.models.game.entities.MovableEntity;
import org.ldts.pacman.models.MovableEntityDirection;
import org.ldts.pacman.models.game.Position;

public abstract class PacmanDirection extends MovableEntityDirection {
    protected String drawSymbol;
    public PacmanDirection(MovableEntity movableEntity) {
        super(movableEntity);
    }

    public abstract Position getNextPosition();

    @Override
    public String getDrawSymbol() {
        return this.drawSymbol;
    }

    public abstract Position getGhostTargetTileWithForwardLevel(int forwardLevel);

}
