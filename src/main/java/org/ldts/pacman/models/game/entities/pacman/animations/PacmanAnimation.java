package org.ldts.pacman.models.game.entities.pacman.animations;

import org.ldts.pacman.models.animations.Animation;
import org.ldts.pacman.models.game.entities.pacman.Pacman;

public abstract class PacmanAnimation extends Animation {
    protected Pacman pacman;

    public PacmanAnimation(long durationInMilliseconds, Pacman pacman) {
        super(durationInMilliseconds);
        this.pacman = pacman;
    }
}
