package org.ldts.pacman.models.game.entities.pacman;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.*;
import org.ldts.pacman.models.animations.Animation;
import org.ldts.pacman.models.game.Clock;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.MovableEntity;
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanAnimation;
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanEatingAnimation;
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionRight;

import java.util.ArrayList;
import java.util.List;

public class Pacman extends MovableEntity implements PacmanObservable {
    List<PacmanObserver> observers = new ArrayList<>();
    List<PacmanAnimation> animations = new ArrayList<>();
    private int lives = 3;
    private final boolean isMouthOpen = true;
    private final List<PacmanAnimation> animationsToExecute = new ArrayList<>();

    public Pacman(Position position, Arena arena) {
        super(position, arena);
        currentDirection = new PacmanDirectionRight(this);
        this.drawSymbol = currentDirection.getDrawSymbol();
        this.color = TextColor.ANSI.YELLOW_BRIGHT;

        this.animationsToExecute.add(new PacmanEatingAnimation(0, new Clock(System.currentTimeMillis()), this));
    }

    public List<PacmanAnimation> getAnimationsToExecute() {
        return this.animationsToExecute;
    }

    public int getLives() {
        return lives;
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

    public void openMouth() {
        String currentPacDirectionSymbol = this.currentDirection.getDrawSymbol();
        this.setDrawSymbolTo(currentPacDirectionSymbol);
    }

    public void closeMouth() {
        this.setDrawSymbolTo("[");
    }
}
