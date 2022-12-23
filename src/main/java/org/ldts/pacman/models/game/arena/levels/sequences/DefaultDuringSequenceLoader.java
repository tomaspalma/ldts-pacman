package org.ldts.pacman.models.game.arena.levels.sequences;

import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost;
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
        return new ArrayList<>(Arrays.asList(new GhostDuringStateSequence(ScatteringState.class, 4000),
                new GhostDuringStateSequence(ChasingState.class, 10000)));
    }
}
