package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class PowerPellet extends FixedEdible {
    public PowerPellet(Position position) {
        super(position);
        this.drawSymbol = "O";
        this.color = TextColor.ANSI.WHITE;
    }
}
