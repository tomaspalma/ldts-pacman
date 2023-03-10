package org.ldts.pacman.models.game.arena.levels.sequences;

import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost;
import org.ldts.pacman.models.game.entities.ghost.states.GhostState;

import java.util.ArrayList;
import java.util.List;

public class DefaultStartSequenceLoader extends StartSequenceLoader {
    protected List<SpecificGhostStartSequence> specificGhostStartSequenceList = new ArrayList<>();

    public DefaultStartSequenceLoader(List<RegularGhost> ghostsList) {
        super(ghostsList);
    }

    @Override
    public List<SpecificGhostStartSequence> populate() {
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
