package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.Game;

import java.util.ArrayList;
import java.util.List;

public class PowerPellet extends FixedEdible implements PowerPelletObservable {
    private final List<EatenPowerPelletObserver> observers = new ArrayList<>();

    public PowerPellet(Position position) {
        super(position);
        this.drawSymbol = "O";
        this.color = TextColor.ANSI.WHITE;
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.add((EatenPowerPelletObserver) observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove((EatenPowerPelletObserver) observer);
    }

    @Override
    public void notifyObservers() {
        for(EatenPowerPelletObserver observer: observers) {
            observer.handlePowerPelletBeingEaten();
        }
    }
}
