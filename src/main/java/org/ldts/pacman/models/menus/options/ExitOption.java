package org.ldts.pacman.models.menus.options;

import org.ldts.pacman.Game;
import org.ldts.pacman.states.State;

public class ExitOption extends MenuOption {
    public ExitOption(String message) {
        super(message);
    }

    @Override
    public void select(Game game, State state) {
        game.setState(null);
    }
}
