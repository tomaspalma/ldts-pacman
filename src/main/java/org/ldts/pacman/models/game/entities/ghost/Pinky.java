package org.ldts.pacman.models.game.entities.ghost;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionUp;
import org.ldts.pacman.models.game.entities.ghost.states.GhostHouseState;
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.AmbushChaseStrategy;
import org.ldts.pacman.models.game.entities.ghost.strategies.scattering.ScatterToTopLeft;

public class Pinky extends RegularGhost {
    public Pinky(Position position, Arena arena) {
        super(position, arena);
        this.previousState = new GhostHouseState(this);
        this.currentState = this.previousState;
        this.originalState = this.previousState;
        this.originalColor = color;
        this.startSequenceInMilliseconds = 6000;
        this.chaseStrategy = new AmbushChaseStrategy();
        this.scatterStrategy = new ScatterToTopLeft();
        this.currentDirection = new GhostDirectionUp(this);
    }
}
