package org.ldts.pacman.models;

public class PacmanEatingAnimation extends PacmanAnimation {

    public int control = 0;

    public int getControl() {
        return this.control;
    }

    public PacmanEatingAnimation(long durationInMilliseconds, Pacman pacman) {
        super(durationInMilliseconds, pacman);
    }

    public boolean isFinished() {
        return false;
    }

    @Override
    public void step() {
        if(control % 2 == 0)
            this.closePacmanMouth();
        else
            this.openPacmanMouth();

        control+=3;
    }

    private void closePacmanMouth() {
        this.pacman.setDrawSymbolTo("[");
    }

    private void openPacmanMouth() {
        String currentPacDirectionSymbol = this.pacman.getCurrentDirection().getDrawSymbol();
        this.pacman.setDrawSymbolTo(currentPacDirectionSymbol);
    }
}
