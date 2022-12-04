package org.ldts.pacman.states.menus;

import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.controllers.menus.MainMenuController;
import org.ldts.pacman.models.menus.MainMenu;
import org.ldts.pacman.states.State;
import org.ldts.pacman.viewers.menus.MainMenuViewer;
import org.ldts.pacman.viewers.Viewer;

public class MainMenuState extends State<MainMenu> {
    public MainMenuState(MainMenu model) {
        super(model);
    }

    @Override
    protected Viewer<MainMenu> getViewer() {
        return new MainMenuViewer(getModel());
    }

    @Override
    protected Controller<MainMenu> getController() {
        return new MainMenuController(getModel());
    }
}
