package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Wall extends FixedEntity {
    public Wall(Position position) {
        super(position);
        this.drawSymbol = "?";
        this.color = TextColor.ANSI.BLUE;
    }
}
