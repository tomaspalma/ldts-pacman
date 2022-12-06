package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Blinky extends RegularGhost {
    protected Blinky(Position position, Arena arena) {
        super(position, arena);
        this.color = TextColor.ANSI.RED;
        this.chaseStrategy = new AgressiveChaseStrategy();
        this.scatterStrategy = new ScatterToTopLeft();
        this.currentDirection = new GhostDirectionLeft(this);
    }
}
