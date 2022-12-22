package org.ldts.pacman.models.game.entities;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.arena.Arena;
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
    public abstract String getDrawSymbol();
}
