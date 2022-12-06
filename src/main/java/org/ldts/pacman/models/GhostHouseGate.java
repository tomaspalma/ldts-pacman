package org.ldts.pacman.models;

public class GhostHouseGate extends FixedEntity {
    protected GhostHouseGate(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = "-";
    }
}
