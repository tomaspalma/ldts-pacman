package org.ldts.pacman.models;

public interface ScatterStrategy extends GhostStrategy {
    Position getNextPosition(Ghost ghost);
}

