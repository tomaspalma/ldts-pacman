package org.ldts.pacman.models.game.arena.levels.sequences;

import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost;

import java.util.List;

public abstract class DuringSequenceLoader extends SequenceLoader<GhostDuringStateSequence> {
    public DuringSequenceLoader(List<RegularGhost> ghostsList) {
        super(ghostsList);
    }
}
