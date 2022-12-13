package org.ldts.pacman.models.game;

import org.ldts.pacman.models.game.Position;

import java.util.Objects;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position getPositionBasedOnSumWith(Position position) {
        return new Position(position.getX() + this.x, position.getY() + this.y);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;

        return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
