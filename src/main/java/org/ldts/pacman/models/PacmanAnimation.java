package org.ldts.pacman.models;

public abstract class PacmanAnimation implements Animation {
    protected Pacman pacman;

    public PacmanAnimation(Pacman pacman) {
        this.pacman = pacman;
    } 
}
