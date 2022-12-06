package org.ldts.pacman.models.menus;

import org.ldts.pacman.models.menus.options.ExitOption;
import org.ldts.pacman.models.menus.options.GoToMainMenuOption;
import org.ldts.pacman.models.menus.options.ResumeOption;
import org.ldts.pacman.states.ArenaState;

import java.util.Arrays;

public class PauseMenu extends Menu {
    private final ArenaState currentArenaState;

    public PauseMenu(ArenaState currentArenaState) {
        this.currentArenaState = currentArenaState;
        this.options = Arrays.asList(new ResumeOption("Resume"), new GoToMainMenuOption("Main Menu"), new ExitOption("Exit"));
    }

    public ArenaState getArenaState() {
        return currentArenaState;
    }
}
