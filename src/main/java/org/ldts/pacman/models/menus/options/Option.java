package org.ldts.pacman.models.menus.options;

import org.ldts.pacman.Game;
import org.ldts.pacman.states.State;

import java.io.IOException;

public abstract class Option {
    final private String message;

    protected Option(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public abstract void select(Game game, State state) throws IOException;
}
