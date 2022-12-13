package org.ldts.pacman.models.game.entities;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.MovableEntityDirection;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.arena.grid.TeletransporterTile;
import org.ldts.pacman.models.game.arena.grid.Tile;

public abstract class MovableEntity extends Entity {
    protected final Position startPosition;
    protected int velocity;
    protected MovableEntityDirection currentDirection;
    public MovableEntity(Position position, Arena arena) {
        super(position, arena);
        this.startPosition = new Position(position.getX(), position.getY());
    }

    public int getVelocity() {
        return velocity;
    }

    public Position getStartPosition() {
        return this.startPosition;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public MovableEntityDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirectionTo(MovableEntityDirection currentDirection) {
        this.drawSymbol = currentDirection.getDrawSymbol();
        this.currentDirection = currentDirection;
    }



    public Position switchTile(Position position) {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        Tile currentTile = this.arena.getGameGrid().get(y - 1).get(x);
        Tile nextTile = this.arena.getGameGrid().get(position.getY() - 1).get(position.getX());

        currentTile.removeChild(this);
        nextTile.put(this);

        if(nextTile instanceof TeletransporterTile teletransporterTile)
            return teletransporterTile.getExitTile().getPosition();

        return position;
    }
}
