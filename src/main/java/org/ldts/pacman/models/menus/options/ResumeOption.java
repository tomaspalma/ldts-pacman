package org.ldts.pacman.models.menus.options;

import org.ldts.pacman.Game;
import org.ldts.pacman.states.State;

import java.io.IOException;

public class ResumeOption extends Option {
    public ResumeOption(String message) {
        super(message);
    }

    @Override
    public void select(Game game, State state) throws IOException {
        game.setState(state);
    }
}
