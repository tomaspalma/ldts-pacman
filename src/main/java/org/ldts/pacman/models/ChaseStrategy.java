package org.ldts.pacman.models;

public interface ChaseStrategy extends GhostStrategy {
    void execute(Ghost ghost);
}
