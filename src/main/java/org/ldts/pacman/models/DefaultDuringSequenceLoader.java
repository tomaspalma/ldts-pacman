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
    public List<GhostDuringStateSequence> populate(float multiplier) {
        return new ArrayList<>(Arrays.asList(new GhostDuringStateSequence(ScatteringState.class, 2000),
                new GhostDuringStateSequence(ChasingState.class, 14000)));
    }
}
