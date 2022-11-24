package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class RegularGhost extends Ghost {
    public RegularGhost(Position position) {
        super(position);
        this.color = TextColor.ANSI.WHITE;
        this.drawSymbol = "I";
    }
}
