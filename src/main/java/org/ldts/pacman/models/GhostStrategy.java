package org.ldts.pacman.models;

public interface GhostStrategy {
    Position getNextPosition(Ghost ghost);
}
