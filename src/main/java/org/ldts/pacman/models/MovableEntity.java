package org.ldts.pacman.models;

public abstract class MovableEntity extends Entity {
    protected MovableEntityDirection currentDirection;
    protected MovableEntity(Position position, Arena arena) {
        super(position, arena);
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

    public void switchTile(Position position) {
        int posX = this.getPosition().getX();
        int posY = this.getPosition().getY();

        Tile currentTile = this.arena.getGameGrid().get(posY - 1).get(posX);
        Tile nextTile = this.arena.getGameGrid().get(position.getY() - 1).get(position.getX());

        currentTile.removeChild(this);
        nextTile.put(this);
    }
}
