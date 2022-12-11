package org.ldts.pacman.models;

public class PacmanDirectionDown extends PacmanDirection {
    public PacmanDirectionDown(MovableEntity movableEntity) {
        super(movableEntity);
        this.drawSymbol = ">";
    }

    @Override
    public Position getNextPosition() {
        int x = this.movableEntity.position.getX();
        int y = this.movableEntity.position.getY();
        Arena arena = this.movableEntity.getArena();

        return new Position(x, y + 1, arena);
    }

    @Override
    public Position getGhostTargetTileWithForwardLevel(int forwardLevel) {
        int newX = this.movableEntity.getPosition().getX();
        int newY = this.movableEntity.getPosition().getY() + forwardLevel;
        Arena arena = this.movableEntity.getArena();

        return new Position(newX, newY, arena);
    }
}
