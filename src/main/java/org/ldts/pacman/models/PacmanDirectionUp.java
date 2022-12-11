package org.ldts.pacman.models;

public class PacmanDirectionUp extends PacmanDirection {
    public PacmanDirectionUp(MovableEntity movableEntity) {
        super(movableEntity);
        this.drawSymbol = "@";
    }

    @Override
    public Position getNextPosition() {
        return new Position(this.movableEntity.getPosition().getX(), this.movableEntity.getPosition().getY() - 1, this.movableEntity.getArena());
    }

    @Override
    public Position getGhostTargetTileWithForwardLevel(int forwardLevel) {
        int newY = this.movableEntity.getPosition().getY() - forwardLevel;
        int newX = this.movableEntity.getPosition().getX() - forwardLevel;

        return new Position(newX, newY, this.movableEntity.getArena());
    }
}
