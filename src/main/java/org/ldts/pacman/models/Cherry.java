package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Cherry extends FixedEdible {
    public Cherry(Position position) {
        super(position);
        this.color = TextColor.ANSI.RED;
        this.drawSymbol = "E";
    }
}
