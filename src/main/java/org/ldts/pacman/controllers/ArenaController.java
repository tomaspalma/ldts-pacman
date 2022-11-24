package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;

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
        if (action == GameActions.ControlActions.EXIT) // missing kill_pacman
            game.setState(null);
        else {
            pacmanController.step(game, action, time);
            regularGhostController.step(game, action, time);
        }
    }
}
