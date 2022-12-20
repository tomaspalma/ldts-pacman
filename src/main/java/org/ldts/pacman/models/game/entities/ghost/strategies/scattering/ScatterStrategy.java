package org.ldts.pacman.models.game.entities.ghost.strategies.scattering;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.strategies.GhostStrategy;

public interface ScatterStrategy extends GhostStrategy {

    @Override
    Position getNextPosition(Ghost ghost);
}

