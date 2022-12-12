package org.ldts.pacman.models.game.entities.pacman.directions;

import org.ldts.pacman.models.game.entities.MovableEntity;
import org.ldts.pacman.models.game.Position;

public class PacmanDirectionLeft extends PacmanDirection {
    public PacmanDirectionLeft(MovableEntity movableEntity) {
        super(movableEntity);
        this.drawSymbol = "=";
    }

    @Override
    public Position getNextPosition() {
        return new Position(this.movableEntity.getPosition().getX() - 1, this.movableEntity.getPosition().getY());
    }

    @Override
    public Position getGhostTargetTileWithForwardLevel(int forwardLevel) {
        int newX = this.movableEntity.getPosition().getX() - forwardLevel;
        int newY = this.movableEntity.getPosition().getY();

        return new Position(newX, newY);
    }

}
