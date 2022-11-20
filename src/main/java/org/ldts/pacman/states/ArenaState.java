package org.ldts.pacman.states;

import org.ldts.pacman.controllers.ArenaController;
import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.viewers.ArenaViewer;
import org.ldts.pacman.viewers.Viewer;

public class ArenaState extends State<Arena> {
    public ArenaState(Arena arena) {
        super(arena);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new ArenaViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }

}
