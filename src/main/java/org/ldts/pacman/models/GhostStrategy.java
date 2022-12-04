package org.ldts.pacman.models;

import java.util.List;

public interface GhostStrategy {
    public Position getNextPosition(Ghost ghost, Position pacmanPosition, List<List<Entity>> gameGrid);
}
