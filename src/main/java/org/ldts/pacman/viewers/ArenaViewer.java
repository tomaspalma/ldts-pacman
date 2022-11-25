package org.ldts.pacman.viewers;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.Entity;
import org.ldts.pacman.models.Position;

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

        gui.writeToScreen(new Position(0, 0), "Score: ", TextColor.ANSI.WHITE);
        gui.writeToScreen(new Position(getModel().getWidth() - 10, 0), "Lives: ", TextColor.ANSI.WHITE);
    }

    private void delegateToViewer(GUI gui, Entity model, EntityViewer viewer) throws IOException {
        viewer.draw(gui);
    }
}
