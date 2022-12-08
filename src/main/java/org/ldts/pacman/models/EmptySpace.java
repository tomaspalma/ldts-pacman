package org.ldts.pacman.models;

public class EmptySpace extends FixedEntity {
    protected EmptySpace(Position position) {
        super(position);
        this.drawSymbol = " ";
    }
}
