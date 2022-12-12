package org.ldts.pacman.models.game.entities;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.Entity;

public abstract class FixedEntity extends Entity {
    protected FixedEntity(Position position) {
        super(position);
    }
}
