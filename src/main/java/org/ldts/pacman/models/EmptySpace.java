package org.ldts.pacman.models;

public class EmptySpace extends FixedEntity {
    protected EmptySpace(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = " ";
    }
}
