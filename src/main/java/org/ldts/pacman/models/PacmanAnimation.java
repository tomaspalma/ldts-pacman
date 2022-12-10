package org.ldts.pacman.models;

public abstract class PacmanAnimation extends Animation {
    protected Pacman pacman;

    public PacmanAnimation(long durationInMilliseconds, Pacman pacman) {
        super(durationInMilliseconds);
        this.pacman = pacman;
    }
}
