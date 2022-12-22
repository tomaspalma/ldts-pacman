package org.ldts.pacman.models.game.entities.ghost;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionUp;
import org.ldts.pacman.models.game.entities.ghost.states.GhostHouseState;
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.PatrolChaseStrategy;
import org.ldts.pacman.models.game.entities.ghost.strategies.scattering.ScatterToBottomRight;

public class Inky extends RegularGhost {
    public Inky(Position position, Arena arena) {
        super(position, arena);
        this.previousState = new GhostHouseState(this);
        this.currentState = this.previousState;
        this.originalState = this.previousState;
        this.color = TextColor.ANSI.BLUE_BRIGHT;
        this.startSequenceInMilliseconds = 5000;
        this.originalColor = this.color;
        this.chaseStrategy = new PatrolChaseStrategy();
        this.scatterStrategy = new ScatterToBottomRight();
        this.currentDirection = new GhostDirectionUp(this);
    }
}
