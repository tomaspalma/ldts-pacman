package org.ldts.pacman.viewers;

import org.ldts.pacman.gui.GUI;

import java.io.IOException;

// Vai servir para renderizar um determinado modelo do tipo T
public abstract class Viewer<T> {
    private final T model;

    protected Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawEntities(gui);
        gui.refresh();
    }

    protected abstract void drawEntities(GUI gui);
}
