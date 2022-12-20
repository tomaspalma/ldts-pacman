package org.ldts.pacman.models.game.entities.ghost;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.states.ScatteringState;
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.AgressiveChaseStrategy;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionLeft;
import org.ldts.pacman.models.game.entities.ghost.strategies.scattering.ScatterToTopRight;

public class Blinky extends RegularGhost {
    public Blinky(Position position, Arena arena) {
        super(position, arena);
        this.previousState = new ScatteringState(this);
        this.currentState = this.previousState;
        this.originalState = this.previousState;
        this.color = TextColor.ANSI.RED;
        this.startSequenceInMilliseconds = 0;
        this.originalColor = color;
        this.chaseStrategy = new AgressiveChaseStrategy();
        this.scatterStrategy = new ScatterToTopRight();
        this.currentDirection = new GhostDirectionLeft(this);
    }
}
