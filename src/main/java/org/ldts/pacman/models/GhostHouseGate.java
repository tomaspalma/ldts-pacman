package org.ldts.pacman.models;

public class GhostHouseGate extends FixedEntity {
    protected GhostHouseGate(Position position) {
        super(position);
        this.drawSymbol = "-";
    }
}
