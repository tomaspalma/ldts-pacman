package org.ldts.pacman.models.game.entities.pacman.directions;

import org.ldts.pacman.models.game.entities.MovableEntity;
import org.ldts.pacman.models.game.Position;

public class PacmanDirectionUp extends PacmanDirection {
    public PacmanDirectionUp(MovableEntity movableEntity) {
        super(movableEntity);
        this.drawSymbol = "@";
    }

    @Override
    public Position getNextPosition() {
        return new Position(this.movableEntity.getPosition().getX(), this.movableEntity.getPosition().getY() - 1);
    }

    @Override
    public Position getGhostTargetTileWithForwardLevel(int forwardLevel) {
        int newY = this.movableEntity.getPosition().getY() - forwardLevel;
        int newX = this.movableEntity.getPosition().getX() - forwardLevel;

        return new Position(newX, newY);
    }
}
