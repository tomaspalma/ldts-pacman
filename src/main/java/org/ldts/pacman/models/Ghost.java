package org.ldts.pacman.models;

public abstract class Ghost extends MovableEntity {
    enum GHOST_STATE {}
    protected GhostStrategy currentStrategy;
    //Para já isto pode ficar numa string
    protected String color;

    protected Ghost(Position position) {
        super(position);
        currentStrategy = new AliveStrategy();
    }

}
