package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class PowerPellet extends Entity {
    public PowerPellet(Position position) {
        super(position);
        this.drawSymbol = "?";
        this.color = TextColor.ANSI.WHITE;
    }
}
