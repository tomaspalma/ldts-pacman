package org.ldts.pacman.models;

import java.beans.JavaBean;

public abstract class MovableEntity extends Entity {
    protected MovableEntityDirection currentDirection;
    protected MovableEntity(Position position) {
        super(position);
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
