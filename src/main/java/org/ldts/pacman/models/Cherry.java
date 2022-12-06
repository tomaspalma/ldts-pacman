package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Cherry extends FixedEdible {
    public Cherry(Position position, Arena arena) {
        super(position, arena);
        this.color = TextColor.ANSI.RED;
        this.drawSymbol = "E";
    }
}
