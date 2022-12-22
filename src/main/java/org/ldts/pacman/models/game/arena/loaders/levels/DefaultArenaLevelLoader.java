package org.ldts.pacman.models.game.arena.loaders.levels;

import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.arena.levels.sequences.DuringSequenceLoader;
import org.ldts.pacman.models.game.arena.levels.sequences.StartSequenceLoader;

public class DefaultArenaLevelLoader extends ArenaLevelLoader {
    public DefaultArenaLevelLoader(int amountOfLevels, Arena arena, StartSequenceLoader startSequenceLoader, DuringSequenceLoader duringSequenceLoader) {
        super(amountOfLevels, arena, startSequenceLoader, duringSequenceLoader);
        this.multiplier = 1.2f;
        this.startSequenceLoader.setSequenceMultiplier(multiplier);
        this.duringSequenceLoader.setSequenceMultiplier(multiplier);
    }
}
