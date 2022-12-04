package org.ldts.pacman.controllers.menus;

import org.ldts.pacman.Game;
import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.menus.MainMenu;
import org.ldts.pacman.models.menus.PauseMenu;
import org.ldts.pacman.states.ArenaState;
import org.ldts.pacman.states.menus.MainMenuState;

import java.io.IOException;

public class PauseMenuController extends Controller<PauseMenu> {
    public PauseMenuController(PauseMenu model) {
        super(model);
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        switch (action) {
            case MOVE_DOWN:
                getModel().moveDown();
                break;
            case MOVE_UP:
                getModel().moveUp();
                break;
            case EXIT:
                game.setState(null);
                break;
            case SELECT:
                switch (getModel().getCurrentOption()) {
                    case 0:
                        game.setState(getModel().getArenaState());
                        break;
                    case 1:
                        game.setState(new MainMenuState(new MainMenu()));
                        break;
                    case 2:
                        game.setState(null);
                }
            default:
                break;
        }
    }
}
