package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;

public class PowerPellet extends FixedEdible implements GameObservable {
    private final List<EatenPowerPelletObserver> observers = new ArrayList<>();

    public PowerPellet(Position position) {
        super(position);
        this.drawSymbol = "O";
        this.color = TextColor.ANSI.WHITE;
    }

    @Override
    public void addObserver(EatenPowerPelletObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(EatenPowerPelletObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(EatenPowerPelletObserver observer: observers) {
            observer.changeBasedOnObservable();
        }
    }
}
