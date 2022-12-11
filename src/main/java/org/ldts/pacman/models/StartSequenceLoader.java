package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.List;

public abstract class StartSequenceLoader {
    protected final Arena arena;
    protected final List<SpecificGhostStartSequence> specificGhostStartSequenceList;

    public StartSequenceLoader(Arena arena) {
        this.arena = arena;
        this.specificGhostStartSequenceList = new ArrayList<>();
    }

    public List<SpecificGhostStartSequence> getLoadedStartSequence() {
        assert !this.specificGhostStartSequenceList.isEmpty();

        return this.specificGhostStartSequenceList;
    }

    public abstract List<SpecificGhostStartSequence> populate();
}
