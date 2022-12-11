package org.ldts.pacman.models;

import java.util.List;

public class DefaultStartSequenceLoader extends StartSequenceLoader {

    public DefaultStartSequenceLoader(Arena arena) {
        super(arena);
    }

    @Override
    public List<SpecificGhostStartSequence> populate() {
        GhostState nextStartSequenceState = null;
        long durationInMillseconds;
        for(RegularGhost regularGhost: this.arena.getRegularGhostsList()) {
            nextStartSequenceState = regularGhost.nextStartState;
            durationInMillseconds = regularGhost.getStartSequenceInMilliseconds();
            SpecificGhostStartSequence startSequence = new SpecificGhostStartSequence(regularGhost, nextStartSequenceState, durationInMillseconds);
            this.specificGhostStartSequenceList.add(startSequence);
        }

        return this.specificGhostStartSequenceList;
    }

    public void load() {

    }
}
