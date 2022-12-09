package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;

public class Pacman extends MovableEntity implements PacmanObservable {
    List<PacmanObserver> observers = new ArrayList<>();
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
        observers.add((PacmanObserver) observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserversItAteFixedEdibleAt(Position position) {
        for(PacmanObserver observer: observers)
            observer.changeOnPacmanEatFixedEdibleAt(position);
    }

    @Override
    public void notifyObserversItCollidedWithGhostAt(Position position) {
        System.out.println("s: " + this.observers.size());
        for(PacmanObserver observer: observers)
            observer.changeOnPacmanCollisionWithGhostAt(position);
    }
}
