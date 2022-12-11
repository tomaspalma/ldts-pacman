package org.ldts.pacman.models;

import java.util.List;

public abstract class StartSequenceLoader extends SequenceLoader<SpecificGhostStartSequence> {
    public StartSequenceLoader(List<RegularGhost> ghostsList) {
        super(ghostsList);
    }
}
