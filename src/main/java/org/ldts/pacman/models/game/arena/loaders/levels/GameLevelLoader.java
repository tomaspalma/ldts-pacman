package org.ldts.pacman.models.game.arena.loaders.levels;

import org.ldts.pacman.models.game.arena.levels.sequences.StartSequenceLoader;

public abstract class GameLevelLoader {
    protected final StartSequenceLoader startSequenceLoader;
    //protected final GameDuringSequenceLoader gameDuringSequenceLoader;

    public GameLevelLoader(StartSequenceLoader startSequenceLoader) {
       this.startSequenceLoader = startSequenceLoader;
    }

    public abstract void createLevels(int amountOfLevels);
}
