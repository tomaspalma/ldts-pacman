package org.ldts.pacman.models;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GhostDuringStateSequence implements LevelStateSequence {

    private final Class ghostState;
    long timeToBeActivatedInMilliseconds;

    public GhostDuringStateSequence(Class ghostState, long timeToBeActivatedInMilliseconds) {
        this.ghostState = ghostState;
        this.timeToBeActivatedInMilliseconds = timeToBeActivatedInMilliseconds;
    }

    public Class ghostState() {
        return this.ghostState;
    }

    public void execute(List<RegularGhost> commonGhostsToApplyTo) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(Ghost ghost: commonGhostsToApplyTo) {
            ghost.getCurrentState().transitionToState((GhostState) ghostState.getDeclaredConstructor(Ghost.class).newInstance(ghost));
        }
    }

    @Override
    public long getTimeToBeActivatedInMilliseconds() {
        return this.timeToBeActivatedInMilliseconds;
    }
}
