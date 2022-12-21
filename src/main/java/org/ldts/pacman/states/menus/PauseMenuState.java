package org.ldts.pacman.states.menus;

import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.controllers.menus.PauseMenuController;
import org.ldts.pacman.models.menus.PauseMenu;
import org.ldts.pacman.states.State;
import org.ldts.pacman.viewers.Viewer;
import org.ldts.pacman.viewers.menus.PauseMenuViewer;

public class PauseMenuState extends State<PauseMenu> {
    public PauseMenuState(PauseMenu model) {
        super(model);
    }

    @Override
    protected Viewer<PauseMenu> getViewer() {
        return new PauseMenuViewer(getModel());
    }

    @Override
    public Controller<PauseMenu> getController() {
        return new PauseMenuController(getModel());
    }
}
