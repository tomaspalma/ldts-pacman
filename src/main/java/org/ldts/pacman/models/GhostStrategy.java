package org.ldts.pacman.models;

public interface GhostStrategy {
    public Position getNextPosition(Ghost ghost);
}
