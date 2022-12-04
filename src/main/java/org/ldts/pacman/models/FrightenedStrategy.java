package org.ldts.pacman.models;

import java.util.List;

public interface FrightenedStrategy extends GhostStrategy {
    public void execute(Ghost ghost);
    public Position getNextPosition(Ghost ghost, Position pacmanPosition, List<List<Entity>> gameGrid);
}
