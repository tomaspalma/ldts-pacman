package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public abstract class Ghost extends MovableEntity {
    protected ChaseStrategy chaseStrategy;
    protected FrightenedStrategy frightenedStrategy;
    protected ScatterStrategy scatterStrategy;
    protected DyingStrategy dyingStrategy;

    protected GhostState previousState;
    protected GhostState currentState;

    protected TextColor.ANSI originalColor;

    protected boolean alreadyPassedGhostHouseGateChasing;

    public GhostState getPreviousState() {
        return previousState;
    }

    public ChaseStrategy getChaseStrategy() {
        return chaseStrategy;
    }

    public FrightenedStrategy getFrightenedStrategy() {
        return frightenedStrategy;
    }

    public DyingStrategy getDyingStrategy() {
        return dyingStrategy;
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
    
    public boolean isOnGhostHouseState() {
        return this.currentState instanceof GhostHouseState;
    }

    public abstract GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult();
    
    protected Ghost(Position position) {
        super(position);
    }

    public TextColor.ANSI getOriginalColor() {
        return this.originalColor;
    }

}
