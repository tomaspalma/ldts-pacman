package org.ldts.pacman.models;

import java.util.List;

public abstract class DuringSequenceLoader extends SequenceLoader<GhostDuringStateSequence> {
    public DuringSequenceLoader(List<RegularGhost> ghostsList) {
        super(ghostsList);
    }
}
