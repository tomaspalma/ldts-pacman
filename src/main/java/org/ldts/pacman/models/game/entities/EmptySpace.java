package org.ldts.pacman.models.game.entities;

import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.Position;

public class EmptySpace extends FixedEntity {
    public EmptySpace(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = " ";
    }
}
