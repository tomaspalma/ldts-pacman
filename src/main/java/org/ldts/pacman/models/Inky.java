package org.ldts.pacman.models;

import java.util.List;

import com.googlecode.lanterna.TextColor;

public class Inky extends RegularGhost {
    protected Inky(Position position) {
        super(position);
        this.previousState = new GhostHouseState(this);
        this.currentState = this.previousState;
        this.color = TextColor.ANSI.BLUE_BRIGHT;
        this.startSequenceInMilliseconds = 14000;
        this.canCurrentlyMoveToGhostHouseGate = false;
        this.originalColor = this.color;
        this.chaseStrategy = new PatrolChaseStrategy();
        this.scatterStrategy = new ScatterToBottomRight();
        this.currentDirection = new GhostDirectionUp(this);
    }
}
