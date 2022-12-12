package org.ldts.pacman.models.game.entities.pacman.animations;

import org.ldts.pacman.models.game.entities.pacman.Pacman;

public class PacmanEatingAnimation extends PacmanAnimation {

    private int control = 0;

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
        if(this.control % 2 == 0) {
            this.closePacmanMouth();
            this.control = 0;
        }
        else
            this.openPacmanMouth();

        control += 100000001;
    }

    private void closePacmanMouth() {
        this.pacman.setDrawSymbolTo("[");
    }

    private void openPacmanMouth() {
        String currentPacDirectionSymbol = this.pacman.getCurrentDirection().getDrawSymbol();
        this.pacman.setDrawSymbolTo(currentPacDirectionSymbol);
    }
}
