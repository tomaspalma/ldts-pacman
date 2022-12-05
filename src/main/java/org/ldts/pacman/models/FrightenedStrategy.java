package org.ldts.pacman.models;

public interface FrightenedStrategy extends GhostStrategy {
    void execute(Ghost ghost);
    Position getNextPosition(Ghost ghost);
}
