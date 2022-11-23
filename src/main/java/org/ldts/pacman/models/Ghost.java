package org.ldts.pacman.models;

public abstract class Ghost extends MovableEntity {
    protected GhostStrategy currentStrategy;

    protected Ghost(Position position) {
        super(position);
        currentStrategy = new AliveStrategy();
    }
}
