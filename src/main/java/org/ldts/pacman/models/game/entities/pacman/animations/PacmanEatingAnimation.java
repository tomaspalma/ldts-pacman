package org.ldts.pacman.models.game.entities.pacman.animations;

import org.ldts.pacman.models.game.Clock;
import org.ldts.pacman.models.game.entities.pacman.Pacman;
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirection;

public class PacmanEatingAnimation extends PacmanAnimation {

    private int control = 0;

    public int getControl() {
        return this.control;
    }

    public PacmanEatingAnimation(long durationInMilliseconds, Clock internalClock, Pacman pacman) {
        super(durationInMilliseconds, internalClock, pacman);
    }

    public boolean isFinished() {
        return false;
    }

    @Override
    public void step() {
        if(this.control % 2 == 0) {
            this.pacman.closeMouth();
            this.control = 0;
        }
        else
            this.pacman.openMouth();

        control += 1000001;
    }
}
