package org.ldts.pacman.models.menus;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.menus.options.ExitOption;
import org.ldts.pacman.models.menus.options.PlayOption;

import java.util.Arrays;

public class MainMenu extends Menu {
    public MainMenu() {
        this.title = "P A C M A N";
        this.color = TextColor.ANSI.YELLOW_BRIGHT;
        this.options = Arrays.asList(new PlayOption("Play"), new ExitOption("Exit"));
    }
}
