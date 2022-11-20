package org.ldts.pacman.models;

public abstract class Ghost extends Entity {
    protected GhostStrategy currentStrategy;
    //Para já isto pode ficar numa string
    protected String color;
    protected String drawSymbol;

    protected Ghost(Position position) {
        super(position);
        currentStrategy = new AliveStrategy();
    }

    protected String getDrawSymbol() {
        return drawSymbol;
    }

}
