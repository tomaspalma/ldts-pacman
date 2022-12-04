package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.menus.GameOverMenu;
import org.ldts.pacman.models.menus.PauseMenu;
import org.ldts.pacman.states.ArenaState;
import org.ldts.pacman.states.menus.GameOverState;
import org.ldts.pacman.states.menus.PauseMenuState;

import java.io.IOException;

public class ArenaController extends Controller<Arena> {
    private final PacmanController pacmanController;
    private final RegularGhostController regularGhostController;

    public ArenaController(Arena model) {
        super(model);

        this.pacmanController = new PacmanController(model);
        this.regularGhostController = new RegularGhostController(model);
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        if(getModel().getFixedEdibles().isEmpty()) game.setState(new GameOverState(new GameOverMenu("win")));

        switch(action) {
            case EXIT: game.setState(null); break;
            case SWITCH_TO_PAUSE_MENU: game.setState(new PauseMenuState(new PauseMenu((ArenaState) game.getArenaState()))); break;
            default: stepChildControllers(game, action, time); break;
        }
    }

    private void stepChildControllers(Game game, GameActions.ControlActions action, long time) throws IOException {
        pacmanController.step(game, action, time);
        regularGhostController.step(game, action, time);
    }
}
