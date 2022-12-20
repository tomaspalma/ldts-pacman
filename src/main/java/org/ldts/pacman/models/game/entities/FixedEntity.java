package org.ldts.pacman.models.game.entities;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;

public abstract class FixedEntity extends Entity {
    protected FixedEntity(Position position, Arena arena) {
        super(position, arena);
    }
}
