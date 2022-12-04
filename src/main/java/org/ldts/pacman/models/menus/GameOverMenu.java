package org.ldts.pacman.models.menus;

import java.util.Arrays;

public class GameOverMenu extends Menu {
    private String message;

    public GameOverMenu(String gameOver) {
        if (gameOver.equals("win")) {
            this.message = "You Win";
            this.options = Arrays.asList("Play Again", "Main Menu", "Exit");
        }
        else if (gameOver.equals("loss")) {
            this.message = "You Lose";
            this.options = Arrays.asList("Try Again", "Main Menu", "Exit");
        }
    }

    public String getMessage() {
        return message;
    }
}
