package org.ldts.pacman.models.game.entities.ghost.strategies.dying;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

public class GhostHouseDyingStrategy implements DyingStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        return ghost.getArena().getGhostHouse().getAvailablePosition();
    }
}
