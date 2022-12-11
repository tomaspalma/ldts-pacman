package org.ldts.pacman.models;

public class FixedEdible extends Entity {
    protected FixedEdible(Position position) {
        super(position);
    }
    protected int points;
    public int getPoints() {
        return this.points;
    }
}
