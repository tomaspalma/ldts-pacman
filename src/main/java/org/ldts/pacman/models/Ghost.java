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

    protected boolean canCurrentlyMoveToGhostHouseGate;

    public GhostState getPreviousState() {
        return previousState;
    }

    public ChaseStrategy getChaseStrategy() {
        return chaseStrategy;
    }

    public boolean canCurrentlyMoveToGhostHouseGate() {
        return this.canCurrentlyMoveToGhostHouseGate;
    }

    public void setCanCurrentlyMoveToGhostHouseGateTo(boolean newValue) {
        this.canCurrentlyMoveToGhostHouseGate = newValue;
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

    public abstract void die();

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

    public boolean willBeInInvalidPosition(Position newPosition) {
        if(newPosition.isOutOfBounds()) return true;

        boolean canMoveToHouseGate = this.canCurrentlyMoveToGhostHouseGate();
        boolean isOnGatePositionAndCantBe = !canMoveToHouseGate && newPosition.isOnGatePosition();

        return newPosition.isOnSomeObstaclePosition() || isOnGatePositionAndCantBe;
    }
}
