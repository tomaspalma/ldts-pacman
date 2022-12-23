package org.ldts.pacman.models.game.arena.levels.sequences;

import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost;

import java.util.ArrayList;
import java.util.List;

public abstract class SequenceLoader<T> {
    protected final List<RegularGhost> ghostsList;
    protected final List<T> specificGhostStartSequenceList;

    public SequenceLoader(List<RegularGhost> ghostsList) {
        this.ghostsList = ghostsList;
        this.specificGhostStartSequenceList = new ArrayList<>();
    }

    public List<T> getSpecificGhostStartSequenceList() {
        return specificGhostStartSequenceList;
    }

    public List<T> getLoadedStartSequence() {
        if(specificGhostStartSequenceList.isEmpty())
            return this.populate();

        return this.specificGhostStartSequenceList;
    }

    public List<RegularGhost> getGhostsList() {
        return ghostsList;
    }

    public abstract List<T> populate();

}
