package org.ldts.pacman.models;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;

public class Pacman extends MovableEntity implements PacmanObservable {
    List<PacmanCollisionSpecialEntitiesObserver> observers = new ArrayList<>();
    private int lives = 3;
    public Pacman(Position position) {
        super(position);
        currentDirection = new PacmanDirectionRight(this);
        this.drawSymbol = currentDirection.getDrawSymbol();
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

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void removeObserver(GameObserver observer) {
    }

    @Override
    public void notifyObservers() {
    }
}
