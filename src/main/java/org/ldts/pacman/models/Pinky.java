package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Pinky extends RegularGhost {
    protected Pinky(Position position) {
        super(position);
        this.color = TextColor.ANSI.WHITE;
        this.originalColor = color;
        this.chaseStrategy = new AmbushChaseStrategy();
        this.scatterStrategy = new ScatterToTopLeft();
        this.currentDirection = new GhostDirectionUp(this);
    }
}
