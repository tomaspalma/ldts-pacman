package org.ldts.pacman.models.game;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.arena.grid.Tile;

import java.util.List;
import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
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

    public Position getPositionToTheLeft() {
       return new Position(this.x - 1, this.y);
    }

    public Position getPositionToTheRight() {
        return new Position(this.x + 1, this.y);
    }

    public Position getPositionAbove() {
        return new Position(this.x, this.y - 1);
    }

    public Position getPositionBelow() {
        return new Position(this.x, this.y + 1);
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

    public Vector getVectorTo(Position position1) {
        int x1 = position1.getX();
        int y1 = position1.getY();

        return new Vector(x1 - this.x, y1 - this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Position position)) return false;

        return x == position.x && y == position.y;
    }

    // Como para objetos iguais o hashcode tem de ser o mesmo e duas posições são iguais se o x e o y forem iguais
    // vamos usar o hashmap de x e y pois, se forem iguais, x e y de cada um vai ser igual e, por isso, a hash também
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
