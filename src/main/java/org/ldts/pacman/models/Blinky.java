package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Blinky extends RegularGhost {
    protected Blinky(Position position) {
        super(position);
        this.previousState = new ChasingState(this);
        this.currentState = this.previousState;
        this.color = TextColor.ANSI.RED;
        this.alreadyPassedGhostHouseGateChasing = true;
        this.startSequenceInMilliseconds = 3000;
        this.originalColor = color;
        this.chaseStrategy = new AgressiveChaseStrategy();
        this.scatterStrategy = new ScatterToTopRight();
        this.currentDirection = new GhostDirectionLeft(this);
    }
}
