package org.ldts.pacman;

import org.ldts.pacman.models.Clock;
import org.ldts.pacman.models.GhostDuringStateSequence;
import org.ldts.pacman.models.SpecificGhostStartSequence;

import java.util.List;

public class GameLevel {
    //private final List<StateMachine> stateMachine;
    private final List<SpecificGhostStartSequence> startStateMachine;
    private final List<GhostDuringStateSequence> duringStateMachine;

    private boolean otherStartSequencesRemaining;
    private boolean otherDuringSequencesRemaining;

    private int startStateMachineCounter = 0;
    private int duringStateMachineCounter = 0;


    private final Clock clock;

    public GameLevel(List<SpecificGhostStartSequence> startStateMachine, List<GhostDuringStateSequence> duringStateMachine) {
        this.startStateMachine = startStateMachine;
        this.duringStateMachine = duringStateMachine;

        this.otherStartSequencesRemaining = (!this.startStateMachine.isEmpty());
        this.otherDuringSequencesRemaining = (!this.duringStateMachine.isEmpty());

        this.clock = new Clock();
    }

    public void step() {
        if(otherStartSequencesRemaining)
            this.stepStartSequence();
        else if(otherDuringSequencesRemaining)
            this.stepDuringSequence();
    }

    private void stepStartSequence() {
        boolean otherSequencesStillRemaining;
        this.clock.step();

        long sequenceDurationInMilliseconds = this.startStateMachine.get(startStateMachineCounter).getTimeToBeActivatedInMilliseconds();
        if(sequenceDurationInMilliseconds < this.clock.getElapsedMilliseconds()) {
            this.startStateMachine.get(startStateMachineCounter).execute();
            this.startStateMachineCounter += 1;

            otherSequencesStillRemaining = (this.startStateMachineCounter == this.startStateMachine.size() - 1);
            if(otherSequencesStillRemaining)
                this.checkIfOtherStartSequencesHavePassed(this.startStateMachine, this.clock.getElapsedMilliseconds());
        }
    }

    private void stepDuringSequence() {

    }

    private void checkIfOtherStartSequencesHavePassed(List<SpecificGhostStartSequence> list, long timePassedInMilliseconds) {
        for(int i = this.startStateMachineCounter; i < list.size(); i++) {
            if(list.get(i).getTimeToBeActivatedInMilliseconds() < timePassedInMilliseconds)
                startStateMachineCounter += 1;
            else
                break;
        }
    }
}
