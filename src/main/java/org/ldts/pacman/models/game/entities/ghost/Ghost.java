package org.ldts.pacman.models.game.entities.ghost;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.*;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.MovableEntity;
import org.ldts.pacman.models.game.entities.ghost.states.GhostHouseState;
import org.ldts.pacman.models.game.entities.ghost.states.GhostState;
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.ChaseStrategy;
import org.ldts.pacman.models.game.entities.ghost.strategies.dying.DyingStrategy;
import org.ldts.pacman.models.game.entities.ghost.strategies.frightened.FrightenedStrategy;
import org.ldts.pacman.models.game.entities.ghost.strategies.scattering.ScatterStrategy;

public abstract class Ghost extends MovableEntity {
    protected ChaseStrategy chaseStrategy;
    protected FrightenedStrategy frightenedStrategy;
    protected ScatterStrategy scatterStrategy;
    protected DyingStrategy dyingStrategy;

    protected GhostState originalState;
    protected GhostState previousState;
    protected GhostState currentState;
    protected TextColor.ANSI originalColor;
    protected long startSequenceInMilliseconds;
    protected GhostState nextStartState;

    public GhostState getNextStartState() {
        return nextStartState;
    }

    public GhostState getPreviousState() {
        return previousState;
    }

    public GhostState getOriginalState() {
        return originalState;
    }

    public void setOriginalState(GhostState originalState) {
        this.originalState = originalState;
    }

    public long getStartSequenceInMilliseconds() {
        return this.startSequenceInMilliseconds;
    }

    public ChaseStrategy getChaseStrategy() {
        return chaseStrategy;
    }

    public FrightenedStrategy getFrightenedStrategy() {
        return frightenedStrategy;
    }

    public DyingStrategy getDyingStrategy() {
        return dyingStrategy;
    }

    public ScatterStrategy getScatterStrategy() {
        return scatterStrategy;
    }

    public void setPreviousStateTo(GhostState state) {
       this.previousState = state;
    }

    public void setCurrentStateTo(GhostState state) {
        this.currentState = state;
    }

    public GhostState getCurrentState() {
        return currentState;
    }

    public abstract void die();

    public boolean isOnGhostHouseState() {
        return this.currentState instanceof GhostHouseState;
    }

    public abstract GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult();
    
    protected Ghost(Position position, Arena arena) {
        super(position, arena);
    }

    public TextColor.ANSI getOriginalColor() {
        return this.originalColor;
    }

    public boolean willBeInInvalidPosition(Position newPosition) {
        if(this.arena.hasItsBoundsViolatedBy(newPosition)) return true;

        boolean willBeOnObstacle = this.arena.getArenaTileAt(newPosition).containsObstacle();
        boolean willBeOnGate = this.arena.getArenaTileAt(newPosition).containsGate();

        return willBeOnObstacle || willBeOnGate;
    }
}
