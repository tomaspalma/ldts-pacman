package org.ldts.pacman.models;

import java.util.List;
import java.util.Objects;

public class Position {
    private int x;
    private int y;
    private final Arena arena;

    public Position(int x, int y, Arena arena) {
        this.x = x;
        this.y = y;
        this.arena = arena;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXTo(int x) {
        this.x = x;
    }

    public void setYTo(int y) {
        this.y = y;
    }

    public Arena getArena() {
        return this.arena;
    }

    public Position getPositionToTheLeft() {
       return new Position(this.x - 1, this.y, this.arena);
    }

    public Position getPositionToTheRight() {
        return new Position(this.x + 1, this.y, this.arena);
    }

    public Position getPositionAbove() {
        return new Position(this.x, this.y - 1, this.arena);
    }

    public Position getPositionBelow() {
        return new Position(this.x, this.y + 1, this.arena);
    }

    public double getDistanceTo(Position position) {
        return Math.sqrt(Math.pow(this.x - (double)position.getX(), 2) + Math.pow(this.y - (double)position.getY(), 2));
    }

    public Position getClosestPositionFrom(List<Position> possiblePositions) {
        double currentMinDirection = Double.MAX_VALUE;
        Position closestPossiblePosition = null;

        if(possiblePositions.isEmpty()) return this;

        for(Position position: possiblePositions) {
            if(position.getDistanceTo(this) < currentMinDirection) {
                currentMinDirection = position.getDistanceTo(this);
                closestPossiblePosition = position;
            }
        }

        return closestPossiblePosition;
    }

    public boolean isOutOfBounds() {
        boolean isOutOfBoundsHorizontally = this.x < 0 || this.x >= this.arena.getGameGrid().get(0).size();
        boolean isOutOfBoundsVertically = this.y < 1 || this.y >= this.arena.getGameGrid().size();

        return isOutOfBoundsHorizontally || isOutOfBoundsVertically;
    }

    public boolean isOnSomeGhostPosition() {
        Tile tile = this.arena.getGameGrid().get(this.y - 1).get(this.x);

        return tile.containsGhost();
    }

    public boolean isOnSomeObstaclePosition() {
        Tile tile = this.arena.getGameGrid().get(this.y - 1).get(this.x);

        return tile.containsObstacle();
    }

    public boolean isOnGatePosition() {
        Tile tile = this.arena.getGameGrid().get(this.y - 1).get(this.x);

        return tile.containsGate();
    }

    public boolean isOnPacmanPosition() {
        Tile tile = this.arena.getGameGrid().get(this.y - 1).get(this.x);

        if(tile.containsPacman()) System.out.println(this.x + ", " + this.y);

        return tile.containsPacman();
    }

    public boolean isOnFixedEdiblePosition() {
        Tile tile = this.arena.getGameGrid().get(this.y - 1).get(this.x);

        return tile.containsFixedEdible();
    }

    public boolean isInvalidTo(MovableEntity movableEntity) {
        if(this.isOutOfBounds()) return true;

        boolean canMoveToHouseGate = false;
        if(movableEntity instanceof Ghost) {
            canMoveToHouseGate = ((Ghost) movableEntity).getIsAlreadyPassedGhostHouseGateChasing();
        }

        boolean isOnGatePositionAndCantBe = !canMoveToHouseGate && this.isOnGatePosition();

        return this.isOnSomeObstaclePosition() || isOnGatePositionAndCantBe;
    }

    public Vector getVectorTo(Position position1) {
        int x1 = position1.getX();
        int y1 = position1.getY();

        return new Vector(x1 - this.x, y1 - this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;

        return x == position.x && y == position.y;
    }

    // Como para objetos iguais o hashcode tem de ser o mesmo e duas posições são iguais se o x e o y forem iguais
    // vamos usar o hashmap de x e y pois, se forem iguais, x e y de cada um vai ser igual e, por isso, a hash também
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
