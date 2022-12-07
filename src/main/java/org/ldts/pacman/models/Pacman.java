package org.ldts.pacman.models;
import com.googlecode.lanterna.TextColor;

import java.util.HashMap;

public class Pacman extends MovableEntity {
    private int lives = 3;
    public Pacman(Position position, Arena arena) {
        super(position, arena);
        currentDirection = new PacmanDirectionRight(this);
        // Aqui depois temos que ver qual é a orientação padrão que o pacman irá ter (se é que existirá uma)
        this.drawSymbol = ((PacmanDirection)currentDirection).getDrawSymbol();
        this.color = TextColor.ANSI.YELLOW_BRIGHT;
    }

    public void die(Position rebornPosition) {
        this.position = rebornPosition;
        lives--;
    }

    public int getRemainingLives() {
        return lives;
    }

    public void setLivesTo(int newLives) {
        this.lives = newLives;
    }

    // Este método vai ser chamado pelo respetivo controller
    public void changeOrientation(PacmanDirection pacmanDirection) {
        this.drawSymbol = pacmanDirection.drawSymbol;
    }
}
