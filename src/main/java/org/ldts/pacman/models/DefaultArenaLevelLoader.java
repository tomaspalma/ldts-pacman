package org.ldts.pacman.models;

import java.util.List;

public class DefaultArenaLevelLoader extends ArenaLevelLoader {
    public DefaultArenaLevelLoader(int amountOfLevels, Arena arena, StartSequenceLoader startSequenceLoader, DuringSequenceLoader duringSequenceLoader) {
        super(amountOfLevels, arena, startSequenceLoader, duringSequenceLoader);
    }

    // Agora aqui cria-se as sequências para cada nível
}
