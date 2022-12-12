package org.ldts.pacman.models.game.entities;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.FixedEntity;

public class Obstacle extends FixedEntity {

    protected Obstacle(Position position, Arena arena) {
        super(position, arena);
    }
    
}
