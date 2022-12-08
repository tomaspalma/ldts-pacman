package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Clyde extends RegularGhost {
    protected Clyde(Position position) {
        super(position);
        this.color = TextColor.ANSI.YELLOW;
        this.originalColor = color;
        this.chaseStrategy = new HybridIgnorantChaseStrategy();
        this.scatterStrategy = new ScatterToBottomLeft();
        this.currentDirection = new GhostDirectionUp(this);
    }
}
