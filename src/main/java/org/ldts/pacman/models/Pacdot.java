package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Pacdot extends FixedEdible {
    public Pacdot(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = ".";
        this.color = TextColor.ANSI.YELLOW;
    }
}
