package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.game.GameActions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

// O controller vai processar e efetuar mudanças num modelo
public abstract class Controller<T> {
    private final T model;
    boolean areSoundsSilenced = false;

    protected Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }


    public abstract void step(Game game, GameActions.ControlActions action, long time) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException;
    public void setAreSoundsSilenced(boolean areSoundsSilenced) {
        this.areSoundsSilenced = areSoundsSilenced;
    }

    public boolean areSoundsSilenced() {
        return areSoundsSilenced;
    }
}
