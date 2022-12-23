package org.ldts.pacman.models.game.arena.levels;

import org.ldts.pacman.models.game.arena.levels.sequences.GhostDuringStateSequence;
import org.ldts.pacman.models.game.arena.levels.sequences.LevelStateSequence;
import org.ldts.pacman.models.game.arena.levels.sequences.SpecificGhostStartSequence;
import org.ldts.pacman.models.game.Clock;
import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost;
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState;
import org.ldts.pacman.models.game.entities.ghost.states.ScatteringState;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GameLevel {
    private List<SpecificGhostStartSequence> startStateMachine;
    private List<GhostDuringStateSequence> duringStateMachine;
    private int multiplier = 1;

    private boolean otherStartSequencesRemaining;
    private boolean otherDuringSequencesRemaining;

    private int startStateMachineCounter = 0;
    private int duringStateMachineCounter = 0;
    private final List<RegularGhost>  ghostsList;

    private Clock clock;

    public boolean isOtherStartSequencesRemaining() {
        return otherStartSequencesRemaining;
    }

    public boolean isOtherDuringSequencesRemaining() {
        return otherDuringSequencesRemaining;
    }

    public void setStartStateMachine(List<SpecificGhostStartSequence> startStateMachine) {
        this.startStateMachine = startStateMachine;
    }

    public void setDuringStateMachine(List<GhostDuringStateSequence> duringStateMachine) {
        this.duringStateMachine = duringStateMachine;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public int getStartStateMachineCounter() {
        return startStateMachineCounter;
    }

    public int getDuringStateMachineCounter() {
        return duringStateMachineCounter;
    }

    public GameLevel(List<SpecificGhostStartSequence> startStateMachine, List<GhostDuringStateSequence> duringStateMachine
        , List<RegularGhost> ghostsList, Clock clock) {
        this.startStateMachine = startStateMachine;
        this.duringStateMachine = duringStateMachine;

        this.otherStartSequencesRemaining = !this.startStateMachine.isEmpty();
        this.otherDuringSequencesRemaining = !this.duringStateMachine.isEmpty();

        this.ghostsList = ghostsList;

        this.clock = clock;
    }

    public List<SpecificGhostStartSequence> getStartStateMachine() {
        return startStateMachine;
    }

    public List<GhostDuringStateSequence> getDuringStateMachine() {
        return duringStateMachine;
    }

    public Clock getClock() {
        return clock;
    }

    public void setOtherStartSequencesRemaining(boolean otherStartSequencesRemaining) {
        this.otherStartSequencesRemaining = otherStartSequencesRemaining;
    }

    public void setOtherDuringSequencesRemaining(boolean otherDuringSequencesRemaining) {
        this.otherDuringSequencesRemaining = otherDuringSequencesRemaining;
    }

    public void setStartStateMachineCounter(int startStateMachineCounter) {
        this.startStateMachineCounter = startStateMachineCounter;
    }

    public void step() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.clock.step();

        if(this.otherStartSequencesRemaining)
            this.stepStartSequence();
        else if(this.otherDuringSequencesRemaining)
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

    public void transformItselfIntoAnotherLevel() {
        this.restart();

        this.changeStartStateMachine();
        this.changeDuringStateMachine();
    }
    
    private void changeStartStateMachine() {
        for(SpecificGhostStartSequence g: startStateMachine) {
            if(g.getTimeToBeActivatedInMilliseconds() - 500 <= 0) continue;
            g.setTimeToBeActivatedInMilliseconds(g.getTimeToBeActivatedInMilliseconds() - (500L * multiplier));
        }
    }
    
    private void changeDuringStateMachine() {
        for (GhostDuringStateSequence g : duringStateMachine) {
            if (g.getGhostState() == ChasingState.class)
                g.setTimeToBeActivatedInMilliseconds(g.getTimeToBeActivatedInMilliseconds() - (1000L * multiplier));
            else if(g.getGhostState() == ScatteringState.class)
                g.setTimeToBeActivatedInMilliseconds(g.getTimeToBeActivatedInMilliseconds() + (500L * multiplier));
        }
    }


    public void setDuringStateMachineCounter(int duringStateMachineCounter) {
        this.duringStateMachineCounter = duringStateMachineCounter;
    }

    private void stepDuringSequence() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GhostDuringStateSequence ghostDuringStateSequence = this.duringStateMachine.get(duringStateMachineCounter);


        if(isTimeToActivate(ghostDuringStateSequence)) {
            ghostDuringStateSequence.execute(this.ghostsList);
            this.duringStateMachineCounter = (this.duringStateMachineCounter + 1) % this.duringStateMachine.size();

            this.otherDuringSequencesRemaining = (this.duringStateMachineCounter < this.duringStateMachine.size());
            if(this.otherDuringSequencesRemaining)
                this.checkIfOtherDuringSequencesHavePassed(this.duringStateMachine, this.clock.getElapsedMilliseconds());
            else
                this.clock.reset();
        }
    }

    private void checkIfOtherStartSequencesHavePassed(List<SpecificGhostStartSequence> list, long timePassedInMilliseconds) {
        for(int i = this.startStateMachineCounter; i < list.size(); i++) {
            if(list.get(i).getTimeToBeActivatedInMilliseconds() <= timePassedInMilliseconds) {
                this.startStateMachineCounter += 1;
                this.otherStartSequencesRemaining = (this.startStateMachineCounter < this.startStateMachine.size());
            }
            else
                break;
        }
    }

    private void checkIfOtherDuringSequencesHavePassed(List<GhostDuringStateSequence> list, long timePassedInMilliseconds) {
        for(int i = this.duringStateMachineCounter; i < list.size(); i++) {
            if(list.get(i).getTimeToBeActivatedInMilliseconds() <= timePassedInMilliseconds) {
                this.duringStateMachineCounter += 1;
                this.otherDuringSequencesRemaining = (this.duringStateMachineCounter < this.duringStateMachine.size());
            }
            else
                break;
        }
    }

    public void restart() {
        this.resetRemainingBooleans();
        this.resetCounters();
        this.clock.reset();
    }

    private void resetCounters() {
        this.startStateMachineCounter = 0;
        this.duringStateMachineCounter = 0;
    }

    private void resetRemainingBooleans() {
        this.otherStartSequencesRemaining = true;
        this.otherDuringSequencesRemaining = true;
    }

}
