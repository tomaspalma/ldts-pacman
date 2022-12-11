package org.ldts.pacman.models;

import org.ldts.pacman.models.Clock;
import org.ldts.pacman.models.GhostDuringStateSequence;
import org.ldts.pacman.models.SpecificGhostStartSequence;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GameLevel {
    //private final List<StateMachine> stateMachine;
    private final List<SpecificGhostStartSequence> startStateMachine;
    private final List<GhostDuringStateSequence> duringStateMachine;

    private boolean otherStartSequencesRemaining;
    private boolean otherDuringSequencesRemaining;

    private int startStateMachineCounter = 0;
    private int duringStateMachineCounter = 0;
    private final List<RegularGhost>  ghostsList;

    private final Clock clock;

    public GameLevel(List<SpecificGhostStartSequence> startStateMachine, List<GhostDuringStateSequence> duringStateMachine
        , List<RegularGhost> ghostsList) {
        this.startStateMachine = startStateMachine;
        this.duringStateMachine = duringStateMachine;

        this.otherStartSequencesRemaining = (!this.startStateMachine.isEmpty());
        this.otherDuringSequencesRemaining = (!this.duringStateMachine.isEmpty());

        this.ghostsList = ghostsList;

        this.clock = new Clock();
    }

    public void step() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.clock.step();

        if(otherStartSequencesRemaining)
            this.stepStartSequence();
        else if(otherDuringSequencesRemaining)
            this.stepDuringSequence();
    }

    private <T extends LevelStateSequence> boolean isTimeToActivate(T levelStateSequence) {
        long sequenceTimeToActivate = levelStateSequence.getTimeToBeActivatedInMilliseconds();
        return sequenceTimeToActivate < this.clock.getElapsedMilliseconds();
    }

    private void stepStartSequence() {
        if(this.startStateMachineCounter >= this.startStateMachine.size()) return;

        SpecificGhostStartSequence specificGhostStartSequence = this.startStateMachine.get(startStateMachineCounter);
        if(isTimeToActivate(specificGhostStartSequence)) {
            specificGhostStartSequence.execute();
            this.startStateMachineCounter += 1;

            this.otherStartSequencesRemaining = (this.startStateMachineCounter < this.startStateMachine.size());
            if(this.otherStartSequencesRemaining)
                this.checkIfOtherStartSequencesHavePassed(this.startStateMachine, this.clock.getElapsedMilliseconds());
            else
                this.clock.reset();
        }
    }

    private void stepDuringSequence() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GhostDuringStateSequence ghostDuringStateSequence = this.duringStateMachine.get(duringStateMachineCounter);
        if(isTimeToActivate(ghostDuringStateSequence)) {
            ghostDuringStateSequence.execute(this.ghostsList);
            this.startStateMachineCounter += 1;

            this.otherStartSequencesRemaining = (this.startStateMachineCounter < this.startStateMachine.size());
            if(this.otherStartSequencesRemaining)
                this.checkIfOtherStartSequencesHavePassed(this.startStateMachine, this.clock.getElapsedMilliseconds());
            else
                this.clock.reset();
        }
    }

    private <T extends LevelStateSequence> void checkIfOtherStartSequencesHavePassed(List<T> list, long timePassedInMilliseconds) {
        for(int i = this.startStateMachineCounter; i < list.size(); i++) {
            if(list.get(i).getTimeToBeActivatedInMilliseconds() < timePassedInMilliseconds)
                startStateMachineCounter += 1;
            else
                break;
        }
    }
}
