package org.ldts.pacman.models.game.arena.loaders.map;

import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.entities.Obstacle;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible;
import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost;

import java.io.IOException;

public abstract class MapArenaLoader {
    protected final Arena arena;

    protected MapArenaLoader(Arena arena) {
        this.arena = arena;
    }
   
    public abstract void load() throws IOException;

    protected abstract void loadObstacle(Obstacle obstacle);

    protected abstract void loadPacmanAt(Position position);

    protected abstract void setPacmanStartPosition(Position position);

    protected abstract void loadRegularGhost(RegularGhost ghost);

    protected abstract void loadFixedEdible(FixedEdible fixedEdible);
}
