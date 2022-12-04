package org.ldts.pacman.models;

public abstract class Ghost extends MovableEntity {
    protected ChaseStrategy chaseStrategy;
    protected FrightenedStrategy frightenedStrategy;
    protected ScatterStrategy scatterStrategy;

    protected GhostState previousState;
    protected GhostState currentState;
    protected GhostDirection currentDirection;

    public GhostState getPreviousState() {
        return previousState;
    }

    public GhostDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirectionTo(GhostDirection direction) {
        this.currentDirection = direction;
    }

    public ChaseStrategy getChaseStrategy() {
        return chaseStrategy;
    }

    public FrightenedStrategy getFrightenedStrategy() {
        return frightenedStrategy;
    }

    public ScatterStrategy getScatterStrategy() {
        return scatterStrategy;
    }

    public void setPreviousStateTo(GhostState state) {
       this.previousState = state;
    }

    public void setCurrentStateTo(GhostState state) {
        this.currentState = state;
    }

    public GhostState getCurrentState() {
        return currentState;
    }

    public void die() {
        this.currentState.transitionToState(new DeadState(this));
    }

    public abstract GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult();
    
    protected Ghost(Position position) {
        super(position);
    }

}
