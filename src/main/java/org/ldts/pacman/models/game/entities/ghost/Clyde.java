package org.ldts.pacman.models.game.entities.ghost;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionUp;
import org.ldts.pacman.models.game.entities.ghost.states.GhostHouseState;
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.HybridIgnorantChaseStrategy;
import org.ldts.pacman.models.game.entities.ghost.strategies.scattering.ScatterToBottomLeft;

public class Clyde extends RegularGhost {
    public Clyde(Position position, Arena arena) {
        super(position, arena);
        this.previousState = new GhostHouseState(this);
        this.currentState = this.previousState;
        this.originalState = this.previousState;
        this.color = TextColor.ANSI.YELLOW;
        this.originalColor = color;
        this.startSequenceInMilliseconds = 10000;
        this.chaseStrategy = new HybridIgnorantChaseStrategy();
        this.scatterStrategy = new ScatterToBottomLeft();
        this.currentDirection = new GhostDirectionUp(this);
    }
}
