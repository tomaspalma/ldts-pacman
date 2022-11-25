package org.ldts.pacman.models;

public abstract class RegularGhost extends Ghost {
    protected RegularGhost(Position position) {
        super(position);
        this.drawSymbol = "I";
        this.frightenedStrategy = new FrightenedRunAwayStrategy();
    }

    // Regular ghosts will always behave this way
    @Override
    public GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult() {
        if(this.currentState == State.FRIGHTENED_PHASE) return GameActions.GhostCollisionWithPacman.KILL_GHOST;

        return GameActions.GhostCollisionWithPacman.KILL_PACMAN;
    }
}
