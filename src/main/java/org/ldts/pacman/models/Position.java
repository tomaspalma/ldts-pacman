package org.ldts.pacman.models;

import java.util.Objects;

public class Position {
    // Atenção a comparar os valores de uma posição com outra por causa de serem números *floating point*
    // Para os comparar utilizar um epsilon. Exemplo:
    private final Float x;
    private final Float y;

    public Position(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;

        return x.equals(position.x) && y.equals(position.y);
    }

    // Como para objetos iguais o hashcode tem de ser o mesmo e duas posições são iguais se o x e o y forem iguais
    // vamos usar o hashmap de x e y pois, se forem iguais, x e y de cada um vai ser igual e, por isso, a hash também
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
