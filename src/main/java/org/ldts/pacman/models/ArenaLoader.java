package org.ldts.pacman.models;

import java.io.IOException;

public abstract class ArenaLoader {
    protected Arena arena;

    protected ArenaLoader(Arena arena) {
        this.arena = arena;
    }
   
    protected abstract void load() throws IOException;

    protected abstract void loadWallAt(Position position);

    protected abstract void loadPacmanAt(Position position);

    protected abstract void loadRegularGhost(RegularGhost ghost);

    protected abstract void loadCherryAt(Position position);

    protected abstract void loadPacdotAt(Position position);

    protected abstract void loadPowerPelletAt(Position position);
}
