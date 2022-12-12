package org.ldts.pacman.models.game;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.entities.FixedEntity;

public class GhostHouseGate extends FixedEntity {
    public GhostHouseGate(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = "-";
    }
}
