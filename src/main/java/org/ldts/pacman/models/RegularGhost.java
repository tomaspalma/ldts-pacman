package org.ldts.pacman.models;

public abstract class RegularGhost extends Ghost implements GameObserver {
    protected RegularGhost(Position position) {
        super(position);
        this.drawSymbol = "I";
        this.frightenedStrategy = new FrightenedRunAwayStrategy();
        currentState = GhostState.CHASING_PHASE;
    }

    // Regular ghosts will always behave this way
    @Override
    public GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult() {
        if(this.currentState == GhostState.FRIGHTENED_PHASE) return GameActions.GhostCollisionWithPacman.KILL_GHOST;

        return GameActions.GhostCollisionWithPacman.KILL_PACMAN;
    }

    @Override
    public void changeBasedOnObservable() {

    }

    public GhostState getState() {
        return currentState;
    }
}
