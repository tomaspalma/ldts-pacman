package org.ldts.pacman.models;

import java.util.List;

// Isto é para os criar um nível
public abstract class ArenaLevelLoader implements ArenaLoader {
    protected final Arena arena;
    protected StartSequenceLoader startSequenceLoader;
    protected DuringSequenceLoader duringSequenceLoader;
    protected int amountOfLevels;

    public ArenaLevelLoader(int amountOfLevels, Arena arena, StartSequenceLoader startSequenceLoader, DuringSequenceLoader duringSequenceLoader) {
        this.amountOfLevels = amountOfLevels;
        this.arena = arena;
        this.startSequenceLoader = startSequenceLoader;
        this.duringSequenceLoader = duringSequenceLoader;
    }

    @Override
    public void load() {
        for(int i = 0; i < amountOfLevels; i++) {
            this.arena.getLevels().add(new GameLevel(this.startSequenceLoader.populate(), this.duringSequenceLoader.populate(), this.arena.getRegularGhostsList()));
        }
    }
}
