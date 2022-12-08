package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class RegularGhost extends Ghost implements GameObserver {

    private final Position startPosition;
    private final AtomicInteger noOfTimesConsequentlyEaten = new AtomicInteger(0);
    protected TextColor.ANSI originalColor;

    protected RegularGhost(Position position) {
        super(position);
        this.startPosition = new Position(position.getX(), position.getY(), this.position.getArena());
        this.drawSymbol = "^";
        this.frightenedStrategy = new FrightenedRunAwayStrategy();
        this.previousState = new ChasingState(this);
        this.currentState = new ChasingState(this);
    }


    // Regular ghosts will always behave this way
    @Override
    public GameActions.GhostCollisionWithPacman getCollisionWithPacmanResult() {
        return currentState.collideWithPacmanResult();
    }

    @Override
    public void changeBasedOnObservable() {
        this.currentState.transitionToState(new FrightenedState(this));
        Thread thread = new Thread(() -> {
            try {
                synchronized (noOfTimesConsequentlyEaten) {noOfTimesConsequentlyEaten.getAndIncrement();}
                Thread.sleep(5000);
                synchronized (noOfTimesConsequentlyEaten) {
                    noOfTimesConsequentlyEaten.getAndDecrement();
                    if(noOfTimesConsequentlyEaten.intValue() == 0) this.currentState.transitionToState(new ChasingState(this));
                }
                this.color = this.originalColor;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
    }

    public void changeColor(TextColor.ANSI newColor) {
        this.color = newColor;
    }

    public GhostState getState() {
        return currentState;
    }
}
