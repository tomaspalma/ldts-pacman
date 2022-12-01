package org.ldts.pacman.models;

public interface FrightenedStrategy extends GhostStrategy {
    public void execute(Ghost ghost);
}
