package org.ldts.pacman.models;

public class SpecificGhostStartSequence {

    private final Ghost ghost;
    private final GhostState newStateToTransitionTo;
    private final long timeToBeActivatedInMilliseconds;

    public SpecificGhostStartSequence(Ghost ghost, GhostState newState, long timeToBeActivatedInMilliseconds) {
        this.ghost = ghost;
        this.newStateToTransitionTo = newState;
        this.timeToBeActivatedInMilliseconds = timeToBeActivatedInMilliseconds;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public GhostState getNewStateToTransitionTo() {
        return this.newStateToTransitionTo;
    }

    public long getTimeToBeActivatedInMilliseconds() {
        return this.timeToBeActivatedInMilliseconds;
    }

    public void execute() {
        this.ghost.getCurrentState().transitionToState(newStateToTransitionTo);
    }
}
