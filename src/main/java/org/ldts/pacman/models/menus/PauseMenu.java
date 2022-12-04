package org.ldts.pacman.models.menus;

import org.ldts.pacman.states.ArenaState;

import java.util.Arrays;

public class PauseMenu extends Menu {
    private final ArenaState currentArenaState;

    public PauseMenu(ArenaState currentArenaState) {
        this.currentArenaState = currentArenaState;
        this.options = Arrays.asList("Resume", "Main Menu", "Exit");
    }

    public ArenaState getArenaState() {
        return currentArenaState;
    }
}
