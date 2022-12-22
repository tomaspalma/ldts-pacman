package org.ldts.pacman.models.menus.options;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.menus.MainMenu;
import org.ldts.pacman.states.State;
import org.ldts.pacman.states.menus.RegularMenuState;

public class GoToMainMenuOption extends MenuOption {
    public GoToMainMenuOption(String message) {
        super(message);
    }

    @Override
    public void select(Game game, State state) {
        game.setState(new RegularMenuState(new MainMenu()));
    }
}
