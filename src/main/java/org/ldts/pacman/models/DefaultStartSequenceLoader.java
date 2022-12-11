package org.ldts.pacman.models;

import java.util.List;

public class DefaultStartSequenceLoader extends StartSequenceLoader {

    public DefaultStartSequenceLoader(List<RegularGhost> ghostsList) {
        super(ghostsList);
    }

    @Override
    public List<SpecificGhostStartSequence> populate() {
        GhostState nextStartSequenceState = null;
        long durationInMillseconds;
        for(RegularGhost regularGhost: this.ghostsList) {
            nextStartSequenceState = regularGhost.nextStartState;
            durationInMillseconds = regularGhost.getStartSequenceInMilliseconds();
            SpecificGhostStartSequence startSequence = new SpecificGhostStartSequence(regularGhost, nextStartSequenceState, durationInMillseconds);

            this.specificGhostStartSequenceList.add(startSequence);
        }

        return this.specificGhostStartSequenceList;
    }
}
