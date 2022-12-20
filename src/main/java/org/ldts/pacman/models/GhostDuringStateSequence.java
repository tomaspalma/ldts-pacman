package org.ldts.pacman.models;

import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.RegularGhost;
import org.ldts.pacman.models.game.entities.ghost.states.GhostState;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GhostDuringStateSequence<T extends GhostState> implements LevelStateSequence {

    private final Class<T> ghostState;
    long timeToBeActivatedInMilliseconds;

    public GhostDuringStateSequence(Class<T> ghostState, long timeToBeActivatedInMilliseconds) {
        this.ghostState = ghostState;
        this.timeToBeActivatedInMilliseconds = timeToBeActivatedInMilliseconds;
    }

    public Class<T> ghostState() {
        return this.ghostState;
    }

    public void execute(List<RegularGhost> commonGhostsToApplyTo) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(Ghost ghost: commonGhostsToApplyTo) {
            GhostState currentState = ghost.getCurrentState();
            currentState.transitionToState(ghostState.getDeclaredConstructor(Ghost.class).newInstance(ghost));
        }
    }

    public void setTimeToBeActivatedInMilliseconds(long timeToBeActivatedInMilliseconds) {
        this.timeToBeActivatedInMilliseconds = timeToBeActivatedInMilliseconds;
    }

    @Override
    public long getTimeToBeActivatedInMilliseconds() {
        return this.timeToBeActivatedInMilliseconds;
    }
}
