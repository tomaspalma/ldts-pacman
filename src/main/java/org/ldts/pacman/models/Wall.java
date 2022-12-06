package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Wall extends Obstacle {
    public Wall(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = "#";
        this.color = TextColor.ANSI.BLUE;
    }
}
