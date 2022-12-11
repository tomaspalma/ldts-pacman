package org.ldts.pacman.controllers.menus;

import org.ldts.pacman.Game;
import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.menus.Menu;

import java.io.IOException;

public class RegularMenuController extends Controller<Menu> {
    public RegularMenuController(Menu model) {
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
                getModel().getCurrentOption().select(game, null);
            default:
                break;
        }
    }
}