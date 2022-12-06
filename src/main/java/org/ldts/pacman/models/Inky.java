package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Inky extends RegularGhost {
    protected Inky(Position position, Arena arena) {
        super(position, arena);
        this.color = TextColor.ANSI.BLUE_BRIGHT;
        this.chaseStrategy = new PatrolChaseStrategy();
        this.scatterStrategy = new ScatterToBottomLeft();
        this.currentDirection = new GhostDirectionUp(this);
    }
}
