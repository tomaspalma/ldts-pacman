package org.ldts.pacman.models;

public interface ChaseStrategy extends GhostStrategy {
    Position getNextPosition(Ghost ghost);
}
