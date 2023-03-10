package org.ldts.pacman.models.game.entities.ghost.strategies.chasing;

import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;
import org.ldts.pacman.models.game.entities.ghost.strategies.GhostStrategy;

public interface ChaseStrategy extends GhostStrategy {

    @Override
    Position getNextPosition(Ghost ghost);
}
