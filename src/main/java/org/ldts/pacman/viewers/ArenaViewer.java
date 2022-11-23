package org.ldts.pacman.viewers;

import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.Entity;

public class ArenaViewer extends Viewer<Arena> {
    public ArenaViewer(Arena arena) {
        super(arena);
    }

    @Override
    protected void drawEntities(GUI gui) {
        for(Entity entity: getModel().getEntitiesToDraw()) {
            new EntityViewer(entity).drawEntities(gui);
        }
    }
}
