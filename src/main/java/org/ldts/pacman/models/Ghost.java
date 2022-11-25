package org.ldts.pacman.models;

public abstract class Ghost extends MovableEntity {
    protected ChaseStrategy chaseStrategy;
    protected FrightenedStrategy frightenedStrategy;
    protected ScatterStrategy scatterStrategy;
    protected State currentState;

    protected enum State { 
        CHASING_PHASE,
        SCATTERING_PHASE,
        FRIGHTENED_PHASE,
    }

    public abstract GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult();
    
    protected Ghost(Position position) {
        super(position);
    }
}
