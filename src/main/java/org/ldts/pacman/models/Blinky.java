package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Blinky extends RegularGhost {
    protected Blinky(Position position) {
        super(position);
        this.color = TextColor.ANSI.RED;
        this.originalColor = color;
        this.chaseStrategy = new AgressiveChaseStrategy();
        this.scatterStrategy = new ScatterToTopLeft();
        this.currentDirection = new GhostDirectionLeft(this);
    }
}
