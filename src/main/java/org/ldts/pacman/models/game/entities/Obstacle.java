package org.ldts.pacman.models.game.entities;

import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.Position;

public class Obstacle extends FixedEntity {

    protected Obstacle(Position position, Arena arena) {
        super(position, arena);
    }
    
}
