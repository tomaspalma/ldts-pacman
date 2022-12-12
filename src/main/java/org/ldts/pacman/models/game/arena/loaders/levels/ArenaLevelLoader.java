package org.ldts.pacman.models.game.arena.loaders.levels;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.DuringSequenceLoader;
import org.ldts.pacman.models.game.arena.levels.GameLevel;
import org.ldts.pacman.models.StartSequenceLoader;
import org.ldts.pacman.models.game.arena.loaders.ArenaLoader;

// Isto é para os criar um nível
public abstract class ArenaLevelLoader implements ArenaLoader {
    protected final Arena arena;
    protected StartSequenceLoader startSequenceLoader;
    protected DuringSequenceLoader duringSequenceLoader;
    protected int amountOfLevels;
    protected float multiplier;

    public ArenaLevelLoader(int amountOfLevels, Arena arena, StartSequenceLoader startSequenceLoader, DuringSequenceLoader duringSequenceLoader) {
        this.amountOfLevels = amountOfLevels;
        this.arena = arena;
        this.startSequenceLoader = startSequenceLoader;
        this.duringSequenceLoader = duringSequenceLoader;
    }

    @Override
    public void load() {
        for(int i = 0; i < amountOfLevels; i++) {
            this.arena.getLevels().add(new GameLevel(this.startSequenceLoader.populate(this.multiplier),
                    this.duringSequenceLoader.populate(this.multiplier), this.arena.getRegularGhostsList()));
        }
    }
}
