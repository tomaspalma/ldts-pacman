package org.ldts.pacman.models;

public abstract class GameLevelLoader {
    protected final StartSequenceLoader startSequenceLoader;
    //protected final GameDuringSequenceLoader gameDuringSequenceLoader;

    public GameLevelLoader(StartSequenceLoader startSequenceLoader) {
       this.startSequenceLoader = startSequenceLoader;
    }

    public abstract void createLevels(int amountOfLevels);
}
