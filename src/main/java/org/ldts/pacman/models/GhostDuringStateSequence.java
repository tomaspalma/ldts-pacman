package org.ldts.pacman.models;

import java.util.List;

public class GhostDuringStateSequence {

    private final GhostState ghostState;
    long durationInMilliseconds;

    public GhostDuringStateSequence(GhostState ghostState, long durationInMilliseconds) {
        this.ghostState = ghostState;
        this.durationInMilliseconds = durationInMilliseconds;
    }

    public GhostState ghostState() {
        return ghostState;
    }

    public long durationInMilliseconds() {
        return durationInMilliseconds;
    }

    public void execute(List<Ghost> commonGhostsToApplyTo) {
        for(Ghost ghost: commonGhostsToApplyTo) {
            ghost.getCurrentState().transitionToState(ghostState);
        }
    }
}
