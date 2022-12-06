package org.ldts.pacman.models;

public abstract class MovableEntityDirection {
    protected final MovableEntity movableEntity;
    protected final int movableEntityX;
    protected final int movableEntityY;
    protected final Arena movableEntityArena;

    public MovableEntityDirection(MovableEntity movableEntity) {
        this.movableEntity = movableEntity;
        movableEntityX = movableEntity.getPosition().getX();
        movableEntityY = movableEntity.getPosition().getY();
        movableEntityArena = movableEntity.getArena();
    }

    /*
    * Position leftPosition = new Position(ghostX - 1, ghostY, ghostArena);
        Position rightPosition = new Position(ghostX + 1, ghostY, ghostArena);
        Position downPosition = new Position(ghostX, ghostY + 1, ghostArena);*/

    public Position getPossiblePositionToMoveLeft() {
        return new Position(movableEntityX - 1, movableEntityY, movableEntity.getArena());
    }

    public Position getPossiblePositionToMoveRight() {
        return new Position(movableEntityX + 1, movableEntityY, movableEntity.getArena());
    }

    public Position getPossiblePositionToMoveUp() {
        return new Position(movableEntityX, movableEntityY - 1, movableEntity.getArena());
    }

    public Position getPossiblePositionToMoveDown() {
        return new Position(movableEntityX, movableEntityY + 1, movableEntity.getArena());
    }
}
