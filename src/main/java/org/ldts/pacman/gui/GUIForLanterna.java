package org.ldts.pacman.gui;

import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class GUIForLanterna implements GUI {
    private final Screen screen;

    public GUIForLanterna(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
