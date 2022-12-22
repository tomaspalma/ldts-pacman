package org.ldts.pacman.models.game.arena.levels.sequences;

import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.states.GhostState;

public class SpecificGhostStartSequence implements LevelStateSequence {

    private final Ghost ghost;
    private final GhostState newStateToTransitionTo;
    private long timeToBeActivatedInMilliseconds;

    public SpecificGhostStartSequence(Ghost ghost, GhostState newState, long timeToBeActivatedInMilliseconds) {
        this.ghost = ghost;
        this.newStateToTransitionTo = newState;
        this.timeToBeActivatedInMilliseconds = timeToBeActivatedInMilliseconds;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public void setTimeToBeActivatedInMilliseconds(long timeInMilliseconds) {
        this.timeToBeActivatedInMilliseconds = timeInMilliseconds;
    }

    public GhostState getNewStateToTransitionTo() {
        return this.newStateToTransitionTo;
    }

    @Override
    public long getTimeToBeActivatedInMilliseconds() {
        return this.timeToBeActivatedInMilliseconds;
    }

    public void execute() {
        this.ghost.getCurrentState().transitionToState(newStateToTransitionTo);
    }
}
