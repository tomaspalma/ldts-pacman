package org.ldts.pacman.models.menus;

import org.ldts.pacman.models.menus.options.ExitOption;
import org.ldts.pacman.models.menus.options.PlayOption;

import java.util.Arrays;

public class MainMenu extends Menu {
    public MainMenu() {
        this.options = Arrays.asList(new PlayOption("Play"), new ExitOption("Exit"));
    }
}
