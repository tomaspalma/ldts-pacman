package org.ldts.pacman.models;

public abstract class Ghost extends Entity {
    protected GhostStrategy currentStrategy;

    protected Ghost(Position position) {
        super(position);
        currentStrategy = new AliveStrategy();
    }
}
