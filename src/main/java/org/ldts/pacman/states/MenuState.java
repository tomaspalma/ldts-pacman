package org.ldts.pacman.states;

import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.models.Menu;
import org.ldts.pacman.viewers.Viewer;

public class MenuState extends State<Menu> {
    protected MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return null;
    }

    @Override
    protected Controller<Menu> getController() {
        return null;
    }
}
