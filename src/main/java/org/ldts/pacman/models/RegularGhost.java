package org.ldts.pacman.models;

public class RegularGhost extends Ghost {
    public RegularGhost(Position position) {
        super(position);
        this.drawSymbol = "E";
    }
}
