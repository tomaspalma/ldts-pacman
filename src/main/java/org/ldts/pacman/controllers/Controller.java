package org.ldts.pacman.controllers;

import org.ldts.pacman.App;
import org.ldts.pacman.gui.GUI;

import java.io.IOException;

// O controller vai processar e efetuar mudanças num modelo
public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(App game, GUI.ACTION action, long time) throws IOException;
}
