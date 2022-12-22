package org.ldts.pacman.states;

import org.ldts.pacman.controllers.ArenaController;
import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.viewers.ArenaViewer;
import org.ldts.pacman.viewers.Viewer;

public class ArenaState extends State<Arena> {
    public ArenaState(Arena arena) {
        super(arena);
    }

    public ArenaState(Arena arena, Viewer viewer, Controller controller) {
        super(arena, viewer, controller);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new ArenaViewer(getModel());
    }

    @Override
    public Controller<Arena> getController() {
        return new ArenaController(getModel());
    }

}
