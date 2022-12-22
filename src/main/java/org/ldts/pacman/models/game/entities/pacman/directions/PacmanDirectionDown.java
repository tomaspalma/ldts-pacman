package org.ldts.pacman.models.game.entities.pacman.directions;

import org.ldts.pacman.models.game.entities.MovableEntity;
import org.ldts.pacman.models.game.Position;

public class PacmanDirectionDown extends PacmanDirection {
    public PacmanDirectionDown(MovableEntity movableEntity) {
        super(movableEntity);
        this.drawSymbol = ">";
    }

    @Override
    public Position getNextPosition() {
        int x = this.movableEntity.getPosition().getX();
        int y = this.movableEntity.getPosition().getY();

        return new Position(x, y + 1);
    }

    @Override
    public Position getGhostTargetTileWithForwardLevel(int forwardLevel) {
        int newX = this.movableEntity.getPosition().getX();
        int newY = this.movableEntity.getPosition().getY() + forwardLevel;

        return new Position(newX, newY);
    }
}
