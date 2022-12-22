package org.ldts.pacman.models.game.entities.fixededibles;

import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.entities.Entity;
import org.ldts.pacman.models.game.Position;

public class FixedEdible extends Entity {
    protected int points;
    protected FixedEdible(Position position, Arena arena) {
        super(position, arena);
    }

    public int getPoints() {
        return this.points;
    }
}
