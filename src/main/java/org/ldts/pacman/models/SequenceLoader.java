package org.ldts.pacman.models;

import org.ldts.pacman.models.game.entities.ghost.RegularGhost;

import java.util.ArrayList;
import java.util.List;

public abstract class SequenceLoader<T> {
    protected final List<RegularGhost> ghostsList;
    protected final List<T> specificGhostStartSequenceList;
    protected float sequenceMultiplier;

    public SequenceLoader(List<RegularGhost> ghostsList) {
        this.ghostsList = ghostsList;
        this.specificGhostStartSequenceList = new ArrayList<>();
    }

    public List<T> getSpecificGhostStartSequenceList() {
        return specificGhostStartSequenceList;
    }

    public float getSequenceMultiplier() {
        return sequenceMultiplier;
    }

    public void setSequenceMultiplier(float sequenceMultiplier) {
        this.sequenceMultiplier = sequenceMultiplier;
    }

    public List<T> getLoadedStartSequence() {
        if(specificGhostStartSequenceList.isEmpty())
            return this.populate(this.sequenceMultiplier);

        return this.specificGhostStartSequenceList;
    }

    public List<RegularGhost> getGhostsList() {
        return ghostsList;
    }

    public abstract List<T> populate(float multiplier);

}
