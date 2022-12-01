package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public abstract class RegularGhost extends Ghost implements GameObserver {
    protected RegularGhost(Position position) {
        super(position);
        this.drawSymbol = "I";
        this.frightenedStrategy = new FrightenedRunAwayStrategy();
        this.previousState = new FrightenedState(this);
        this.currentState = new GhostHouseState(this);
    }

    // Regular ghosts will always behave this way
    @Override
    public GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult() {
        return currentState.collideWithPacmanResult();
    }

    @Override
    public void changeBasedOnObservable() {
        this.currentState.transitionToState(new FrightenedState(this));
    }

    public void changeColor(TextColor.ANSI newColor) {
        this.color = newColor;
    }

    public GhostState getState() {
        return currentState;
    }
}
