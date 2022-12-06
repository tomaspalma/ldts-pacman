package org.ldts.pacman.models;

import java.util.Objects;

public class Position {
    // Atenção a comparar os valores de uma posição com outra por causa de serem números *floating point*
    // Para os comparar utilizar um epsilon. Exemplo:
    private final int x;
    private final int y;
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

    public boolean isOutOfBounds() {
        boolean isOutOfBoundsHorizontally = this.x < 0 || this.x >= this.arena.getGameGrid().get(0).size();
        boolean isOutOfBoundsVertically = this.y < 1 || this.y >= this.arena.getGameGrid().size();

        return isOutOfBoundsHorizontally || isOutOfBoundsVertically;
    }

    public boolean isOnSomeGhostPosition() {
        return this.arena.getGameGrid().get(this.y - 1).get(this.x)
            .containsGhost();
    }

    public boolean isOnSomeObstaclePosition() {
        return this.arena.getGameGrid().get(this.y - 1).get(this.x)
            .containsObstacle();
    }

    public boolean isOnGatePosition() {
        return this.arena.getGameGrid().get(this.y - 1).get(this.x)
            .containsGate();
    }

    public boolean isOnPacmanPosition() {
        return this.arena.getGameGrid().get(this.y - 1).get(this.x)
            .containsPacman();
    }

    public boolean isOnFixedEdiblePosition() {
        return this.arena.getGameGrid().get(this.y - 1).get(this.x)
            .containsFixedEdible();
    }

    public boolean isInvalidTo(MovableEntity movableEntity) {
        boolean isAliveGhostAndOnHouseGate = movableEntity instanceof Ghost && this.isOnGatePosition();

        return this.isOutOfBounds() || this.isOnSomeObstaclePosition() || isAliveGhostAndOnHouseGate;
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
