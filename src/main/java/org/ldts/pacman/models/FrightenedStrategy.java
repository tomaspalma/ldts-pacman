package org.ldts.pacman.models;

public interface FrightenedStrategy extends GhostStrategy {
    Position getNextPosition(Ghost ghost);
}
