package org.ldts.pacman.models;

public interface FrightenedStrategy extends GhostStrategy {
    public void execute(Ghost ghost);
    public Position getNextPosition(Ghost ghost);
}
