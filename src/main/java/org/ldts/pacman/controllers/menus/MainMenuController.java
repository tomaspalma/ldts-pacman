package org.ldts.pacman.controllers.menus;

import org.ldts.pacman.Game;
import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.menus.MainMenu;
import org.ldts.pacman.states.ArenaState;

import java.io.IOException;

public class MainMenuController extends Controller<MainMenu> {

    public MainMenuController(MainMenu model) {
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
                        game.setState(new ArenaState(new Arena(21, 21, "maps/easy.txt")));
                        break;
                    case 1:
                        game.setState(null);
                        break;
                }
            default:
                break;
        }
    }

}
