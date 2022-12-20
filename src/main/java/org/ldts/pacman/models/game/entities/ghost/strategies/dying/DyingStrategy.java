package org.ldts.pacman.models.game.entities.ghost.strategies.dying;

import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.strategies.GhostStrategy;
import org.ldts.pacman.models.game.Position;

public interface DyingStrategy extends GhostStrategy {

    @Override
    Position getNextPosition(Ghost ghost);
}
