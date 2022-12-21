package org.ldts.pacman.models.game.entities.ghost;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.*;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState;
import org.ldts.pacman.models.game.entities.ghost.states.DeadState;
import org.ldts.pacman.models.game.entities.ghost.states.FrightenedState;
import org.ldts.pacman.models.game.entities.ghost.states.*;
import org.ldts.pacman.models.game.entities.ghost.strategies.dying.GhostHouseDyingStrategy;
import org.ldts.pacman.models.game.entities.ghost.strategies.frightened.FrightenedRunAwayStrategy;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class RegularGhost extends Ghost implements EatenPowerPelletObserver {

    private final AtomicInteger noOfTimesConsequentlyEaten = new AtomicInteger(0);

    protected RegularGhost(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = "^";
        this.frightenedStrategy = new FrightenedRunAwayStrategy();
        this.dyingStrategy = new GhostHouseDyingStrategy();
        this.nextStartState = new ChasingState(this);
        this.velocity = 1;
    }

    @Override
    public void die() {
        this.currentState.transitionToState(new DeadState(this));
    }

    // Regular ghosts will always behave this way
    @Override
    public GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult() {
        return currentState.collideWithPacmanResult();
    }

    @Override
    public void handlePowerPelletBeingEaten() {
        this.currentState.transitionToState(new FrightenedState(this));
        Thread thread = new Thread(() -> {
            try {
                synchronized (noOfTimesConsequentlyEaten) {noOfTimesConsequentlyEaten.getAndIncrement();}
                Thread.sleep(5000);
                synchronized (noOfTimesConsequentlyEaten) {
                    noOfTimesConsequentlyEaten.getAndDecrement();
                    if(noOfTimesConsequentlyEaten.intValue() == 0) {
                        this.color = this.originalColor;
                        GhostState newState = (this.previousState instanceof GhostHouseState) ? new GhostHouseState(this) : new ChasingState(this);
                        this.currentState.transitionToState(newState);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
    }

    public void returnToOriginalState() {
        this.currentState = this.originalState;
        this.previousState = this.originalState;
    }


    public void changeColor(TextColor.ANSI newColor) {
        this.color = newColor;
    }

}
