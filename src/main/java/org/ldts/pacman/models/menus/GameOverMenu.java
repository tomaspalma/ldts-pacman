package org.ldts.pacman.models.menus;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.menus.options.ExitOption;
import org.ldts.pacman.models.menus.options.GoToMainMenuOption;
import org.ldts.pacman.models.menus.options.PlayOption;

import java.util.Arrays;

public class GameOverMenu extends Menu {

    public GameOverMenu(String gameOver) {
        if (gameOver.equals("win")) {
            this.color = TextColor.ANSI.YELLOW_BRIGHT;
            this.title = "You Win";
            this.options = Arrays.asList(new PlayOption("Play Again"), new GoToMainMenuOption("Main Menu"), new ExitOption("Exit"));
        }
        else if (gameOver.equals("loss")) {
            this.color = TextColor.ANSI.RED;
            this.title = "You Lose";
            this.options = Arrays.asList(new PlayOption("Try Again"), new GoToMainMenuOption("Main Menu"), new ExitOption("Exit"));
        }
    }
}
