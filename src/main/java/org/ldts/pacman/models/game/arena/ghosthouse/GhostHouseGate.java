package org.ldts.pacman.models.game.arena.ghosthouse;

import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.FixedEntity;

public class GhostHouseGate extends FixedEntity {
    public GhostHouseGate(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = "-";
    }
}
