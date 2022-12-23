package org.ldts.pacman.models.game.arena.levels.sequences;

import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost;

import java.util.ArrayList;
import java.util.List;

public abstract class SequenceLoader<T> {
    protected final List<RegularGhost> ghostsList;

    public SequenceLoader(List<RegularGhost> ghostsList) {
        this.ghostsList = ghostsList;
    }

    public List<RegularGhost> getGhostsList() {
        return ghostsList;
    }

    public abstract List<T> populate();

}
