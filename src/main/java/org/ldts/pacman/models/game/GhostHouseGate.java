package org.ldts.pacman.models.game;

import org.ldts.pacman.models.game.entities.FixedEntity;

public class GhostHouseGate extends FixedEntity {
    public GhostHouseGate(Position position) {
        super(position);
        this.drawSymbol = "-";
    }
}
