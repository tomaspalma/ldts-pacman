package org.ldts.pacman.models;

import java.util.List;

public class DefaultArenaLevelLoader extends ArenaLevelLoader {
    public DefaultArenaLevelLoader(int amountOfLevels, Arena arena, StartSequenceLoader startSequenceLoader) {
        super(amountOfLevels, arena, startSequenceLoader);
    }

    // Agora aqui cria-se as sequências para cada nível
    @Override
    public void load() {
        List<SpecificGhostStartSequence> ghostStartSequence = this.startSequenceLoader.getLoadedStartSequence();
    }
}
