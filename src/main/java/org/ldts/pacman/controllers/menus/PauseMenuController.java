package org.ldts.pacman.controllers.menus;

import org.ldts.pacman.Game;
import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.models.game.GameActions;
import org.ldts.pacman.models.menus.PauseMenu;

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
            case SWITCH_TO_PAUSE_MENU:
                game.setState(getModel().getArenaState());
                break;
            case SELECT:
                getModel().getCurrentOption().select(game, getModel().getArenaState());
                break;
            default:
                break;
        }
    }
}
