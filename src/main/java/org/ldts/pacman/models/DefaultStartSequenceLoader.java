package org.ldts.pacman.models;

import org.ldts.pacman.models.game.entities.ghost.RegularGhost;
import org.ldts.pacman.models.game.entities.ghost.states.GhostState;

import java.util.List;

public class DefaultStartSequenceLoader extends StartSequenceLoader {

    public DefaultStartSequenceLoader(List<RegularGhost> ghostsList) {
        super(ghostsList);
    }

    @Override
    public List<SpecificGhostStartSequence> populate(float multiplier) {
        GhostState nextStartSequenceState = null;
        long durationInMillseconds;
        for(RegularGhost regularGhost: this.ghostsList) {
            nextStartSequenceState = regularGhost.getNextStartState();
            durationInMillseconds = regularGhost.getStartSequenceInMilliseconds();
            SpecificGhostStartSequence startSequence = new SpecificGhostStartSequence(regularGhost, nextStartSequenceState, durationInMillseconds);

            this.specificGhostStartSequenceList.add(startSequence);
        }

        return this.specificGhostStartSequenceList;
    }
}
