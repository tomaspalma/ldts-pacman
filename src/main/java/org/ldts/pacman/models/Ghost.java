package org.ldts.pacman.models;

public abstract class Ghost extends MovableEntity {
    protected ChaseStrategy chaseStrategy;
    protected FrightenedStrategy frightenedStrategy;
    protected ScatterStrategy scatterStrategy;

    public ChaseStrategy getChaseStrategy() {
        return chaseStrategy;
    }

    public FrightenedStrategy getFrightenedStrategy() {
        return frightenedStrategy;
    }

    public ScatterStrategy getScatterStrategy() {
        return scatterStrategy;
    }

    public GhostState getCurrentState() {
        return currentState;
    }

    protected GhostState currentState;

    public enum GhostState {
        CHASING_PHASE,
        SCATTERING_PHASE,
        FRIGHTENED_PHASE,
    }

    public abstract GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult();
    
    protected Ghost(Position position) {
        super(position);
    }
}
