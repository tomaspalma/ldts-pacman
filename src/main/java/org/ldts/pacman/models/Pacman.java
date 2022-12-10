package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;

public class Pacman extends MovableEntity implements PacmanObservable {
    List<PacmanObserver> observers = new ArrayList<>();
    List<PacmanAnimation> animations = new ArrayList<>();
    private int lives = 3;
    private boolean isMouthOpen = true;
    private final List<PacmanAnimation> animationsToExecute = new ArrayList<>();

    public Pacman(Position position) {
        super(position);
        currentDirection = new PacmanDirectionRight(this);
        this.drawSymbol = currentDirection.getDrawSymbol();
        this.color = TextColor.ANSI.YELLOW_BRIGHT;

        this.animationsToExecute.add(new PacmanEatingAnimation(0, this));
    }

    public List<PacmanAnimation> getAnimationsToExecute() {
        return this.animationsToExecute;
    }

    public boolean isMouthOpen() {
        return this.isMouthOpen;
    }

    public void die() {
        switchTile(this.startPosition);
        this.position = this.startPosition;
        lives--;
    }

    public List<Animation> getAnimations() {
        return this.possibleAnimations;
    }

    public void addAnimation(PacmanAnimation pacmanAnimation) {
       this.possibleAnimations.add(pacmanAnimation);
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
        for(PacmanObserver observer: observers)
            observer.changeOnPacmanCollisionWithGhostAt(position);
    }
}
