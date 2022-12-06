package org.ldts.pacman.models.menus.options;

import org.ldts.pacman.Game;
import org.ldts.pacman.states.State;

import java.io.IOException;

public class ExitOption extends Option {
    public ExitOption(String message) {
        super(message);
    }

    @Override
    public void select(Game game, State state) {
        game.setState(null);
    }
}
