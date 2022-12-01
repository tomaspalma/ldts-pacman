package org.ldts.pacman.models;

public interface ChaseStrategy extends GhostStrategy {
    public void execute(Ghost ghost);
}
