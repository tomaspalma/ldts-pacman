package org.ldts.pacman.models;

public interface DyingStrategy extends GhostStrategy {
    Position getNextPosition(Ghost ghost);
}
