package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;

import java.io.IOException;

public class PacmanController extends Controller<Arena> {
    public PacmanController(Arena model) {
        super(model);
    }

    @Override
    public void step(Game game, GameActions.ControlActions controlActions) throws IOException {

    }
}
