package org.ldts.pacman.models;

import org.ldts.pacman.models.game.entities.ghost.RegularGhost;

import java.util.List;

public abstract class DuringSequenceLoader extends SequenceLoader<GhostDuringStateSequence> {
    public DuringSequenceLoader(List<RegularGhost> ghostsList) {
        super(ghostsList);
    }
}
