package org.ldts.pacman.states.menus;

import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.controllers.menus.RegularMenuController;
import org.ldts.pacman.models.menus.Menu;
import org.ldts.pacman.states.State;
import org.ldts.pacman.viewers.Viewer;
import org.ldts.pacman.viewers.menus.RegularMenuViewer;

public class RegularMenuState extends State<Menu> {
    public RegularMenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new RegularMenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new RegularMenuController(getModel());
    }
}
