package org.ldts.pacman.models.game.entities.pacman.animations;

import org.ldts.pacman.models.animations.Animation;
import org.ldts.pacman.models.game.Clock;
import org.ldts.pacman.models.game.entities.pacman.Pacman;

public abstract class PacmanAnimation extends Animation {
    protected Pacman pacman;

    public PacmanAnimation(long durationInMilliseconds, Clock internalClock, Pacman pacman) {
        super(durationInMilliseconds, internalClock);
        this.pacman = pacman;
    }
}
