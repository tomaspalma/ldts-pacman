package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;

public class PowerPellet extends FixedEdible implements GameObservable {
    private final List<GameObserver> observers = new ArrayList<>();

    public PowerPellet(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = "O";
        this.color = TextColor.ANSI.WHITE;
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(GameObserver observer: observers) {
            observer.changeBasedOnObservable();
        }
    }
}
