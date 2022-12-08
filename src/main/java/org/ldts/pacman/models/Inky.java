package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Inky extends RegularGhost {
    protected Inky(Position position) {
        super(position);
        this.color = TextColor.ANSI.BLUE_BRIGHT;
        this.originalColor = this.color;
        this.chaseStrategy = new PatrolChaseStrategy();
        this.scatterStrategy = new ScatterToBottomRight();
        this.currentDirection = new GhostDirectionUp(this);
    }
}
