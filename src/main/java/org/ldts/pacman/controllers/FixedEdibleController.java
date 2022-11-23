package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;

import java.io.IOException;

public class FixedEdibleController extends Controller<Arena> {
    public FixedEdibleController(Arena model) {
        super(model);
    }

    @Override
    public void step(Game game, GameActions.ControlActions controlActions) throws IOException {

    }
}
