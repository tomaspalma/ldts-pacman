package org.ldts.pacman.models.menus.options;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.states.ArenaState;
import org.ldts.pacman.states.State;

import java.io.IOException;

public class PlayOption extends Option {
    public PlayOption(String message) {
        super(message);
    }

    @Override
    public void select(Game game, State state) throws IOException {
        game.setState(new ArenaState(new Arena(20, 21, "maps/easy.txt")));
    }
}
