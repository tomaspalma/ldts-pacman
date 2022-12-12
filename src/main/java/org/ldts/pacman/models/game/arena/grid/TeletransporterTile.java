package org.ldts.pacman.models.game.arena.grid;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.Entity;
import org.ldts.pacman.models.game.entities.MovableEntity;

public class TeletransporterTile extends Tile {
    private TeletransporterTile exitTile;
    public TeletransporterTile(Position position, Arena arena) {
        super(position, arena);
    }

    @Override
    public void put(Entity child) {
        if(!(child instanceof MovableEntity)) throw new IllegalArgumentException("Non movable entity was moved to teletransportable tile");
        this.teletransportToExitTile((MovableEntity) child);
    }

    public void setExitTile(TeletransporterTile exitTile) {
        this.exitTile = exitTile;
    }

    public TeletransporterTile getExitTile() {
        return exitTile;
    }

    private void teletransportToExitTile(MovableEntity child) {
        child.getPosition().setXTo(exitTile.getPosition().getX());
        child.getPosition().setYTo(exitTile.getPosition().getY());
    }
}
