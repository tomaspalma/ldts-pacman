package org.ldts.pacman.models;

public class PacmanEatingAnimation extends PacmanAnimation {

    private int animationState = 0;

    public PacmanEatingAnimation(long durationInMilliseconds, Pacman pacman) {
        super(durationInMilliseconds, pacman);
    }

    public boolean isFinished() {
        return this.internalClock.getElapsedMilliseconds() > this.durationInMilliseconds;
    }

    @Override
    public void step() {
        this.internalClock.step();
        if( (this.internalClock.getElapsedMilliseconds() / 5000) % 2 == 0)
            this.closePacmanMouth();
        else
            this.openPacmanMouth();
    }

    private void closePacmanMouth() {
        this.pacman.setDrawSymbolTo("[");
    }

    private void openPacmanMouth() {
        String currentPacDirectionSymbol = this.pacman.getCurrentDirection().getDrawSymbol();
        this.pacman.setDrawSymbolTo(currentPacDirectionSymbol);
    }
}
