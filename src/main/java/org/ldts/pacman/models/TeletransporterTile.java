package org.ldts.pacman.models;

public class TeletransporterTile extends Tile {
    private Tile exitTile;
    public TeletransporterTile(Position position, Arena arena) {
        super(position, arena);
    }

    @Override
    public void put(Entity child) {
        if(!(child instanceof MovableEntity)) throw new IllegalArgumentException("Non movable entity was moved to teletransportable tile");
        this.teletransportToExitTile((MovableEntity) child);
    }

    public void setExitTile(Tile exitTile) {
        this.exitTile = exitTile;
    }

    private void teletransportToExitTile(MovableEntity child) {
        child.getPosition().setXTo(exitTile.getPosition().getX());
        child.getPosition().setYTo(exitTile.getPosition().getY());
    }
}
