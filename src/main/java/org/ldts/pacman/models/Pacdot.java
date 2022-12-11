package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Pacdot extends FixedEdible {
    public Pacdot(Position position) {
        super(position);
        this.drawSymbol = ".";
        this.color = TextColor.ANSI.YELLOW;
        this.points = 10;
    }

}
