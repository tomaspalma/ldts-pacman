package org.ldts.pacman.models.game.entities.fixededibles;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.entities.Entity;
import org.ldts.pacman.models.game.Position;

public class FixedEdible extends Entity {
    protected FixedEdible(Position position, Arena arena) {
        super(position, arena);
    }
}