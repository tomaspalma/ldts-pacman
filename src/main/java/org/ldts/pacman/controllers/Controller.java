package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.GameActions;

import java.io.IOException;

// O controller vai processar e efetuar mudanças num modelo
public abstract class Controller<T> {
    private final T model;
    private int numberOfSteps;

    protected Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, GameActions.ControlActions action, long time) throws IOException;
}
