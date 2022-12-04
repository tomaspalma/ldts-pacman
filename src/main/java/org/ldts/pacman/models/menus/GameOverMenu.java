package org.ldts.pacman.models.menus;

import java.util.Arrays;

public class GameOverMenu extends Menu {
    private String condition;

    public GameOverMenu(String gameOver) {
        if (gameOver.equals("win")) {
            this.condition = "You Win";
            this.options = Arrays.asList("Play Again", "Main Menu", "Exit");
        }
        else if (gameOver.equals("loss")) {
            this.condition = "You Lose";
            this.options = Arrays.asList("Try Again", "Main Menu", "Exit");
        }
    }

    public String getCondition() {
        return condition;
    }
}
