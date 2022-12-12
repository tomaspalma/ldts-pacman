package org.ldts.pacman.models;

import org.ldts.pacman.models.game.entities.ghost.RegularGhost;
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState;
import org.ldts.pacman.models.game.entities.ghost.states.ScatteringState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultDuringSequenceLoader extends DuringSequenceLoader {
    public DefaultDuringSequenceLoader(List<RegularGhost> ghostsList) {
        super(ghostsList);
    }

    @Override
    public List<GhostDuringStateSequence> populate() {
        return new ArrayList<>(Arrays.asList(new GhostDuringStateSequence(ChasingState.class, 8000),
                new GhostDuringStateSequence(ScatteringState.class, 16000)));
    }
}
