package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultDuringSequenceLoader extends DuringSequenceLoader {
    public DefaultDuringSequenceLoader(List<RegularGhost> ghostsList) {
        super(ghostsList);
    }

    @Override
    public List<GhostDuringStateSequence> populate() {
        return new ArrayList<>(Arrays.asList(new GhostDuringStateSequence(ChasingState.class, 4000)));
    }
}
