package org.ldts.pacman.models;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.MovableEntity;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionDown;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionLeft;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionRight;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionUp;

public abstract class MovableEntityDirection {
    protected final MovableEntity movableEntity;
    protected final Arena movableEntityArena;

    public MovableEntityDirection(MovableEntity movableEntity) {
        this.movableEntity = movableEntity;
        movableEntityArena = movableEntity.getArena();
    }
    public MovableEntityDirection generateNextDirectionAfterChangeTo(Position nextPosition) {
        boolean movedToLeft = this.movableEntity.getPosition().getX() > nextPosition.getX();
        boolean movedToRight = this.movableEntity.getPosition().getX() < nextPosition.getX();
        boolean movedUp = this.movableEntity.getPosition().getY() > nextPosition.getY();
        boolean movedDown = this.movableEntity.getPosition().getY() < nextPosition.getY();

        boolean notAlreadyInLeft = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionLeft);
        boolean notAlreadyInRight = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionRight);
        boolean notAlreadyUp = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionUp);
        boolean notAlreadyDown = !(this.movableEntity.getCurrentDirection() instanceof GhostDirectionDown);

        if (movedToLeft && notAlreadyInLeft) return new GhostDirectionLeft((Ghost) this.movableEntity);
        if (movedToRight && notAlreadyInRight) return new GhostDirectionRight((Ghost) this.movableEntity);
        if (movedUp && notAlreadyUp) return new GhostDirectionUp((Ghost) this.movableEntity);
        if (movedDown && notAlreadyDown) {
            return new GhostDirectionDown((Ghost) this.movableEntity);
        }

        return this.movableEntity.getCurrentDirection();
    }

    public abstract String getDrawSymbol();

    /*
    * Position leftPosition = new Position(ghostX - 1, ghostY, ghostArena);
        Position rightPosition = new Position(ghostX + 1, ghostY, ghostArena);
        Position downPosition = new Position(ghostX, ghostY + 1, ghostArena);*/

}
