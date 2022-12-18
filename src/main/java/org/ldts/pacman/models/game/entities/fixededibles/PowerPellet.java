package org.ldts.pacman.models.game.entities.fixededibles;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.EatenPowerPelletObserver;
import org.ldts.pacman.models.GameObserver;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.PowerPelletObservable;

import java.util.ArrayList;
import java.util.List;

public class PowerPellet extends FixedEdible implements PowerPelletObservable {
    private final List<EatenPowerPelletObserver> observers = new ArrayList<>();

    public PowerPellet(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = ":";
        this.color = TextColor.ANSI.YELLOW;
        this.points = 50;
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.add((EatenPowerPelletObserver) observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove((EatenPowerPelletObserver) observer);
    }

    public List<EatenPowerPelletObserver> getObservers() {
        return observers;
    }

    @Override
    public void notifyObservers() {
        for(EatenPowerPelletObserver observer: observers) {
            observer.handlePowerPelletBeingEaten();
        }
    }
}
