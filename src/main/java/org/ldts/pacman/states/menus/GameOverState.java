package org.ldts.pacman.states.menus;

import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.controllers.menus.GameOverMenuController;
import org.ldts.pacman.controllers.menus.MainMenuController;
import org.ldts.pacman.models.menus.GameOverMenu;
import org.ldts.pacman.models.menus.MainMenu;
import org.ldts.pacman.states.State;
import org.ldts.pacman.viewers.Viewer;
import org.ldts.pacman.viewers.menus.GameOverMenuViewer;
import org.ldts.pacman.viewers.menus.MainMenuViewer;

public class GameOverState extends State<GameOverMenu> {
    public GameOverState(GameOverMenu model) {
        super(model);
    }

    @Override
    protected Viewer<GameOverMenu> getViewer() {
        return new GameOverMenuViewer(getModel());
    }

    @Override
    protected Controller<GameOverMenu> getController() {
        return new GameOverMenuController(getModel());
    }
}
