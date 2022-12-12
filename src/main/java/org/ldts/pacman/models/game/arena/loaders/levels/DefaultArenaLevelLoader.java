package org.ldts.pacman.models.game.arena.loaders.levels;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.DuringSequenceLoader;
import org.ldts.pacman.models.StartSequenceLoader;
import org.ldts.pacman.models.game.arena.loaders.levels.ArenaLevelLoader;

public class DefaultArenaLevelLoader extends ArenaLevelLoader {
    public DefaultArenaLevelLoader(int amountOfLevels, Arena arena, StartSequenceLoader startSequenceLoader, DuringSequenceLoader duringSequenceLoader) {
        super(amountOfLevels, arena, startSequenceLoader, duringSequenceLoader);
    }

    // Agora aqui cria-se as sequências para cada nível
}
