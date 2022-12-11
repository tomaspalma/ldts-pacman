package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class RegularGhost extends Ghost implements EatenPowerPelletObserver {

    private final AtomicInteger noOfTimesConsequentlyEaten = new AtomicInteger(0);

    protected RegularGhost(Position position) {
        super(position);
        this.drawSymbol = "^";
        this.frightenedStrategy = new FrightenedRunAwayStrategy();
        this.dyingStrategy = new GhostHouseDyingStrategy();
        this.canCurrentlyMoveToGhostHouseGate = false;
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
                    if(noOfTimesConsequentlyEaten.intValue() == 0)
                        this.currentState.transitionToState(new ChasingState(this));
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
