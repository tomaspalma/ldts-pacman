package org.ldts.pacman.models;

import java.util.List;

// Isto é para os criar um nível
public abstract class ArenaLevelLoader implements ArenaLoader {
    protected final Arena arena;
    protected StartSequenceLoader startSequenceLoader;
    protected int amountOfLevels;

    public ArenaLevelLoader(int amountOfLevels, Arena arena, StartSequenceLoader startSequenceLoader) {
        this.amountOfLevels = amountOfLevels;
        this.arena = arena;
        this.startSequenceLoader = startSequenceLoader;
    }
}
