package org.ldts.pacman.models.game;

import org.ldts.pacman.models.game.Position;

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
}
