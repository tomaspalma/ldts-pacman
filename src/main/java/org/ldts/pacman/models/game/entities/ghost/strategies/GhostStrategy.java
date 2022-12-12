package org.ldts.pacman.models.game.entities.ghost.strategies;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;

public interface GhostStrategy {
    Position getNextPosition(Ghost ghost);
}
