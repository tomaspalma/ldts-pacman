package org.ldts.pacman.models;

import java.io.IOException;

public abstract class MapArenaLoader {
    protected final Arena arena;

    protected MapArenaLoader(Arena arena) {
        this.arena = arena;
    }
   
    protected abstract void load() throws IOException;

    protected abstract void loadObstacle(Obstacle obstacle);

    protected abstract void loadPacmanAt(Position position);

    protected abstract void setPacmanStartPosition(Position position);

    protected abstract void loadRegularGhost(RegularGhost ghost);

    protected abstract void loadFixedEdible(FixedEdible fixedEdible);
}
