package org.ldts.pacman.models;

public class TeletransporterTile extends Tile {
    private Tile exitTile;
    public TeletransporterTile(Position position, Arena arena) {
        super(position, arena);
        //TODO Auto-generated constructor stub
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
        System.out.println(child.getPosition().getX() + ", " + child.getPosition().getY());
        child.getPosition().setXTo(exitTile.getPosition().getX());
        child.getPosition().setYTo(exitTile.getPosition().getY());
        System.out.println(child.getPosition().getX() + ", " + child.getPosition().getY());
    }
}
