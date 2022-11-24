package org.ldts.pacman.viewers;

import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.Entity;

import java.io.IOException;
import java.util.List;

public class ArenaViewer extends Viewer<Arena> {
    public ArenaViewer(Arena arena) {
        super(arena);
    }

    @Override
    protected void drawEntities(GUI gui) {
        for(Entity entity: getModel().getWalls()) {
            new EntityViewer(entity).drawEntities(gui);
        }

        for(Entity entity: getModel().getGhosts()) {
            new EntityViewer(entity).drawEntities(gui);
        }

        for(Entity entity: getModel().getFixedEdibles()) {
            new EntityViewer(entity).drawEntities(gui);
        }

        new EntityViewer(getModel().getPacman()).drawEntities(gui);
    }

    private void delegateToViewer(GUI gui, Entity model, EntityViewer viewer) throws IOException {
        viewer.draw(gui);
    }
}
