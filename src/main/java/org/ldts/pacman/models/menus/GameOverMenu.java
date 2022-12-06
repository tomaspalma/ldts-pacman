package org.ldts.pacman.models.menus;

import org.ldts.pacman.models.menus.options.ExitOption;
import org.ldts.pacman.models.menus.options.GoToMainMenuOption;
import org.ldts.pacman.models.menus.options.PlayOption;

import java.util.Arrays;

public class GameOverMenu extends Menu {
    private String message;

    public GameOverMenu(String gameOver) {
        if (gameOver.equals("win")) {
            this.message = "You Win!";
            this.options = Arrays.asList(new PlayOption("Play Again"), new GoToMainMenuOption("Main Menu"), new ExitOption("Exit"));
        }
        else if (gameOver.equals("loss")) {
            this.message = "You Lose";
            this.options = Arrays.asList(new PlayOption("Try Again"), new GoToMainMenuOption("Main Menu"), new ExitOption("Exit"));
        }
    }

    public String getMessage() {
        return message;
    }
}
