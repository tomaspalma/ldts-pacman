package org.ldts.pacman.states;

import org.ldts.pacman.Game;
import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.viewers.Viewer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    protected State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    // Função que acaba
    public void step(Game game, GUI gui, long time) throws IOException  {
        GameActions.ControlActions userControlAction = gui.getNextUserInput();
        controller.step(game, userControlAction, time);
        viewer.draw(gui);
    }
}
