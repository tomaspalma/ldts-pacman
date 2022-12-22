package org.ldts.pacman.states;

import org.ldts.pacman.Game;
import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.game.GameActions;
import org.ldts.pacman.viewers.Viewer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;
    protected GameActions.ControlActions userControlAction;

    protected State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Viewer<T> getViewer();

    public abstract Controller<T> getController();

    public Controller<T> getRealController() {
        return this.controller;
    }

    public T getModel() {
        return model;
    }

    public GameActions.ControlActions getUserControlAction() {
        return userControlAction;
    }

    // Função que acaba
    public void step(Game game, GUI gui, long time) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {
        this.userControlAction = gui.getNextUserInput();
        controller.step(game, userControlAction, time);
        viewer.draw(gui);
    }
}
